package com.mylovin.designpattern.proxy.dynamicproxy.jdk;

/**
 * jdk动态代理是利用反射机制生成一个实现代理接口InvocationHandler的匿名类，在调用具体方法前调用InvokeHandler来处理
 */
public class JdkProxyTest {
    public static void main(String[] args) {
        Concert concert = new YelloBullProxy(new NewYearConcert());
        concert.buyTicket();
    }
}
