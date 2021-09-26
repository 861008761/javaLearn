package com.mylovin.spring.cycledeps.advanced;

import org.springframework.beans.BeansException;

public interface MyObjectFactory<T> {
    T getObject() throws BeansException;
}
