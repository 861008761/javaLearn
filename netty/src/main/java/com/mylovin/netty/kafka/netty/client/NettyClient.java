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
package com.mylovin.netty.kafka.netty.client;

import com.mylovin.netty.kafka.netty.NettyConstant;
import com.mylovin.netty.kafka.netty.codec.NettyMessageDecoder;
import com.mylovin.netty.kafka.netty.codec.NettyMessageEncoder;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.timeout.ReadTimeoutHandler;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.net.InetSocketAddress;
import java.util.Scanner;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author Lilinfeng
 * @version 1.0
 * @date 2014年3月15日
 */
public class NettyClient {

    private static final Log LOG = LogFactory.getLog(NettyClient.class);

    private ScheduledExecutorService executor = Executors
            .newScheduledThreadPool(1);

    EventLoopGroup group = new NioEventLoopGroup();

    public void connect(int port, String host) throws Exception {

        // 配置客户端NIO线程组
        try {
            Bootstrap b = new Bootstrap();
            b.group(group).channel(NioSocketChannel.class)
                    .option(ChannelOption.TCP_NODELAY, true)
                    //心跳超时的机制非常简单，直接利用Netty的ReadTimeoutHandler进行实现，
                    // 当一定周期内(50s)没有接收到任何对方消息时，需要主动关闭链路。
                    // 如果是客户端，则重新发起连接，如果是服务端，则释放资源，清除客户端登录缓存信息，等待服务器端重连。
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        public void initChannel(SocketChannel ch)
                                throws Exception {
                            ch.pipeline().addLast(
                                    new NettyMessageDecoder(1024 * 1024, 4, 4));
                            ch.pipeline().addLast("MessageEncoder",
                                    new NettyMessageEncoder());
                            ch.pipeline().addLast("readTimeoutHandler",
                                    new ReadTimeoutHandler(50));
                            ch.pipeline().addLast("LoginAuthHandler",
                                    new LoginAuthReqHandler());
                            ch.pipeline().addLast("HeartBeatHandler",
                                    new HeartBeatReqHandler());
                            ch.pipeline().addLast("ServiceReqHandler",
                                    new ServiceReqHandler(queue));
                        }
                    });
            // 发起异步连接操作
            ChannelFuture future = b.connect(
                    new InetSocketAddress(host, port),
                    new InetSocketAddress(NettyConstant.LOCALIP,
                            NettyConstant.LOCAL_PORT)).sync();
            // 当对应的channel关闭的时候，就会返回对应的channel。
            // Returns the ChannelFuture which will be notified when this channel is closed. This method always returns the same future instance.
            future.channel().closeFuture().sync();
        } finally {
            // 所有资源释放完成之后，清空资源，再次发起重连操作
            executor.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        TimeUnit.SECONDS.sleep(1);
                        try {
                            connect(NettyConstant.PORT, NettyConstant.REMOTEIP);// 发起重连操作
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }

    private static LinkedBlockingQueue queue = new LinkedBlockingQueue();

    /**
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        Task task = new Task();
        new Thread(task).start();
        LOG.info("started!");

        NettyClient client = new NettyClient();
        client.connect(NettyConstant.PORT, NettyConstant.REMOTEIP);
    }

    private static class Task implements Runnable {
        @Override
        public void run() {
            while (true) {
                System.out.print("请输入:");
                String msg = new Scanner(System.in).nextLine();
                queue.offer(msg);
                System.out.println("输入完成");
            }
        }
    }

}
