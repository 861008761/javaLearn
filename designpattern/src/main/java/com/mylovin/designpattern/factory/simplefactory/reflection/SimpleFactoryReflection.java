package com.mylovin.designpattern.factory.simplefactory.reflection;

import com.mylovin.designpattern.factory.simplefactory.staticfactory.Phone;
import com.mylovin.designpattern.factory.simplefactory.staticfactory.PhoneTypeEnum;

import java.util.HashMap;
import java.util.Map;

public class SimpleFactoryReflection {
    private static final Map<PhoneTypeEnum, Class> phoneMap = new HashMap<>();

    public static void addPhone(PhoneTypeEnum phoneType, Class phoneClass) {
        phoneMap.put(phoneType, phoneClass);
    }

    public static Phone createPhone(PhoneTypeEnum phoneType) throws InstantiationException, IllegalAccessException {
        if (phoneMap.containsKey(phoneType)) {
            Class phoneClass = phoneMap.get(phoneType);
            return (Phone) phoneClass.newInstance();
        }
        return null;
    }
}
