package com.mylovin.designpattern.observer.example;

public class UserTwo implements User {
    private int expectPrice;

    public UserTwo(int expectPrice) {
        this.expectPrice = expectPrice;
    }

    @Override
    public boolean isExpectedPrice(int price) {
        return this.expectPrice >= price;
    }

    @Override
    public void shortMSG(String msg) {
        System.out.println("userTwo, " + msg);
    }
}
