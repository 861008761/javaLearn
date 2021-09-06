package com.mylovin.designpattern.factory.factorymethod;

import com.mylovin.designpattern.factory.simplefactory.staticfactory.HuaweiPhone;
import com.mylovin.designpattern.factory.simplefactory.staticfactory.Phone;

public class HuaweiFactory implements PhoneFactory {
    @Override
    public Phone createPhone() {
        return new HuaweiPhone();
    }
}
