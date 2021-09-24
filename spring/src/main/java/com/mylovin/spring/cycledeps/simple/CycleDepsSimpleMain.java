package com.mylovin.spring.cycledeps.simple;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 模拟spring解决循环依赖代码逻辑
 */
public class CycleDepsSimpleMain {
    public static void main(String[] args) {
        try {
            // 加载类
            Class clzA = Class.forName("com.mylovin.spring.cycledeps.simple.ServiceA");
            Class clzB = Class.forName("com.mylovin.spring.cycledeps.simple.ServiceB");

            // 获取构造函数
            Constructor constructorA = clzA.getConstructor(null);
            Constructor constructorB = clzB.getConstructor(null);

            // 实例化对象
            ServiceA serviceA = (ServiceA) constructorA.newInstance();
            ServiceB serviceB = (ServiceB) constructorB.newInstance();

            // 反射方式获取set方法
            Method setServiceBMethod = clzA.getDeclaredMethod("setServiceB", ServiceB.class);
            Method setServiceAMethod = clzB.getDeclaredMethod("setServiceA", ServiceA.class);

            // 设置属性
            setServiceBMethod.invoke(serviceA, serviceB);
            setServiceAMethod.invoke(serviceB, serviceA);

            System.in.read();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
