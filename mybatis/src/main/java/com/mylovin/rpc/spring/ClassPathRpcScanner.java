package com.mylovin.rpc.spring;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.AnnotatedBeanDefinition;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanDefinitionHolder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.core.type.filter.AssignableTypeFilter;

import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Set;

public class ClassPathRpcScanner extends ClassPathBeanDefinitionScanner {
    private static final Logger LOGGER = LoggerFactory.getLogger(ClassPathRpcScanner.class);
    private boolean addToConfig = true;
    private String sqlSessionTemplateBeanName;
    private String sqlSessionFactoryBeanName;
    private Class<? extends Annotation> annotationClass;
    private Class<?> markerInterface;
    private RpcFactoryBean<?> rpcFactoryBean = new RpcFactoryBean();

    public ClassPathRpcScanner(BeanDefinitionRegistry registry) {
        super(registry, false);
    }

    public void setAddToConfig(boolean addToConfig) {
        this.addToConfig = addToConfig;
    }

    public void setAnnotationClass(Class<? extends Annotation> annotationClass) {
        this.annotationClass = annotationClass;
    }

    public void setMarkerInterface(Class<?> markerInterface) {
        this.markerInterface = markerInterface;
    }

    public void setRpcFactoryBean(RpcFactoryBean<?> rpcFactoryBean) {
        this.rpcFactoryBean = rpcFactoryBean != null ? rpcFactoryBean : new RpcFactoryBean();
    }

    public void registerFilters() {
        boolean acceptAllInterfaces = true;
        if (this.annotationClass != null) {
            this.addIncludeFilter(new AnnotationTypeFilter(this.annotationClass));
            acceptAllInterfaces = false;
        }

        if (this.markerInterface != null) {
            this.addIncludeFilter(new AssignableTypeFilter(this.markerInterface) {
                protected boolean matchClassName(String className) {
                    return false;
                }
            });
            acceptAllInterfaces = false;
        }

        if (acceptAllInterfaces) {
            this.addIncludeFilter((metadataReader, metadataReaderFactory) -> {
                return true;
            });
        }

        this.addExcludeFilter((metadataReader, metadataReaderFactory) -> {
            String className = metadataReader.getClassMetadata().getClassName();
            return className.endsWith("package-info");
        });
    }

    public Set<BeanDefinitionHolder> doScan(String... basePackages) {
        Set<BeanDefinitionHolder> beanDefinitions = super.doScan(basePackages);
        if (beanDefinitions.isEmpty()) {
            LOGGER.warn("No MyBatis rpc was found in '" + Arrays.toString(basePackages) + "' package. Please check your configuration.");
        } else {
            this.processBeanDefinitions(beanDefinitions);
        }
        return beanDefinitions;
    }

    private void processBeanDefinitions(Set<BeanDefinitionHolder> beanDefinitions) {
        Iterator var3 = beanDefinitions.iterator();

        while(var3.hasNext()) {
            BeanDefinitionHolder holder = (BeanDefinitionHolder)var3.next();
            GenericBeanDefinition definition = (GenericBeanDefinition)holder.getBeanDefinition();
            String beanClassName = definition.getBeanClassName();
            LOGGER.debug("Creating RpcFactoryBean with name '" + holder.getBeanName() + "' and '" + beanClassName + "' rpcInterface");
            definition.getConstructorArgumentValues().addGenericArgumentValue(beanClassName);
            definition.setBeanClass(this.rpcFactoryBean.getClass());
            definition.getPropertyValues().add("addToConfig", this.addToConfig);
            boolean explicitFactoryUsed = false;

            if (!explicitFactoryUsed) {
                LOGGER.debug("Enabling autowire by type for RpcFactoryBean with name '" + holder.getBeanName() + "'.");
                definition.setAutowireMode(2);
            }
        }

    }

    protected boolean isCandidateComponent(AnnotatedBeanDefinition beanDefinition) {
        return beanDefinition.getMetadata().isInterface() && beanDefinition.getMetadata().isIndependent();
    }

    protected boolean checkCandidate(String beanName, BeanDefinition beanDefinition) {
        if (super.checkCandidate(beanName, beanDefinition)) {
            return true;
        } else {
            LOGGER.warn("Skipping RpcFactoryBean with name '" + beanName + "' and '" + beanDefinition.getBeanClassName() + "' rpcInterface. Bean already defined with the same name!");
            return false;
        }
    }
}