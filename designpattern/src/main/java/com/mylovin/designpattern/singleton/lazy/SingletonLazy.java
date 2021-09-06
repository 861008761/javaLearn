package com.mylovin.designpattern.singleton.lazy;

/**
 * 懒汉式
 * <p>
 * 懒汉和饿汉的对比：大家可以发现两者的区别基本上就是第一次创作时候的开销问题，以及线程安全问题（线程不安全模式的懒汉）。
 */
public class SingletonLazy {
    private static SingletonLazy instance;

    private SingletonLazy() {
    }

    public static SingletonLazy getInstance() {
        if (null == instance) { // 线程不安全
            instance = new SingletonLazy(); // 创建对象的地方移动到第一次进入该方法的时候
        }
        return instance;
    }
}
