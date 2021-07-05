package com.chat.test.sjj.redis;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * @PROJECT_NAME: sjj
 * @DESCRIPTION:
 * @USER: jingjie.shen
 * @DATE: 2021/7/2 10:44
 */
@Component
public class RedisUtil {

    @Resource
    private RedisTemplate<String, Object> redisTemplate;


  /*  *//**
     * 获取hostname
     *//*
    public String getHostName() {
        RedisConnectionFactory connectionFactory = redisTemplate.getConnectionFactory();
        if (connectionFactory instanceof JedisConnectionFactory) {
            return ((JedisConnectionFactory)connectionFactory).getHostName();
        } else if (connectionFactory instanceof LettuceConnectionFactory) {
            return ((LettuceConnectionFactory)connectionFactory).getHostName();
        }
        return Constants.LOCALHOST;
    }*/

    /**
     * Set the value and expire  for key.
     */
    public void set(String key, String value, long expire, TimeUnit unit) {
        redisTemplate.opsForValue().set(key, value, expire, unit);
    }

    public void set(String key, long value, long expire, TimeUnit unit) {
        redisTemplate.opsForValue().set(key, value, expire, unit);
    }

    public void set(String key, Object value, long expire, TimeUnit unit) {
        redisTemplate.opsForValue().set(key, value, expire, unit);
    }
    /**
     * setNx & setExpire
     *
     * @param key    must not be {@literal null}.
     * @param value  must not be {@literal null}.
     * @param expire the key expiration timeout.
     * @param unit   must not be {@literal null}.
     */
    public boolean setNX(String key, String value, long expire, TimeUnit unit) {
        return redisTemplate.opsForValue().setIfAbsent(key, value, expire, unit);
    }


    /**
     * 增加或减少key
     *
     * @param key
     * @param num
     * @return
     */
    public long incr(String key, int num) {
        return redisTemplate.opsForValue().increment(key, num);
    }

    /**
     * 递增
     *
     * @param key    key
     * @param expire
     * @param unit
     * @return
     */
    public long incr(String key, long expire, TimeUnit unit) {
        Long val = redisTemplate.opsForValue().increment(key);
        if (val != null && val == 1) {
            redisTemplate.expire(key, expire, unit);
        }
        return val;
    }

    public String getString(String key) {
        Object o = redisTemplate.opsForValue().get(key);
        return o == null ? null : String.valueOf(o);
    }

    /**
     * Get the time to live for key in and convert it to the given unit
     *
     * @param key  must not be null
     * @param unit must not be null
     */
    public long ttl(String key, TimeUnit unit) {
        return redisTemplate.opsForValue().getOperations().getExpire(key, TimeUnit.MILLISECONDS);
    }

    public boolean del(String key) {
        return redisTemplate.delete(key);
    }

    /**
     * 判断 key 是否存在
     *
     * @param key 键
     * @return 如果存在 key 则返回 true，否则返回 false
     */
    public Boolean exists(String key) {
        return redisTemplate.hasKey(key);
    }


    public boolean lSet(String key, Object value) {
        try {
            redisTemplate.opsForList().rightPush(key, value);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * HashSet
     *
     * @param key   键 不能为null
     * @param item  项 不能为null
     * @param value 值
     * @return
     */
    public boolean hSet(String key, String item, Object value) {
        try {
            redisTemplate.opsForHash().put(key, item, value);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * HashGet
     *
     * @param key  键 不能为null
     * @param item 项 不能为null
     * @return 值
     */
    public Object hGet(String key, String item) {
        return redisTemplate.opsForHash().get(key, item);
    }
}

