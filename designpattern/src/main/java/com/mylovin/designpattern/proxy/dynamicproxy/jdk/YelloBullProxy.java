package com.mylovin.designpattern.proxy.dynamicproxy.jdk;

import java.lang.reflect.Proxy;

/**
 * 黄牛代理买票
 */
public class YelloBullProxy implements Concert {

    /**
     * 黄牛代理实例
     */
    public Concert concert;

    public YelloBullProxy(Concert concert) {
        this.concert = (Concert) Proxy.newProxyInstance(this.getClass().getClassLoader(), concert.getClass().getInterfaces(), new YelloBullHandler(concert));
    }

    @Override
    public void buyTicket() {
        this.concert.buyTicket();
    }
}