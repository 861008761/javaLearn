package com.mylovin.netty.rpc.client;

import com.mylovin.netty.rpc.pojo.RequestProto;
import com.mylovin.netty.rpc.pojo.ResponseProto;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

public class ClientHandler extends ChannelHandlerAdapter {
    private static ChannelHandlerContext ctx;

    public static void sendMsg(RequestProto.Request msg) {
        ctx.writeAndFlush(msg);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        ClientHandler.ctx = ctx;
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ResponseProto.Response response = (ResponseProto.Response) msg;
        System.out.println(response.getReturnValue());
    }
}
