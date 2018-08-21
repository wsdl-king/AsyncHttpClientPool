package com.server.java.util.redis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.*;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;


@Component
public class RedisTool implements RedisOperation {

    private static final Logger LOGGER = LoggerFactory.getLogger(RedisTool.class);
    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    /*
     * 默认超期时间
     */
    private static Integer TIMEOUT_SECONDS = 5184000;

    public void set(final String key, final String value) {
        set(key, value, TIMEOUT_SECONDS);
    }

    /**
     * 设置redis key val 过期时间
     * key 键
     * value 值
     * seconds 过期时间
     */
    public void set(final String key, final String value, final Integer seconds) {
        redisTemplate.execute((RedisCallback<Object>) connection -> {
            connection.setEx(key.getBytes(), seconds, value.getBytes());
            return null;
        });
    }

    /**
     * 删除redis key
     */
    public void del(String key) {
        redisTemplate.delete(key);
    }

    /**
     * 获取
     */
    public String get(final String key) {
        String value = redisTemplate.execute((RedisCallback<String>) connection -> {
            RedisSerializer<String> serializer = getRedisSerializer();
            byte[] keyid = serializer.serialize(key);
            byte[] value1 = connection.get(keyid);
            if (value1 == null) {
                return null;
            }
            String name = serializer.deserialize(value1);
            return name;
        });
        return value;
    }

    /**
     * 匹配
     */
    public Set<String> keys(String key) {
        return redisTemplate.keys(key);
    }

    /**
     * 获取 RedisSerializer
     * <br>------------------------------<br>
     */
    private RedisSerializer<String> getRedisSerializer() {
        return redisTemplate.getStringSerializer();
    }

    public RedisTemplate<String, String> getRedisTemplate() {
        return redisTemplate;
    }

    public void setRedisTemplate(RedisTemplate<String, String> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }


    public void zSetAdd(String key, String value, Long time) {
        ZSetOperations<String, String> zSet = redisTemplate.opsForZSet();
        zSet.add(key, value, time);
    }

    public Set<ZSetOperations.TypedTuple<String>> getAllZSet(String key) {
        ZSetOperations<String, String> zSet = redisTemplate.opsForZSet();
        return zSet.rangeWithScores(key, 0, -1);
    }

    public void zInterSet(String key1, String key2, String newKey) {
        ZSetOperations<String, String> zSet = redisTemplate.opsForZSet();
        zSet.intersectAndStore(key1, key2, newKey);
    }

    public void zUnitonSet(String key1, String key2, String newKey) {
        ZSetOperations<String, String> zSet = redisTemplate.opsForZSet();
        zSet.unionAndStore(key1, key2, newKey);
    }

    public void rmUnionSet(String key) {
        ZSetOperations<String, String> zSet = redisTemplate.opsForZSet();
        zSet.removeRangeByScore(key, 1, Long.MAX_VALUE);
    }

    public void rmZset(String key, String value) {
        ZSetOperations<String, String> zSet = redisTemplate.opsForZSet();
        zSet.remove(key, value);
    }
}
