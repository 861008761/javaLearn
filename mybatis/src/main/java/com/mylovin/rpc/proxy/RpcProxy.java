package com.mylovin.rpc.proxy;

import java.lang.reflect.Proxy;

public class RpcProxy<T> {
    private Class<T> rpcInterface;

    public RpcProxy(Class<T> rpcInterface) {
        this.rpcInterface = rpcInterface;
    }

    public T getRpcInstance() {
        return (T) Proxy.newProxyInstance(this.rpcInterface.getClassLoader(), new Class[]{this.rpcInterface}, new RpcIncationHandler());
    }
}
