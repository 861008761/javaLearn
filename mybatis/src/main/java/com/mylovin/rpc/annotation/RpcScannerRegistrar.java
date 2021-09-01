package com.mylovin.rpc.annotation;

import com.mylovin.rpc.spring.ClassPathRpcScanner;
import com.mylovin.rpc.spring.RpcFactoryBean;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanNameGenerator;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.util.ClassUtils;
import org.springframework.util.StringUtils;

import java.lang.annotation.Annotation;
import java.util.*;
import java.util.stream.Collectors;

public class RpcScannerRegistrar implements ImportBeanDefinitionRegistrar, ResourceLoaderAware {
    private ResourceLoader resourceLoader;

    public RpcScannerRegistrar() {
    }

    public void setResourceLoader(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        AnnotationAttributes rpcScanAttrs = AnnotationAttributes.fromMap(importingClassMetadata.getAnnotationAttributes(RpcScan.class.getName()));
        if (rpcScanAttrs != null) {
            this.registerBeanDefinitions(rpcScanAttrs, registry);
        }

    }

    void registerBeanDefinitions(AnnotationAttributes annoAttrs, BeanDefinitionRegistry registry) {
        ClassPathRpcScanner scanner = new ClassPathRpcScanner(registry);
        Optional.ofNullable(this.resourceLoader).ifPresent(scanner::setResourceLoader);
        Class<? extends Annotation> annotationClass = annoAttrs.getClass("annotationClass");
        if (!Annotation.class.equals(annotationClass)) {
            scanner.setAnnotationClass(annotationClass);
        }

        Class<?> markerInterface = annoAttrs.getClass("markerInterface");
        if (!Class.class.equals(markerInterface)) {
            scanner.setMarkerInterface(markerInterface);
        }

        Class<? extends BeanNameGenerator> generatorClass = annoAttrs.getClass("nameGenerator");
        if (!BeanNameGenerator.class.equals(generatorClass)) {
            scanner.setBeanNameGenerator((BeanNameGenerator) BeanUtils.instantiateClass(generatorClass));
        }

        Class<? extends RpcFactoryBean> rpcFactoryBeanClass = annoAttrs.getClass("factoryBean");
        if (!RpcFactoryBean.class.equals(rpcFactoryBeanClass)) {
            scanner.setRpcFactoryBean((RpcFactoryBean)BeanUtils.instantiateClass(rpcFactoryBeanClass));
        }

        List<String> basePackages = new ArrayList();
        basePackages.addAll((Collection) Arrays.stream(annoAttrs.getStringArray("value")).filter(StringUtils::hasText).collect(Collectors.toList()));
        basePackages.addAll((Collection)Arrays.stream(annoAttrs.getStringArray("basePackages")).filter(StringUtils::hasText).collect(Collectors.toList()));
        basePackages.addAll((Collection)Arrays.stream(annoAttrs.getClassArray("basePackageClasses")).map(ClassUtils::getPackageName).collect(Collectors.toList()));
        scanner.registerFilters();
        scanner.doScan(StringUtils.toStringArray(basePackages));
    }

    static class RepeatingRegistrar extends RpcScannerRegistrar {
        RepeatingRegistrar() {
        }

        public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
            AnnotationAttributes rpcScansAttrs = AnnotationAttributes.fromMap(importingClassMetadata.getAnnotationAttributes(RpcScans.class.getName()));
            if (rpcScansAttrs != null) {
                Arrays.stream(rpcScansAttrs.getAnnotationArray("value")).forEach((rpcScanAttrs) -> {
                    this.registerBeanDefinitions(rpcScanAttrs, registry);
                });
            }

        }
    }
}