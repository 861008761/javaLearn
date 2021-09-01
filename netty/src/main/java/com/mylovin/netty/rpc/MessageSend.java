package com.mylovin.netty.rpc;

public class MessageSend implements Basic {
    @Override
    public String send() {
        return "send msg success";
    }

    @Override
    public String send(String msg) {
        return "发送的内容为: " + msg;
    }
}
