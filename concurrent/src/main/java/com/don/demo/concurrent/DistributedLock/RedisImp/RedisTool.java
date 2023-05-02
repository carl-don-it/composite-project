package com.don.demo.concurrent.DistributedLock.RedisImp;

import redis.clients.jedis.Jedis;

import java.util.Collections;

/**
 * todo
 *
 * @author Carl Don
 * @version V1.0
 * @date 2020年02月16日 下午 7:27
 */
public class RedisTool {
    private static final String LOCK_SUCCESS = "OK";
    private static final String SET_IF_NOT_EXIST = "NX"; //当key不存在时，我们进行set操作；若key已经存在，则不做任何操作；
    private static final String SET_WITH_EXPIRE_TIME = "PX";//给这个key加一个过期的设置，具体时间由expireTime决定。

    /**
     * 尝试获取分布式锁
     *
     * @param jedis      Redis客户端
     * @param lockKey    锁
     * @param requestId  请求标识，也就是value，由持有锁的线程自己保存，标识着锁的持有者，只有持有者才可以解锁
     * @param expireTime 超期时间
     * @return 是否获取成功
     */
    public static boolean tryGetDistributedLock(Jedis jedis, String lockKey, String requestId, int expireTime) {

        //1. 当前没有锁（key不存在），那么就进行加锁操作，并对锁设置个有效期，同时value表示加锁的客户端。
        String result = jedis.set(lockKey, requestId, SET_IF_NOT_EXIST, SET_WITH_EXPIRE_TIME, expireTime);

        if (LOCK_SUCCESS.equals(result)) {
            return true;
        }

        //2. 已有锁存在，不做任何操作。
        return false;

    }

    private static final Long RELEASE_SUCCESS = 1L;


    /**
     * 释放分布式锁
     *
     * @param jedis     Redis客户端
     * @param lockKey   锁
     * @param requestId 请求标识
     * @return 是否释放成功
     */
    public static boolean releaseDistributedLock(Jedis jedis, String lockKey, String requestId) {

        //首先获取锁对应的value值，检查是否与requestId相等，如果相等则删除锁（解锁）。
        //在eval命令执行Lua代码的时候，Lua代码将被当成一个命令去执行，并且直到eval命令执行完成，Redis才会执行其他命令。
        String script = "if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end";
        Object result = jedis.eval(script, Collections.singletonList(lockKey), Collections.singletonList(requestId));

        if (RELEASE_SUCCESS.equals(result)) {
            return true;
        }
        return false;

    }

}
