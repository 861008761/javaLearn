package com.mylovin.designpattern.factory.factorymethod;

import com.mylovin.designpattern.factory.simplefactory.staticfactory.Phone;

/**
 * 定义一个抽象工厂PhoneFactory，其定义了产品的生产接口createPhone，但不负责具体的产品
 * 将生产任务交给不同的派生类工厂。这样不用通过指定类型来创建对象了。
 */
public class FactoryMethodTest {
    public static void main(String[] args) {
        PhoneFactory factory = new HuaweiFactory();
        Phone phone = factory.createPhone();
        phone.display();
    }
}
