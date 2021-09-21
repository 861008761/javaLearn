package com.mylovin.seckill.controller;

import com.mylovin.seckill.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.concurrent.TimeUnit;

@RestController
public class SeckillController {
    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private RedisLockImplSix redisLockImplSix;

    @Autowired
    private RedisLockImplSeven redisLockImplSeven;

    @Autowired
    private RedisLockImplEight redisLockImplEight;

    @Autowired
    private RedisLockImplNine redisLockImplNine;

    private static final String STOCK = "stock";
    private static final String LOCK = "lock";

    @PostConstruct
    public void init() {
        redisTemplate.opsForValue().set(STOCK, 100);
    }

    /**
     * 第一种方式
     * 分析：使用jmeter测试，多线程环境下，十分容易超卖
     *
     * @return
     */
    @RequestMapping("/submitOne")
    public String submitOne() {
        int count = (int) redisTemplate.opsForValue().get(STOCK);
        if (count <= 0) {
            return "抢购失败，剩余：" + count;
        } else {
            count--;
            redisTemplate.opsForValue().set(STOCK, count);
            System.out.println("商品剩余数量: " + count + ", 当前线程：" + Thread.currentThread());
            return "抢购成功，剩余：" + count;
        }
    }

    /**
     * 第二种方式
     * 分析：使用redis的分布式锁，理论上可行。
     * 但是存在一个问题：如果在某个线程获取锁之后，此时线程崩溃，导致锁不释放，其他线程始终无法获取锁。
     *
     * @return
     */
    @RequestMapping("/submitTwo")
    public String submitTwo() {
        String msg = "";
        // 竞争锁
        boolean flag = redisTemplate.opsForValue().setIfAbsent(LOCK, 1); // 对应setnx命令，set if not exist
        if (!flag) {
            return "抢购失败，未竞争到锁";
        }

        int count = (int) redisTemplate.opsForValue().get(STOCK);
        if (count > 0) {
            count--;
            redisTemplate.opsForValue().set(STOCK, count);
            System.out.println("商品剩余数量: " + count + ", 当前线程：" + Thread.currentThread());
            msg = "抢购成功，剩余：" + count;
        } else {
            msg = "抢购失败，剩余：" + count;
        }
        // 释放锁
        redisTemplate.delete(LOCK);
        return msg;
    }

    /**
     * 第三种
     * 使用try catch finally
     * 尽力保证最终释放锁
     * 这种还是会有问题，如果没有优雅退出机制，没有到finally块中，还是会无法释放锁
     */
    @RequestMapping("/submitThree")
    public String submitThree() {
        String msg = "";
        // 竞争锁
        boolean flag = redisTemplate.opsForValue().setIfAbsent(LOCK, 1); // 对应setnx命令，set if not exist
        if (!flag) {
            return "抢购失败，未竞争到锁";
        }
        try {
            int count = (int) redisTemplate.opsForValue().get(STOCK);
            if (count > 0) {
                count--;
                redisTemplate.opsForValue().set(STOCK, count);
                System.out.println("商品剩余数量: " + count + ", 当前线程：" + Thread.currentThread());
                msg = "抢购成功，剩余：" + count;
            } else {
                msg = "抢购失败，剩余：" + count;
            }
        } finally {
            // 释放锁
            redisTemplate.delete(LOCK);
        }
        return msg;
    }

    /**
     * 第四种方式
     * 使用redis超时机制，expire(LOCK, 1, timeout)
     * 设置锁之后，给一个超时时间
     * 存在的问题：如果设置锁之后，设置超时时间之前线程崩溃，就会存在问题
     */
    @RequestMapping("/submitFour")
    public String submitFour() {
        String msg = "";
        // 竞争锁
        boolean flag = redisTemplate.opsForValue().setIfAbsent(LOCK, 1); // 对应setnx命令，set if not exist
        if (!flag) {
            return "抢购失败，未竞争到锁";
        }
        try {
            redisTemplate.expire(LOCK, 30, TimeUnit.SECONDS); //设置超时
            int count = (int) redisTemplate.opsForValue().get(STOCK);
            if (count > 0) {
                count--;
                redisTemplate.opsForValue().set(STOCK, count);
                System.out.println("商品剩余数量: " + count + ", 当前线程：" + Thread.currentThread());
                msg = "抢购成功，剩余：" + count;
            } else {
                msg = "抢购失败，剩余：" + count;
            }
        } finally {
            // 释放锁
            redisTemplate.delete(LOCK);
        }
        return msg;
    }

