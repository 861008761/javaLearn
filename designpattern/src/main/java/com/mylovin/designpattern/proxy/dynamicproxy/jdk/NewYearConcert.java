package com.mylovin.designpattern.proxy.dynamicproxy.jdk;

public class NewYearConcert implements Concert {
    @Override
    public void buyTicket() {
        System.out.println("购买演唱会门票");
    }
}
