package com.mylovin.designpattern.factory.abstractfactory;

import com.mylovin.designpattern.factory.simplefactory.staticfactory.HuaweiPhone;
import com.mylovin.designpattern.factory.simplefactory.staticfactory.Phone;

public class HuaweiFactory implements AbstractFactory {
    @Override
    public Phone createPhone() {
        return new HuaweiPhone();
    }

    @Override
    public Computer createComputer() {
        return new HuaweiComputer();
    }
}
