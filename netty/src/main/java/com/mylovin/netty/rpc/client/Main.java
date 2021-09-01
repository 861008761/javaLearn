package com.mylovin.netty.rpc.client;

import com.mylovin.netty.rpc.Basic;
import com.mylovin.netty.rpc.MessageProcessProxy;
import org.apache.log4j.Logger;

import java.lang.reflect.Proxy;

public class Main {
    private static final Logger LOG = Logger.getLogger(Main.class);

    public void send() {
        // 必须使用接口转换类型的时候
        Basic msgSend = (Basic) Proxy.newProxyInstance(this.getClass().getClassLoader(), new Class[]{Basic.class}, new MessageProcessProxy(Basic.class));
        // 使用具体类的时候会出问题
        // Basic messageSend1 = (MessageSend) Proxy.newProxyInstance(messageSend.getClass().getClassLoader(), messageSend.getClass().getInterfaces(), new MessageProcessProxy(messageSend));
        //System.out.println(msgSend.send());
        LOG.info(msgSend.send());
        LOG.info(msgSend.send("hello, world!"));
    }

    public static void main(String[] args) {
        Runnable client = new Runnable() {
            @Override
            public void run() {
                String host = "127.0.0.1";
                int port = 9999;
                NettyClient client = new NettyClient();
                client.connect(host, port);
            }
        };
        new Thread(client).start();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Main main = new Main();
        main.send();
    }
}
