package com.mylovin.netty.nettycode.pipe;

import com.mylovin.netty.nettycode.pipe.handler.CustomHandlerOne;
import com.mylovin.netty.nettycode.pipe.handler.CustomHandlerTwo;
import com.mylovin.netty.nettycode.pipe.pipeline.DefaultPipeline;

/**
 * 解析：
 * 1、pipeline就像一个双向链表，放着head和tail两个链表节点，提供addLast和addFirst等方法，向链表中添加数据
 * 2、context是链表的一个个的node节点，包含prev和next指针，以及存放的实际的数据，即handler
 * 3、pipeline的fireChannelRead等方法像是遍历链表节点的作用
 * 4、invoker是netty中使用到的，在这里的作用其实没有体现的很明显
 */
public class Main {
    public static void main(String[] args) {
        DefaultPipeline pipeline = new DefaultPipeline();
        pipeline.addLast(new CustomHandlerOne());
        pipeline.addLast(new CustomHandlerTwo());
        pipeline.fireChannelRead();
    }
}
