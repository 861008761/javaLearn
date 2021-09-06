package com.mylovin.designpattern.state;

public class Work implements State {
    @Override
    public void doJob(Washing washing) {
        System.out.println("Working Now!");
        washing.setState(new Finish());
        washing.request();
    }
}
