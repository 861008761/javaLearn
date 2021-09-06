package com.mylovin.designpattern.state;

public class Washing {
    private State state;

    public void setState(State state) {
        this.state = state;
        if (null == state) {
            System.out.println("current state is null");
        } else {
            System.out.println("current state: " + state.getClass().getSimpleName());
        }
    }

    public void request() {
        if (null != state) {
            state.doJob(this);
        }
    }
}
