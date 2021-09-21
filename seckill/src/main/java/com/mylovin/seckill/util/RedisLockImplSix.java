package com.mylovin.seckill.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class RedisLockImplSix implements RedisLock {
    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public boolean tryLock(String key, long timeout, TimeUnit unit) {
        return redisTemplate.opsForValue().setIfAbsent(key, 1, timeout, unit);
    }

    @Override
    public void releaseLock(String key) {
        redisTemplate.delete(key);
    }
}
