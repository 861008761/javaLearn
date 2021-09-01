package com.mylovin.rpc.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class RpcIncationHandler implements InvocationHandler {
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        // 这个地方使用rpc
        return 123;
    }
}
