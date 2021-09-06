package com.mylovin.designpattern.state;

public class Start implements State {
    @Override
    public void doJob(Washing washing) {
        System.out.println("Start Washing Clothes!");
        washing.setState(new Work());
        washing.request();
    }
}
