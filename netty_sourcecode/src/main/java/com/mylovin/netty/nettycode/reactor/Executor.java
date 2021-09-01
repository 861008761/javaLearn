package com.mylovin.netty.nettycode.reactor;

import io.netty.util.internal.PlatformDependent;
import io.netty.util.internal.chmv8.ForkJoinPool;

import java.util.List;
import java.util.Queue;
import java.util.concurrent.AbstractExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

public abstract class Executor extends AbstractExecutorService {
    public static final AtomicIntegerFieldUpdater<Executor> STATE_UPDATER;
    private static final AtomicReferenceFieldUpdater<Executor, Thread> THREAD_UPDATER;
    private volatile Thread thread;
    private volatile int state = 1;
    private static final Runnable NOOP_TASK = new Runnable() {
        @Override
        public void run() {
            // Do nothing.
        }
    };
    private static final long SCHEDULE_PURGE_INTERBAL = 1;
    private Runnable runnable;
    protected ForkJoinPool forkJoinPool = new DefaultForkJoinPoolFactory().newExecutorService(24);
    protected Queue<Runnable> queue = new LinkedBlockingQueue<>(100);

    static {
        AtomicIntegerFieldUpdater<Executor> updater =
                PlatformDependent.newAtomicIntegerFieldUpdater(Executor.class, "state");
        if (updater == null) {
            updater = AtomicIntegerFieldUpdater.newUpdater(Executor.class, "state");
        }
        STATE_UPDATER = updater;

        AtomicReferenceFieldUpdater<Executor, Thread> refUpdater =
                PlatformDependent.newAtomicReferenceFieldUpdater(Executor.class, "thread");
        if (refUpdater == null) {
            refUpdater = AtomicReferenceFieldUpdater.newUpdater(
                    Executor.class, Thread.class, "thread");
        }
        THREAD_UPDATER = refUpdater;
    }

    public Executor() {
        // 关键之处：因为调用这个线程的是forkJoinPool线程池，因此currentThread线程是forkJoinPool中的某个线程
        this.runnable = () -> {
            Executor.this.updateThread(Thread.currentThread());
            Executor.this.run();
        };
    }

    private void updateThread(Thread t) {
        THREAD_UPDATER.lazySet(this, t);
    }

    abstract void run();

    public void startExecution() {
        this.updateThread(null);
        forkJoinPool.execute(runnable);
    }

    public boolean inEventLoop() {
        return inEventLoop(Thread.currentThread());
    }

    public boolean inEventLoop(Thread thread) {
        return this.thread == thread;
    }

    @Override
    public void execute(Runnable command) {
        boolean inEventLoop = this.inEventLoop();
        if (inEventLoop) {
            this.queue.add(command);
        } else {
            this.startExecution();
            if (null != command) {
                this.queue.add(command);
            }
        }
    }

    public void addTask(Runnable command) {
        if (null != command) {
            this.queue.add(command);
        }
    }

    @Override
    public void shutdown() {

    }

    @Override
    public List<Runnable> shutdownNow() {
        return null;
    }



    @Override
    public boolean isShutdown() {
        return false;
    }

    @Override
    public boolean isTerminated() {
        return false;
    }

    @Override
    public boolean awaitTermination(long timeout, TimeUnit unit) throws InterruptedException {
        return false;
    }
}
