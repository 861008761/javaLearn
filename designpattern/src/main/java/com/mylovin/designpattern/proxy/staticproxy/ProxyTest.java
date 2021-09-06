package com.mylovin.designpattern.proxy.staticproxy;

public class ProxyTest {
    public static void main(String[] args) {
        // 第一种方式，使用反射获取代理对象
        try {
            Server server = new ProxyServer();
            server.visit("www.google.com");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        // 第二种方式，创建对象
        Server server = new ProxyServer(new RealServer());
        server.visit("www.youtube.com");
    }
}
