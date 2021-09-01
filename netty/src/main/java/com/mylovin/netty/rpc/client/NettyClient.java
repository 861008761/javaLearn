package com.mylovin.netty.rpc.client;

import com.mylovin.netty.rpc.pojo.ResponseProto;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufEncoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32FrameDecoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32LengthFieldPrepender;

public class NettyClient {
    public void connect(String host, int port) {
        EventLoopGroup masterLoogGroup = new NioEventLoopGroup();
        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(masterLoogGroup)
                    .option(ChannelOption.TCP_NODELAY, true)
                    .channel(NioSocketChannel.class)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline().addLast(
                                    new ProtobufVarint32FrameDecoder());
                            ch.pipeline().addLast(
                                    new ProtobufDecoder(
                                            ResponseProto.Response
                                                    .getDefaultInstance()));
                            ch.pipeline().addLast(
                                    new ProtobufVarint32LengthFieldPrepender());
                            ch.pipeline().addLast(new ProtobufEncoder());
                            ch.pipeline().addLast("ClientHandler", new ClientHandler());
                        }
                    });
            ChannelFuture future = bootstrap.connect(host, port).sync();
            future.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            masterLoogGroup.shutdownGracefully();
        }

    }

    public static void main(String[] args) {
        String host = "127.0.0.1";
        int port = 9999;
        NettyClient client = new NettyClient();
        client.connect(host, port);
    }
}
