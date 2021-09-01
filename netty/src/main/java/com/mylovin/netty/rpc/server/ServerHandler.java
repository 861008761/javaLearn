package com.mylovin.netty.rpc.server;

import com.alibaba.fastjson.JSON;
import com.mylovin.netty.rpc.MessageSend;
import com.mylovin.netty.rpc.pojo.RequestProto;
import com.mylovin.netty.rpc.pojo.ResponseProto;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import org.apache.log4j.Logger;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class ServerHandler extends ChannelHandlerAdapter {
    private static final Logger LOG = Logger.getLogger(ServerHandler.class);
    //应该存储类和对象的对应关系还是实现类名呢？感觉是单例的 否则客户端多次调用会产生多个对象，占用内存
    private Map<String, Object> implementMap = new HashMap<>(1);

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        implementMap.put("com.phei.netty.rpc.Basic", new MessageSend());
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        RequestProto.Request request = (RequestProto.Request) msg;
        String clazz = request.getClazz();
        String mtd = request.getMethod();
        Class<?>[] paramTypes = JSON.parseObject(request.getParamTypes(), Class[].class);
        Object[] args = JSON.parseObject(request.getArgs(), Object[].class);

        Object bean = implementMap.get(clazz);
        Method method = bean.getClass().getMethod(mtd, paramTypes);
        Object value = method.invoke(bean, args);

        System.out.println("Server receive request from remote client! msg: " + request);

        ResponseProto.Response.Builder builder = ResponseProto.Response.newBuilder();
        builder.setReturnValue(JSON.toJSONString(value));
        ctx.writeAndFlush(builder.build());
    }
}
