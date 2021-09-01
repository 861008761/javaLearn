package com.mylovin.netty.nettycode.pipe.pipeline;

import com.mylovin.netty.nettycode.pipe.context.Context;
import com.mylovin.netty.nettycode.pipe.handler.Handler;

public class DefaultPipeline {
    private Context headContext;
    private Context tailContext;

    public DefaultPipeline() {
        this.headContext = new HeadContext();
        this.tailContext = new TailContext();
        this.headContext.next = this.tailContext;
        this.tailContext.prev = this.headContext;
    }

    public void addLast(Handler handler) {
        Context ctx = new Context();
        ctx.setHandler(handler);

        Context tmp = this.tailContext.prev;
        tmp.next = ctx;
        ctx.prev = tmp;
        ctx.next = this.tailContext;
        this.tailContext.prev = ctx;
    }

    public void fireChannelRead() {
        this.headContext.fireChannelRead();
    }

    private class HeadContext extends Context {
        public HeadContext() {
            super();
            this.handler = new Handler() {
                @Override
                public void fireChannelRead(Context context) {
                    context.fireChannelRead();
                }
            };
        }
    }

    private class TailContext extends Context {
        public TailContext() {
            super();
            this.handler = new Handler() {
                @Override
                public void fireChannelRead(Context context) {
                    context.fireChannelRead();
                }
            };
        }
    }
}
