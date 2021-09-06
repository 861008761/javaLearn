package com.mylovin.designpattern.proxy.dynamicproxy.cglib;

public class Goodmorning implements Hello {
    @Override
    public void sayHello() {
        System.out.println("hello, good morning...");
    }
}
