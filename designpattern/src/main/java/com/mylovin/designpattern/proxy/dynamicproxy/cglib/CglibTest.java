package com.mylovin.designpattern.proxy.dynamicproxy.cglib;

/**
 * cglib动态代理是利用ASM开源包，对被代理对象类的class文件加载进来，通过修改其字节码生成子类来处理
 * <p>
 * ASM: 一个 Java 字节码操控框架。它能被用来动态生成类或者增强既有类的功能。ASM 可以直接产生二进制 class 文件，
 * 也可以在类被加载入 Java 虚拟机之前动态改变类行为。Java class 被存储在严格格式定义的 .class 文件里，
 * 这些类文件拥有足够的元数据来解析类中的所有元素：类名称、方法、属性以及 Java 字节码（指令）。
 * ASM 从类文件中读入信息后，能够改变类行为，分析类信息，甚至能够根据用户要求生成新类。
 */
public class CglibTest {
    public static void main(String[] args) {
        GoodmorningProxy proxy = new GoodmorningProxy();
        Hello hello = proxy.getInstance(new Goodmorning());
        hello.sayHello();
    }
}
