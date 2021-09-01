package com.mylovin.netty.rpc;

import com.alibaba.fastjson.JSON;
import com.mylovin.netty.rpc.client.ClientHandler;
import com.mylovin.netty.rpc.client.Main;
import com.mylovin.netty.rpc.pojo.RequestProto;
import org.apache.log4j.Logger;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class MessageProcessProxy implements InvocationHandler {
    private static final Logger LOG = Logger.getLogger(Main.class);
    private Class<?> clazz;

    public MessageProcessProxy() {
    }

    public MessageProcessProxy(Class<?> clazz) {
        this.clazz = clazz;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        // 这里使用rpc
        RequestProto.Request.Builder builder = RequestProto.Request.newBuilder();
        builder.setClazz(clazz.getName());
        builder.setMethod(method.getName());
        Class<?>[] parameterTypes = method.getParameterTypes();
        builder.setParamTypes(JSON.toJSONString(parameterTypes));
        builder.setArgs(JSON.toJSONString(args));
        ClientHandler.sendMsg(builder.build());
        return "hhhh";
    }
}
