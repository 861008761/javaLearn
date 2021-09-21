package com.mylovin.seckill.util;

import java.util.concurrent.TimeUnit;

public interface RedisLock {
    //加锁操作
    boolean tryLock(String key, long timeout, TimeUnit unit);
    //解锁操作
    void releaseLock(String key);
}
