package com.mylovin.netty.nettycode.pipe.invoker;

import com.mylovin.netty.nettycode.pipe.context.Context;

public class InvokerUtil {
    public static void invokeChannelReadNow(Context context) {
        context.handler.fireChannelRead(context);
    }
}
