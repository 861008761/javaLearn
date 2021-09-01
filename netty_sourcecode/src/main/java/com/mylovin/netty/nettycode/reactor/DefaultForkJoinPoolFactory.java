package com.mylovin.netty.nettycode.reactor;

import io.netty.util.concurrent.FastThreadLocalAccess;
import io.netty.util.internal.InternalThreadLocalMap;
import io.netty.util.internal.chmv8.ForkJoinPool;
import io.netty.util.internal.chmv8.ForkJoinPool.ForkJoinWorkerThreadFactory;
import io.netty.util.internal.chmv8.ForkJoinWorkerThread;

import java.util.concurrent.atomic.AtomicInteger;

public class DefaultForkJoinPoolFactory {
    private final String namePrefix = "testReactor";
    private static final AtomicInteger executorId = new AtomicInteger();

    public ForkJoinPool newExecutorService(int parallelism) {
        ForkJoinWorkerThreadFactory threadFactory =
                new DefaultForkJoinWorkerThreadFactory(namePrefix + '-' + executorId.getAndIncrement());

        return new ForkJoinPool(parallelism, threadFactory, DefaultUncaughtExceptionHandler.INSTANCE, true);
    }

    private static final class DefaultUncaughtExceptionHandler implements Thread.UncaughtExceptionHandler {

        private static final DefaultUncaughtExceptionHandler INSTANCE = new DefaultUncaughtExceptionHandler();

        @Override
        public void uncaughtException(Thread t, Throwable e) {
            System.out.println("error");
        }
    }

    private static final class DefaultForkJoinWorkerThreadFactory implements ForkJoinWorkerThreadFactory {

        private final AtomicInteger idx = new AtomicInteger();
        private final String namePrefix;

        DefaultForkJoinWorkerThreadFactory(String namePrefix) {
            this.namePrefix = namePrefix;
        }

        @Override
        public ForkJoinWorkerThread newThread(ForkJoinPool pool) {
            // Note: The ForkJoinPool will create these threads as daemon threads.
            ForkJoinWorkerThread thread = new DefaultForkJoinWorkerThread(pool);
            thread.setName(namePrefix + '-' + idx.getAndIncrement());
            thread.setPriority(Thread.MAX_PRIORITY);
            return thread;
        }
    }

    private static final class DefaultForkJoinWorkerThread
            extends ForkJoinWorkerThread implements FastThreadLocalAccess {

        private InternalThreadLocalMap threadLocalMap;

        DefaultForkJoinWorkerThread(ForkJoinPool pool) {
            super(pool);
        }

        @Override
        public InternalThreadLocalMap threadLocalMap() {
            return threadLocalMap;
        }

        @Override
        public void setThreadLocalMap(InternalThreadLocalMap threadLocalMap) {
            this.threadLocalMap = threadLocalMap;
        }
    }
}
