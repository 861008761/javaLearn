package com.mylovin.designpattern.observer.example;

/**
 * 我们以一个更加实际的例子——商品价格的变动来体会一下观察者模式的用途。
 *
 * 在网上购物的时候，商品一般都有一个价格变动通知，前提是我们关注了该商品。
 *
 * 这里我们稍微变通一下，只有当关注的商品价格下降，且低于用户期望购买价格的时候，才会给用户发送一条商品降价的短信通知。
 */
public class ObserverExampleTest {
    public static void main(String[] args) {
        User userOne = new UserOne(100);
        User userTwo = new UserTwo(150);
        Book book = new Book();
        book.follow(userOne);
        book.follow(userTwo);
        // 初始价格设置为160
        book.setPrice(160);
        // 降价，只有userTwo愿意买
        book.setPrice(120);
        // 再降价，两个人都可以买
        book.setPrice(80);
        // userOne不再关注
        book.unfollow(userOne);
        // 降价之后，只有userTwo收到消息
        book.setPrice(50);
    }
}
