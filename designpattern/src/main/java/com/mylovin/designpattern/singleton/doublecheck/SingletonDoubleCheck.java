package com.mylovin.designpattern.singleton.doublecheck;

/**
 * 双重检查锁式
 * <p>
 * instance使用volatile修饰
 * <p>
 * volatile的作用
 * 防止指令重排序，因为instance = new SingletonDoubleCheck()不是原子操作
 * 保证内存可见
 */
public class SingletonDoubleCheck {

    private static volatile SingletonDoubleCheck instance;

    private SingletonDoubleCheck() {
    }

    public static SingletonDoubleCheck getInstance() {
        //先检查实例是否存在，如果不存在才进入下面的同步块
        if (null == instance) {
            //同步块，线程安全的创建实例
            synchronized (SingletonDoubleCheck.class) {
                //再次检查实例是否存在，如果不存在才真正的创建实例
                if (null == instance) {
                    // 如果instance不加volatile修饰符的话，可能出现的极端情况：
                    // A线程进入synchronized区域，由于new对象在jvm中不是一个原子操作，操作可分为：画出一个内存区域，初始化和赋值三个步骤
                    // 同时JVM内部的优化机制：在单线程中一个操作的最终结果保持一致，但操作过程可能打乱顺序，new对象同样，可能先画出内存区域，第二步赋值，最后初始化
                    // 在第二步赋值后，instance即不为空，此时B线程进入getInstance第一个判断，发现instance不为空即返回，导致取到的是一个没有初始化的对象，程序异常。
                    instance = new SingletonDoubleCheck();
                }
            }
        }
        return instance;
    }
}
