package com.mylovin.designpattern.observer.example;

public interface Product {
    public void setPrice(int price);
    public int getPrice();
    public void follow(User user);
    public void unfollow(User user);
    public void notifyLowPrice();
}
