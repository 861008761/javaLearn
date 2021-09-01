package com.mylovin.netty.kafka.netty.server;

import com.mylovin.netty.kafka.netty.struct.NettyMessage;
import com.mylovin.netty.kafka.producer.Producer;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class ServiceRespHandler extends ChannelHandlerAdapter {
    private static final Log LOG = LogFactory.getLog(ServiceRespHandler.class);

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        NettyMessage message = (NettyMessage) msg;
        String body = (String) message.getBody();
        LOG.info(body);
        Producer producer = new Producer();
        producer.produce(body);
    }
}
