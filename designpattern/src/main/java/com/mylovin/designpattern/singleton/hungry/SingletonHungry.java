package com.mylovin.designpattern.singleton.hungry;

/**
 * 饿汉式
 * <p>
 * 单例有如下几个特点：
 * <p>
 * 在Java应用中，单例模式能保证在一个JVM中，该对象只有一个实例存在
 * 构造器必须是私有的，外部类无法通过调用构造器方法创建该实例
 * 没有公开的set方法，外部类无法调用set方法创建该实例
 * 提供一个公开的get方法获取唯一的这个实例
 * <p>
 * 那单例模式有什么好处呢？
 * <p>
 * 某些类创建比较频繁，对于一些大型的对象，这是一笔很大的系统开销
 * 省去了new操作符，降低了系统内存的使用频率，减轻GC压力
 * 系统中某些类，如spring里的controller，控制着处理流程，如果该类可以创建多个的话，系统完全乱了
 * 避免了对资源的重复占用
 * <p>
 * 应用：
 * spring的对象默认都是单例
 */
public class SingletonHungry {
    // 创建一个实例对象
    private static SingletonHungry instance = new SingletonHungry();

    // 私有构造函数，防止其他地方实例对象
    private SingletonHungry() {
    }

    // 静态get方法
    public static SingletonHungry getInstance() {
        return instance;
    }
}
