package com.mylovin.netty.nettycode.pipe.handler;

import com.mylovin.netty.nettycode.pipe.context.Context;

public class CustomHandlerTwo implements Handler {
    @Override
    public void fireChannelRead(Context context) {
        System.out.println(this.getClass().getSimpleName() + " method[fireChannelRead] called..");
        context.fireChannelRead();
    }
}
