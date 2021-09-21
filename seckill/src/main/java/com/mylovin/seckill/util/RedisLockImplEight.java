package com.mylovin.seckill.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
public class RedisLockImplEight implements RedisLock {
    @Autowired
    private RedisTemplate redisTemplate;
    private ThreadLocal<String> threadLocal = new ThreadLocal<String>();

    @Override
    public boolean tryLock(String key, long timeout, TimeUnit unit) {
        Boolean isLocked = false;
        if (threadLocal.get() == null) {
            String uuid = UUID.randomUUID().toString();
            threadLocal.set(uuid);
            isLocked = redisTemplate.opsForValue().setIfAbsent(key, uuid, timeout, unit);
        } else {
            isLocked = true;
        }
        return isLocked;
    }

    @Override
    public void releaseLock(String key) {
        //当前线程中绑定的uuid与Redis中的uuid相同时，再执行删除锁的操作
        if (threadLocal.get().equals(redisTemplate.opsForValue().get(key))) {
            redisTemplate.delete(key);
        }
    }
}
