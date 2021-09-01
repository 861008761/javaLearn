package com.mylovin.netty.rpc.server;

import com.mylovin.netty.rpc.pojo.RequestProto;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufEncoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32FrameDecoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32LengthFieldPrepender;

public class NettyServer {
    public void connect(int port) {
        EventLoopGroup masterLoogGroup = new NioEventLoopGroup();
        EventLoopGroup slaveLoogGroup = new NioEventLoopGroup();
        ServerBootstrap bootstrap = new ServerBootstrap();
        bootstrap.group(masterLoogGroup, slaveLoogGroup)
                .option(ChannelOption.SO_BACKLOG, 1024)
                .channel(NioServerSocketChannel.class)
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        ch.pipeline().addLast(
                                new ProtobufVarint32FrameDecoder());
                        ch.pipeline().addLast(
                                new ProtobufDecoder(
                                        RequestProto.Request
                                                .getDefaultInstance()));
                        ch.pipeline().addLast(
                                new ProtobufVarint32LengthFieldPrepender());
                        ch.pipeline().addLast(new ProtobufEncoder());
                        ch.pipeline().addLast("ServerHandler", new ServerHandler());
                    }
                });
        try {
            ChannelFuture future = bootstrap.bind(port).sync();
            future.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            masterLoogGroup.shutdownGracefully();
            slaveLoogGroup.shutdownGracefully();
        }

    }

    public static void main(String[] args) {
        int port = 9999;
        NettyServer server = new NettyServer();
        server.connect(port);
    }
}
