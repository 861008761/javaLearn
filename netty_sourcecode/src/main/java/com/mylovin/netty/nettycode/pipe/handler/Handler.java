package com.mylovin.netty.nettycode.pipe.handler;

import com.mylovin.netty.nettycode.pipe.context.Context;

public interface Handler {
    void fireChannelRead(Context context);
}
