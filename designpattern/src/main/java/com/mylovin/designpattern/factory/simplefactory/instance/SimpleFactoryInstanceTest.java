package com.mylovin.designpattern.factory.simplefactory.instance;

import com.mylovin.designpattern.factory.simplefactory.staticfactory.HuaweiPhone;
import com.mylovin.designpattern.factory.simplefactory.staticfactory.Phone;
import com.mylovin.designpattern.factory.simplefactory.staticfactory.PhoneTypeEnum;
import com.mylovin.designpattern.factory.simplefactory.staticfactory.XiaomiPhone;

/**
 * 与reflection相似，把map中的class换成对象
 */
public class SimpleFactoryInstanceTest {
    public static void main(String[] args) {
        SimpleFactoryInstance.addPhone(PhoneTypeEnum.HuaweiPhone, new HuaweiPhone());
        SimpleFactoryInstance.addPhone(PhoneTypeEnum.XiaomiPhone, new XiaomiPhone());

        try {
            Phone huaweiPhone = SimpleFactoryInstance.createPhone(PhoneTypeEnum.HuaweiPhone);
            huaweiPhone.display();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
