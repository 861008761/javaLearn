package com.mylovin.netty.nettycode.pipe.invoker;

import com.mylovin.netty.nettycode.pipe.context.Context;

public class DefaultInvoker {
    public void invokeChannelRead(Context context) {
        InvokerUtil.invokeChannelReadNow(context);
    }
}
