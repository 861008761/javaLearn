package com.mylovin.designpattern.factory.abstractfactory;

import com.mylovin.designpattern.factory.simplefactory.staticfactory.Phone;
import com.mylovin.designpattern.factory.simplefactory.staticfactory.XiaomiPhone;

public class XiaomiFactory implements AbstractFactory {
    @Override
    public Phone createPhone() {
        return new XiaomiPhone();
    }

    @Override
    public Computer createComputer() {
        return new XiaomiComputer();
    }
}
