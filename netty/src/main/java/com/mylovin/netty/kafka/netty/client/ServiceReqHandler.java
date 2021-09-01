package com.mylovin.netty.kafka.netty.client;

import com.mylovin.netty.kafka.netty.MessageType;
import com.mylovin.netty.kafka.netty.struct.Header;
import com.mylovin.netty.kafka.netty.struct.NettyMessage;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.concurrent.LinkedBlockingQueue;

public class ServiceReqHandler extends ChannelHandlerAdapter {
    private static final Log LOG = LogFactory.getLog(ServiceReqHandler.class);
    private LinkedBlockingQueue queue;

    public ServiceReqHandler(LinkedBlockingQueue queue) {
        this.queue = queue;
    }

    private NettyMessage buildServiceReq(String msg) {
        NettyMessage message = new NettyMessage();
        Header header = new Header();
        header.setType(MessageType.SERVICE_REQ.value());
        message.setHeader(header);
        message.setBody(msg);
        LOG.info("success to send!");
        return message;
    }

    /**
     * 初始化业务线程必须的参数
     *
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        ServiceTask task = new ServiceTask(ctx);
        new Thread(task).start();
    }

    private class ServiceTask implements Runnable {
        private ChannelHandlerContext ctx;

        public ServiceTask(ChannelHandlerContext ctx) {
            this.ctx = ctx;
        }

        @Override
        public void run() {
            LOG.info("启动业务数据处理线程");
            while (true) {
                String msg = null;
                try {
                    msg = (String) queue.take();
                    LOG.info(msg);
                    if (msg != null) {
                        NettyMessage message = buildServiceReq(msg);
                        ctx.writeAndFlush(message);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
