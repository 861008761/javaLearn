package com.mylovin.designpattern.proxy.dynamicproxy.cglib;


import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class GoodmorningProxy implements MethodInterceptor {
    private Hello hello;

    public Hello getInstance(Hello hello) {
        this.hello = hello;
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(this.hello.getClass());
        enhancer.setCallback(this);
        return (Hello) enhancer.create();
    }

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("cglib动态代理");
        Object object = methodProxy.invokeSuper(o, objects);
        return object;
    }
}
