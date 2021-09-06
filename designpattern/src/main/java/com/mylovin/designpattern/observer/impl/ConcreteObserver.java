package com.mylovin.designpattern.observer.impl;

public class ConcreteObserver implements Observer {
    @Override
    public void update(String msg) {
        System.out.println("observer receive new msg: " + msg);
    }
}
