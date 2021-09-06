package com.mylovin.designpattern.factory.factorymethod;

import com.mylovin.designpattern.factory.simplefactory.staticfactory.Phone;
import com.mylovin.designpattern.factory.simplefactory.staticfactory.XiaomiPhone;

public class XiaomiFactory implements PhoneFactory{
    @Override
    public Phone createPhone() {
        return new XiaomiPhone();
    }
}
