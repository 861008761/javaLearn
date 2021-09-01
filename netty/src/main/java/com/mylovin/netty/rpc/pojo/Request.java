package com.mylovin.netty.rpc.pojo;

/**
 * rpc请求类的格式
 * <p>
 * 这个格式如何使用protobuf序列化？？？Object[]如何处理？？？
 */
public class Request {
    /**
     * 类名
     */
    String clazz;
    /**
     * 方法
     */
    String method;
    /**
     * 方法的参数类型
     */
    Object[] parameterTypes;
    /**
     * 针对特定对象传入的参数值
     */
    Object[] arguments;

    public String getClazz() {
        return clazz;
    }

    public void setClazz(String clazz) {
        this.clazz = clazz;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public Object[] getParameterTypes() {
        return parameterTypes;
    }

    public void setParameterTypes(Object[] parameterTypes) {
        this.parameterTypes = parameterTypes;
    }

    public Object[] getArguments() {
        return arguments;
    }

    public void setArguments(Object[] arguments) {
        this.arguments = arguments;
    }

    @Override
    public String toString() {
        return "Request[" + "clazz: " + clazz + ", method: "
                + method + ", parameterTypes: " + parameterTypes + ", arguments: " + arguments + "]";
    }
}
