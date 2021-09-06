package com.mylovin.designpattern.factory.simplefactory.staticfactory;

public class SimpleFactory {
    public static Phone createPhone(PhoneTypeEnum phoneType) {
        if (PhoneTypeEnum.HuaweiPhone.equals(phoneType)) {
            return new HuaweiPhone();
        } else if (PhoneTypeEnum.XiaomiPhone.equals(phoneType)) {
            return new XiaomiPhone();
        }
        return null;
    }
}
