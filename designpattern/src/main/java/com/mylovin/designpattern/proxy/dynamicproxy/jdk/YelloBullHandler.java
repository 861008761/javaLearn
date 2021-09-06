package com.mylovin.designpattern.proxy.dynamicproxy.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class YelloBullHandler implements InvocationHandler {
    /**
     * 这个就是要代理的真实对象
     */
    private Concert concert;

    /**
     * 构造方法，给需要代理的真实对象赋初值
     *
     * @param concert
     */
    public YelloBullHandler(Concert concert) {
        this.concert = concert;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        // 在代理真实对象前可以添加一些自己的操作
        System.out.println("jdk动态代理");
        return method.invoke(concert, args);
    }
}
