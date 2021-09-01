/*
 * Copyright 2013-2018 Lilinfeng.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.mylovin.netty.kafka.netty.server;

import com.mylovin.netty.kafka.netty.NettyConstant;
import com.mylovin.netty.kafka.netty.codec.NettyMessageDecoder;
import com.mylovin.netty.kafka.netty.codec.NettyMessageEncoder;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.timeout.ReadTimeoutHandler;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.IOException;

/**
 * @author Lilinfeng
 * @version 1.0
 * @date 2014年3月15日
 */
public class NettyServer {

    private static final Log LOG = LogFactory.getLog(NettyServer.class);

    public void bind() throws Exception {
        // 配置服务端的NIO线程组
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        ServerBootstrap b = new ServerBootstrap();
        b.group(bossGroup, workerGroup).channel(NioServerSocketChannel.class)
                // TCP参数 指定内核为此套接口排队的最大连接个数
                .option(ChannelOption.SO_BACKLOG, 100)
                // 两种handler的区别暂时不清楚
                .handler(new LoggingHandler(LogLevel.INFO))
                //
                //心跳超时的机制非常简单，直接利用Netty的ReadTimeoutHandler进行实现，
                // 当一定周期内(50s)没有接收到任何对方消息时，需要主动关闭链路。
                // 如果是客户端，则重新发起连接，如果是服务端，则释放资源，清除客户端登录缓存信息，等待服务器端重连。
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    public void initChannel(SocketChannel ch)
                            throws IOException {
                        ch.pipeline().addLast(
                                new NettyMessageDecoder(1024 * 1024, 4, 4));
                        ch.pipeline().addLast(new NettyMessageEncoder());
                        ch.pipeline().addLast("readTimeoutHandler",
                                new ReadTimeoutHandler(50));
                        ch.pipeline().addLast(new LoginAuthRespHandler());
                        ch.pipeline().addLast("HeartBeatHandler",
                                new HeartBeatRespHandler());
                        ch.pipeline().addLast("ServiceRespHandler",
                                new ServiceRespHandler());
                    }
                });

        // 绑定端口，同步等待成功
        b.bind(NettyConstant.REMOTEIP, NettyConstant.PORT).sync();
        LOG.info("Netty server start ok : "
                + (NettyConstant.REMOTEIP + " : " + NettyConstant.PORT));
    }

    public static void main(String[] args) throws Exception {
        new NettyServer().bind();
    }
}
