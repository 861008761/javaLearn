package com.mylovin.netty.rpc.pojo;

public class Response {
    private String paramReturnType;
    private String returnValue;

    public String getParamReturnType() {
        return paramReturnType;
    }

    public void setParamReturnType(String paramReturnType) {
        this.paramReturnType = paramReturnType;
    }

    public String getReturnValue() {
        return returnValue;
    }

    public void setReturnValue(String returnValue) {
        this.returnValue = returnValue;
    }

    @Override
    public String toString() {
        return "Response[" + "paramReturnType: " + paramReturnType + "]";
    }
}
