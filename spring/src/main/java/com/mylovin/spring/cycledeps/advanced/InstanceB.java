package com.mylovin.spring.cycledeps.advanced;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class InstanceB {
    public InstanceB() {
        System.out.println("调用InstanceB无参构造函数");
    }

    @Autowired
    private InstanceA instanceA;

    public InstanceA getInstanceA() {
        return instanceA;
    }

    public void setInstanceA(InstanceA instanceA) {
        this.instanceA = instanceA;
    }
}