    /**
     * 第五种方式
     * 使用redis超时机制，setnx
     * 设置锁之后，同时给一个超时时间
     * 存在的问题：上述的代码基本上在功能角度解决了程序的死锁问题，那么，上述程序真的就完美了吗？
     * 在我们开发公共的系统组件时，比如我们这里说的分布式锁，我们肯定会抽取一些公共的类来完成相应的功能来供系统使用。
     * 这里，假设我们定义了一个RedisLock接口，如下所示。
     */
    @RequestMapping("/submitFive")
    public String submitFive() {
        String msg = "";
        // 竞争锁
        boolean flag = redisTemplate.opsForValue().setIfAbsent(LOCK, 1, 30, TimeUnit.SECONDS); // 对应setnx命令，set if not exist
        if (!flag) {
            return "抢购失败，未竞争到锁";
        }
        try {
            int count = (int) redisTemplate.opsForValue().get(STOCK);
            if (count > 0) {
                count--;
                redisTemplate.opsForValue().set(STOCK, count);
                System.out.println("商品剩余数量: " + count + ", 当前线程：" + Thread.currentThread());
                msg = "抢购成功，剩余：" + count;
            } else {
                msg = "抢购失败，剩余：" + count;
            }
        } finally {
            // 释放锁
            redisTemplate.delete(LOCK);
        }
        return msg;
    }

    /**
     * 第六种
     * 在我们开发公共的系统组件时，比如我们这里说的分布式锁，我们肯定会抽取一些公共的类来完成相应的功能来供系统使用。
     * <p>
     * 定义了一个RedisLock接口，使用RedisLockImpl类实现RedisLock接口，提供具体的加锁和解锁实现
     * <p>
     * 存在的问题：
     * 在开发集成的角度来说，当一个线程从上到下执行时，首先对程序进行加锁操作，然后执行业务代码，执行完成后，再进行释放锁的操作。
     * 理论上，加锁和释放锁时，操作的Redis Key都是一样的。
     * <p>
     * 但是，如果其他开发人员在编写代码时，并没有调用tryLock()方法，而是直接调用了releaseLock()方法，
     * 并且他调用releaseLock()方法传递的key与你调用tryLock()方法传递的key是一样的。
     * 那此时就会出现问题了，他在编写代码时，硬生生的将你加的锁释放了！！！
     *
     * @return
     */
    @RequestMapping("/submitSix")
    public String submitSix() {
        String msg = "";
        // 竞争锁
        boolean flag = redisLockImplSix.tryLock(LOCK, 30, TimeUnit.SECONDS); // 对应setnx命令，set if not exist
        if (!flag) {
            return "抢购失败，未竞争到锁";
        }
        try {
            int count = (int) redisTemplate.opsForValue().get(STOCK);
            if (count > 0) {
                count--;
                redisTemplate.opsForValue().set(STOCK, count);
                System.out.println("商品剩余数量: " + count + ", 当前线程：" + Thread.currentThread());
                msg = "抢购成功，剩余：" + count;
            } else {
                msg = "抢购失败，剩余：" + count;
            }
        } finally {
            // 释放锁 此处，如果其他线程直接调用了release，错误的释放锁，会造成混乱
            redisLockImplSix.releaseLock(LOCK);
        }
        return msg;
    }

