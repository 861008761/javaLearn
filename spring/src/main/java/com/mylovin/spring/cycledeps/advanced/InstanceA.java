package com.mylovin.spring.cycledeps.advanced;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class InstanceA {
    public InstanceA() {
        System.out.println("调用InstanceA无参构造函数");
    }

    @Autowired
    private InstanceB instanceB;

    public InstanceB getInstanceB() {
        return instanceB;
    }

    public void setInstanceB(InstanceB instanceB) {
        this.instanceB = instanceB;
    }
}
