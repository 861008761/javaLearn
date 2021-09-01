package com.mylovin.netty.nettycode.pipe.context;

import com.mylovin.netty.nettycode.pipe.handler.Handler;
import com.mylovin.netty.nettycode.pipe.invoker.DefaultInvoker;

public class Context {
    public Context next;
    public Context prev;
    public Handler handler;
    public DefaultInvoker invoker;

    DefaultInvoker invoker() {
        return this.invoker == null ? new DefaultInvoker() : this.invoker;
    }

    public void fireChannelRead() {
        Context next = this.next;
        if (null != next) {
            next.invoker().invokeChannelRead(next);
        }
    }

    public Handler getHandler() {
        return handler;
    }

    public void setHandler(Handler handler) {
        this.handler = handler;
    }
}
