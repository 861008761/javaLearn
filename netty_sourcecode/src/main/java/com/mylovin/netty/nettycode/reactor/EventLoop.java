package com.mylovin.netty.nettycode.reactor;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class EventLoop extends Executor {
    public EventLoop() {
        super();
    }

    @Override
    public void run() {
        while (!queue.isEmpty()) {
            Runnable runnable = queue.poll();
            if (null != runnable) {
                //注意 这里的任务执行方式是runnable调用run方法
                runnable.run();
            }
        }

        try {
            int x = new Random().nextInt(5);
            TimeUnit.SECONDS.sleep(x);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        startExecution();
    }

    @Override
    public void execute(Runnable command) {
        super.execute(command);
    }

    public void printHello() {
        System.out.println("eventLoop: hello!");
    }
}
