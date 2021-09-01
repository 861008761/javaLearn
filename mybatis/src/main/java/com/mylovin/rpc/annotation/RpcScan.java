package com.mylovin.rpc.annotation;

import com.mylovin.rpc.spring.RpcFactoryBean;
import org.springframework.beans.factory.support.BeanNameGenerator;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@Documented
@Import({RpcScannerRegistrar.class})
public @interface RpcScan {
    String[] value() default {};

    String[] basePackages() default {};

    Class<?>[] basePackageClasses() default {};

    Class<? extends BeanNameGenerator> nameGenerator() default BeanNameGenerator.class;

    Class<? extends Annotation> annotationClass() default Annotation.class;

    Class<?> markerInterface() default Class.class;

    String sqlSessionTemplateRef() default "";

    String sqlSessionFactoryRef() default "";

    Class<? extends RpcFactoryBean> factoryBean() default RpcFactoryBean.class;
}
