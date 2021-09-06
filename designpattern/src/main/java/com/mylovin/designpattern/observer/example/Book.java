package com.mylovin.designpattern.observer.example;

import java.util.LinkedList;
import java.util.List;

public class Book implements Product {
    private List<User> users = new LinkedList<>();
    private int curPrice;

    @Override
    public void setPrice(int price) {
        this.curPrice = price;
        System.out.println("set current price: " + price);
        notifyLowPrice();
    }

    @Override
    public int getPrice() {
        return this.curPrice;
    }

    @Override
    public void follow(User user) {
        this.users.add(user);
    }

    @Override
    public void unfollow(User user) {
        this.users.remove(user);
    }

    @Override
    public void notifyLowPrice() {
        for (User user : users) {
            if (user.isExpectedPrice(this.curPrice)) {
                user.shortMSG("current price is low than your expected...");
            }
        }
    }
}
