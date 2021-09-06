package com.mylovin.designpattern.factory.simplefactory.instance;

import com.mylovin.designpattern.factory.simplefactory.staticfactory.Phone;
import com.mylovin.designpattern.factory.simplefactory.staticfactory.PhoneTypeEnum;

import java.util.HashMap;
import java.util.Map;

public class SimpleFactoryInstance {
    private static final Map<PhoneTypeEnum, Phone> phoneMap = new HashMap<>();

    public static void addPhone(PhoneTypeEnum phoneType, Phone phone) {
        phoneMap.put(phoneType, phone);
    }

    public static Phone createPhone(PhoneTypeEnum phoneType) throws InstantiationException, IllegalAccessException {
        if (phoneMap.containsKey(phoneType)) {
            Phone phone = phoneMap.get(phoneType);
            return phone;
        }
        return null;
    }
}
