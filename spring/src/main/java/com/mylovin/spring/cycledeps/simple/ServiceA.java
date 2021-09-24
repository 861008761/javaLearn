package com.mylovin.spring.cycledeps.simple;

public class ServiceA {
    private ServiceB serviceB;

    public ServiceA() {
    }

    public ServiceB getServiceB() {
        return serviceB;
    }

    public void setServiceB(ServiceB serviceB) {
        this.serviceB = serviceB;
        System.out.println("ServiceA类的对象设置属性serviceB");
    }
}
