package com.example.stepone.tools;

import cn.hutool.json.JSONUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

@Component
public class QRedisTool {

    @Autowired
    RedisTemplate redisTemplate;

    // 默认 七天 存活 时间
    final static int DEF_LIVE_TIME = 60 * 60 * 24 * 7;

    // 过期 时间单位 为 秒
    final static TimeUnit TIME_UNIT = TimeUnit.SECONDS;

    // 有 存活 时间
    public <T> void set(String k, T v, Integer liveSecond) {
        try {
            ValueOperations vo = redisTemplate.opsForValue();
            vo.set(k, JSONUtil.toJsonStr(v), liveSecond, TIME_UNIT);
        } catch (Exception e) { }
    }
    public <T> void set(String k, T v) { set(k, v, DEF_LIVE_TIME); }

    public <T> T get(String k, Class<T> clazz) {
        try {
            ValueOperations vo = redisTemplate.opsForValue();
            String obj = Objects.requireNonNull(vo.get(k)).toString();
            return JSONUtil.toBean(obj, clazz);
        } catch (Exception e) { } return null;
    }

    // 获取
    public String get(String k) {
        try {
            ValueOperations vo = redisTemplate.opsForValue();
            return Objects.requireNonNull(vo.get(k)).toString();
        } catch (Exception e) { } return null;
    }

    // 重置 过期 时间（相当于延长）
    public void expire(String k, long time) {
        try {
            redisTemplate.expire(k, time, TIME_UNIT);
        } catch (Exception e) { }
    }
}
