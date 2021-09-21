package com.mylovin.spring.basic;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class BasicMain {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(BasicConfig.class);
        BasicService service = (BasicService) context.getBean("basicService");
        String res = service.sayHello();
        System.out.println(res);
    }
}
