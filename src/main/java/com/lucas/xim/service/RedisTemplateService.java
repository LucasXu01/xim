package com.lucas.xim.service;

import com.lucas.xim.utils.JsonMapper;
import com.lucas.xim.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @name 封装的Redis工具类，所有key/value都以json形式存储
 */
@Service
public class RedisTemplateService {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;


    /**
     * 向变量中批量添加值。返回添加的数量
     *
     * add(K key, V... values)
     */
    public<T> void set_add(String key, String... values) {
        Long add = stringRedisTemplate.opsForSet().add(key, values);
        System.out.println(add);
    }

    public Set<String> set_get(String key) {
        Set<String> set = stringRedisTemplate.opsForSet().members(key);
        return set;
    }



    /**
     * 存放值到redis
     *
     * @param key
     * @param value
     * @param <T>
     * @return
     */
    public <T> boolean set(String key, T value) {
        try {
            String val = JsonMapper.toJSONString(value);
            if (StringUtil.isEmpty(val)) {
                return false;
            }

            stringRedisTemplate.opsForValue().set(key, val);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    /**
     * 存放值到redis
     * 重载
     *
     * @param key
     * @param value
     * @param timeout 过期时间
     * @param unit
     * @param <T>
     * @return
     */
    public <T> boolean set(String key, T value, long timeout, TimeUnit unit) {
        try {
            String val = JsonMapper.toJSONString(value);
            if (StringUtil.isEmpty(val)) {
                return false;
            }

            stringRedisTemplate.opsForValue().set(key, val, timeout, unit);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    /**
     * 从redis获取值
     *
     * @param key
     * @param cls
     * @param <T>
     * @return
     */
    public <T> T get(String key, Class<T> cls) {
        try {
            String val = stringRedisTemplate.opsForValue().get(key);
            return JsonMapper.parseObject(val, cls);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * 设置redis key过期时间
     *
     * @param key
     * @param timeout
     * @param unit
     */
    public void expire(String key, long timeout, TimeUnit unit) {
        try {
            stringRedisTemplate.expire(key, timeout, unit);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
