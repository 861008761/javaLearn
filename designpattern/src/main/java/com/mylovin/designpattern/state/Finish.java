package com.mylovin.designpattern.state;

public class Finish implements State {
    @Override
    public void doJob(Washing washing) {
        System.out.println("All Finished!");
        washing.setState(null);
    }
}