    /**
     * 第七种方式
     * 每个线程加锁时，用threadLock变量记录下uuid，释放锁的时候校验，防止不是加锁的线程释放其他线程的锁
     * 存在的问题：可重入性问题，如果线程A对LOCK对象加锁了，A调用了线程B，线程B同样要对该资源加锁会失败
     *
     * @return
     */
    @RequestMapping("/submitSeven")
    public String submitSeven() {
        String msg = "";
        // 竞争锁
        boolean flag = redisLockImplSeven.tryLock(LOCK, 30, TimeUnit.SECONDS); // 对应setnx命令，set if not exist
        if (!flag) {
            return "抢购失败，未竞争到锁";
        }
        try {
            int count = (int) redisTemplate.opsForValue().get(STOCK);
            if (count > 0) {
                count--;
                redisTemplate.opsForValue().set(STOCK, count);
                System.out.println("商品剩余数量: " + count + ", 当前线程：" + Thread.currentThread());
                msg = "抢购成功，剩余：" + count;
            } else {
                msg = "抢购失败，剩余：" + count;
            }
        } finally {
            // 释放锁 此处，如果其他线程直接调用了release，错误的释放锁，会造成混乱
            redisLockImplSeven.releaseLock(LOCK);
        }
        return msg;
    }

    /**
     * 第八种方式
     * 尝试解决可重入性问题，如果线程A对LOCK对象加锁了，A调用了线程B，线程B同样要对该资源加锁时，判断是否是已经加过了，加过了就直接返回
     * 存在的问题：不知道加了几次可重入锁，如果有一个线程释放锁，就会造成所有线程都失去了锁
     * <p>
     * 假设我们提交订单的方法中，首先使用RedisLock接口对代码块添加了分布式锁，在加锁后的代码中调用了服务A，
     * 而服务A中也存在调用RedisLock接口的加锁和解锁操作。而多次调用RedisLock接口的加锁操作时，
     * 只要之前的锁没有失效，则会直接返回true，表示成功获取锁。也就是说，无论调用加锁操作多少次，
     * 最终只会成功加锁一次。而执行完服务A中的逻辑后，在服务A中调用RedisLock接口的解锁方法，
     * 此时，会将当前线程所有的加锁操作获得的锁全部释放掉。
     *
     * @return
     */
    @RequestMapping("/submitEight")
    public String submitEight() {
        String msg = "";
        // 竞争锁
        boolean flag = redisLockImplEight.tryLock(LOCK, 30, TimeUnit.SECONDS); // 对应setnx命令，set if not exist
        if (!flag) {
            return "抢购失败，未竞争到锁";
        }
        try {
            int count = (int) redisTemplate.opsForValue().get(STOCK);
            if (count > 0) {
                count--;
                redisTemplate.opsForValue().set(STOCK, count);
                System.out.println("商品剩余数量: " + count + ", 当前线程：" + Thread.currentThread());
                msg = "抢购成功，剩余：" + count;
            } else {
                msg = "抢购失败，剩余：" + count;
            }
        } finally {
            // 释放锁 此处，如果其他线程直接调用了release，错误的释放锁，会造成混乱
            redisLockImplEight.releaseLock(LOCK);
        }
        return msg;
    }

    @RequestMapping("/submitNine")
    public String submitNine() {
        String msg = "";
        // 竞争锁
        boolean flag = redisLockImplNine.tryLock(LOCK, 30, TimeUnit.SECONDS); // 对应setnx命令，set if not exist
        if (!flag) {
            return "抢购失败，未竞争到锁";
        }
        try {
            int count = (int) redisTemplate.opsForValue().get(STOCK);
            if (count > 0) {
                count--;
                redisTemplate.opsForValue().set(STOCK, count);
                System.out.println("商品剩余数量: " + count + ", 当前线程：" + Thread.currentThread());
                msg = "抢购成功，剩余：" + count;
            } else {
                msg = "抢购失败，剩余：" + count;
            }
        } finally {
            // 释放锁 此处，如果其他线程直接调用了release，错误的释放锁，会造成混乱
            redisLockImplNine.releaseLock(LOCK);
        }
        return msg;
    }
}
