package com.mylovin.designpattern.observer.example;

public class UserOne implements User {
    private int expectPrice;

    public UserOne(int expectPrice) {
        this.expectPrice = expectPrice;
    }

    @Override
    public boolean isExpectedPrice(int price) {
        return this.expectPrice >= price;
    }

    @Override
    public void shortMSG(String msg) {
        System.out.println("userOne, " + msg);
    }
}
