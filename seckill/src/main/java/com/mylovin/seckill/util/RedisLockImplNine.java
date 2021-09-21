package com.mylovin.seckill.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
public class RedisLockImplNine implements RedisLock {
    @Autowired
    private RedisTemplate redisTemplate;
    private ThreadLocal<String> threadLocal = new ThreadLocal<>();
    private ThreadLocal<Integer> threadLocalInteger = new ThreadLocal<Integer>();

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
        //加锁成功后将计数器加1
        if (isLocked) {
            Integer count = threadLocalInteger.get() == null ? 0 : threadLocalInteger.get();
            threadLocalInteger.set(count++);
        }
        return isLocked;
    }

    @Override
    public void releaseLock(String key) {
        //当前线程中绑定的uuid与Redis中的uuid相同时，再执行删除锁的操作
        if (threadLocal.get().equals(redisTemplate.opsForValue().get(key))) {
            Integer count = threadLocalInteger.get();
            //计数器减为0时释放锁
            if (count == null || --count <= 0) {
                redisTemplate.delete(key);
            }
        }
    }
}
