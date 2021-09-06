package com.mylovin.designpattern.factory.simplefactory.staticfactory;

/**
 * 简单工厂-静态工厂
 * 这种方式创建看起来其实也没什么问题，根据类型创建不同的商品，但是有一个问题不知道大家发现没有？
 * 是不是每增加一种类型，还要去修改createProduct方法的 if else？这不是违背我们的开闭原则吗？
 *
 * 应用场景：
 * spring中的FactoryBean
 */
public class SimpeFactoryTest {
    public static void main(String[] args) {
        Phone huaweiPhone = SimpleFactory.createPhone(PhoneTypeEnum.HuaweiPhone);
        huaweiPhone.display();

        Phone xiaomiPhone = SimpleFactory.createPhone(PhoneTypeEnum.XiaomiPhone);
        xiaomiPhone.display();
    }
}
