package top.lmqstudy.service;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class RedisService {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    public void setStringKeyAndValue(String key, Object value) {
        stringRedisTemplate.opsForValue().set(key, JSONObject.toJSONString(value));
    }

    public void setStringKeyAndValue(String key, String value) {
        stringRedisTemplate.opsForValue().set(key, value);
    }

    public void setStringKeyAndValue(String key, Object value, long minute) {
        stringRedisTemplate.opsForValue().set(key, JSONObject.toJSONString(value), minute, TimeUnit.MINUTES);
    }

    public void setStringKeyAndValue(String key, String value, long minute) {
        stringRedisTemplate.opsForValue().set(key, value, minute, TimeUnit.MINUTES);
    }

    /**
     * 功能说明 取出对象并转换为对象类型
     *
     * @param key
     * @param clazz
     * @return T
     * @author caiwen
     * @date 2020/8/22
     */
    public <T> T getKeyObjectValue(String key, Class<T> clazz) {
        T t = null;
        String s = stringRedisTemplate.opsForValue().get(key);
        if (StringUtils.equalsIgnoreCase(clazz.getName(), "java.lang.string")) {
            t = (T) s;
        } else if (StringUtils.isNotBlank(s)) {
            t = JSONObject.parseObject(s, clazz);
        }
        return t;
    }

    public void deleteByKey(String key){
        stringRedisTemplate.delete(key);
    }
}
