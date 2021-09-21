package com.mylovin.java.pubsub.simple;

public class SimpleMain {
    private int count = 0;
    private Object signal = new Object();

    class Provider implements Runnable {
        @Override
        public void run() {
            try {
                synchronized (signal) {
                    while (true) {
                        if (count >= 1) {
                            // 先通知其他等待锁的线程来竞争锁
                            signal.notifyAll();
                            // 把当前线程放入等待队列
                            signal.wait();
                        }
                        count++;
                        System.out.println("生产者生产");
                        Thread.sleep(500);
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    class Consumer implements Runnable {
        @Override
        public void run() {
            try {
                synchronized (signal) {
                    while (true) {
                        if (count <= 0) {
                            // 通知其他线程来竞争锁
                            signal.notifyAll();
                            // 消费线程进入等待队列
                            signal.wait();
                        }
                        count--;
                        System.out.println("消费者消费");
                        Thread.sleep(500);
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void main0() {
        new Thread(new Provider()).start();
        new Thread(new Consumer()).start();
    }

    public static void main(String[] args) {
        SimpleMain main = new SimpleMain();
        main.main0();
    }
}
