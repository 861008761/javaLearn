package com.mylovin.rpc.spring;

import com.mylovin.rpc.proxy.RpcProxy;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.util.Assert;

public class RpcFactoryBean<T> implements FactoryBean<T> {
    private Class<T> rpcInterface;
    private boolean addToConfig = true;

    public RpcFactoryBean() {
    }

    public RpcFactoryBean(Class<T> rpcInterface) {
        this.rpcInterface = rpcInterface;
    }

    protected void checkDaoConfig() {
        Assert.notNull(this.rpcInterface, "Property 'rpcInterface' is required");
    }

    public T getObject() throws Exception {
        RpcProxy proxy = new RpcProxy(this.rpcInterface);
        return (T) proxy.getRpcInstance();
    }

    public Class<T> getObjectType() {
        return this.rpcInterface;
    }

    public boolean isSingleton() {
        return true;
    }

    public void setRpcInterface(Class<T> rpcInterface) {
        this.rpcInterface = rpcInterface;
    }

    public Class<T> getRpcInterface() {
        return this.rpcInterface;
    }

    public void setAddToConfig(boolean addToConfig) {
        this.addToConfig = addToConfig;
    }

    public boolean isAddToConfig() {
        return this.addToConfig;
    }
}
