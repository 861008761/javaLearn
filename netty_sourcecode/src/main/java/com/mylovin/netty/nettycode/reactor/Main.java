package com.mylovin.netty.nettycode.reactor;

import java.util.Random;
import java.util.concurrent.CountDownLatch;

public class Main {
    public static void main(String[] args) {
        CountDownLatch latch = new CountDownLatch(1);
        // 实例化父类的runnable对象
        final EventLoop eventLoop = new EventLoop();
        eventLoop.execute(null);
        // 将实例化的runnable对象放到forkJoinPool线程池中调用，即启动eventLoop的执行线程
        eventLoop.startExecution();

        for (int i = 0; i < 1000; i++) {
            int x = new Random().nextInt(4);
            try {
                Thread.sleep(x * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            eventLoop.addTask(new Runnable() {
                @Override
                public void run() {
                    int x = new Random().nextInt(10);
                    try {
                        Thread.sleep(x * 100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("runnable task do something...");

                    if (eventLoop.inEventLoop()) {
                        System.out.println("相同线程");
                        eventLoop.printHello();
                    } else {
                        // 加入队列 由线程池统一调度
                        System.out.println("不同线程");
                        eventLoop.execute(new Runnable() {
                            @Override
                            public void run() {
                                eventLoop.printHello();
                            }
                        });
                    }
                    try {
                        Thread.sleep(2100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
