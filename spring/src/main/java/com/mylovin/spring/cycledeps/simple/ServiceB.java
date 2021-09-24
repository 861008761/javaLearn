package com.mylovin.spring.cycledeps.simple;

public class ServiceB {
    private ServiceA serviceA;

    public ServiceB() {
    }

    public ServiceA getServiceA() {
        return serviceA;
    }

    public void setServiceA(ServiceA serviceA) {
        this.serviceA = serviceA;
        System.out.println("ServiceB类的对象设置属性serviceA");
    }
}
