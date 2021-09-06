package com.mylovin.designpattern.observer.impl;

import java.util.LinkedList;
import java.util.List;

public class ConcreteSubject implements Subject {
    private List<Observer> observers = new LinkedList<>();

    @Override
    public void setState(int state) {
        notify("the new state is " + state);
    }

    @Override
    public int getState() {
        return 0;
    }

    @Override
    public void attach(Observer obs) {
        this.observers.add(obs);
    }

    @Override
    public void detach(Observer obs) {
        this.observers.remove(obs);
    }

    @Override
    public void notify(String msg) {
        for (Observer observer : observers) {
            observer.update(msg);
        }
    }
}
