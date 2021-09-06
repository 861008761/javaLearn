package com.mylovin.designpattern.observer.impl;

/**
 * 观察者模式、订阅/发布模式、事件监听模式
 * 应用场景：
 *  第一种  熟悉JDK的人应该知道在java.util包下除了常用的集合和map之外还有一个Observable类，他的实现方式其实就是观察者模式。里面也有添加、删除、通知等方法。
 *  这里需要注意是的 他是用Vector 作为订阅关系的容器，同时在他的定义方法中都添加synchronized关键字修饰类，以达到线程安全的目的。
 *
 *  第二种 在Spring中有一个ApplicationListener，也是采用观察者模式来处理的，ApplicationEventMulticaster作为主题，里面有添加，删除，通知等。
 *
 *  第三种  Google Guava的事件处理机制Guava EventBus 他的实现也是采用设计模式中的观察者设计模式。
 *
 *  大家看完本篇文章不知道有发现没有，其实整个内容都是围绕了解耦的思想来写的，观察者模式作为行为型设计模式，
 *  。
 */
public class ObserverTest {
    public static void main(String[] args) {
        Observer observer = new ConcreteObserver();

        Subject subject = new ConcreteSubject();
        subject.attach(observer);
        subject.setState(100);
        subject.notify("just test subject notify function!");
    }
}
