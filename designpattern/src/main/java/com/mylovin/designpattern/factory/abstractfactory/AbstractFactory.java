package com.mylovin.designpattern.factory.abstractfactory;

import com.mylovin.designpattern.factory.simplefactory.staticfactory.Phone;

/**
 * 与前两种不同的地方，可以生产多种产品
 */
public interface AbstractFactory {
    Phone createPhone();
    Computer createComputer();
}
