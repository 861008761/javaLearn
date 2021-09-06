package com.mylovin.designpattern.factory.simplefactory.reflection;

import com.mylovin.designpattern.factory.simplefactory.staticfactory.HuaweiPhone;
import com.mylovin.designpattern.factory.simplefactory.staticfactory.Phone;
import com.mylovin.designpattern.factory.simplefactory.staticfactory.PhoneTypeEnum;
import com.mylovin.designpattern.factory.simplefactory.staticfactory.XiaomiPhone;

/**
 * 看了静态工厂的代码，反射方式其实也很容易就实现了，但是在一些特定的情况下，并不适用，而且在某些特定的情况下是无法实现的
 * 而且反射机制也会降低程序的运行效果，在对性能要求很高的场景下应该避免这种实现。
 */
public class SimpleFactoryReflectionTest {
    public static void main(String[] args) {
        SimpleFactoryReflection.addPhone(PhoneTypeEnum.HuaweiPhone, HuaweiPhone.class);
        SimpleFactoryReflection.addPhone(PhoneTypeEnum.XiaomiPhone, XiaomiPhone.class);

        try {
            Phone huaweiPhone = SimpleFactoryReflection.createPhone(PhoneTypeEnum.HuaweiPhone);
            huaweiPhone.display();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
