package com.mylovin.spring.basic;

import org.springframework.stereotype.Service;

@Service
public class BasicService {
    public String sayHello() {
        return "hello, world...";
    }
}
