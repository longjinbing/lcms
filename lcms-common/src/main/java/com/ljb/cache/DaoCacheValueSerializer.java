package com.ljb.cache;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;

/**
 * 作者: @author longjinbin <br>
 * 时间: 2018/11/12<br>
 * 描述: <br>
 */
public class DaoCacheValueSerializer implements RedisSerializer<Object> {

    private static Jackson2JsonRedisSerializer valueSerializer;

    static {
        valueSerializer = new Jackson2JsonRedisSerializer(Object.class);
        ObjectMapper om = new ObjectMapper();
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        valueSerializer.setObjectMapper(om);
    }

    @Override
    public byte[] serialize(Object o) throws SerializationException {
        return valueSerializer.serialize(o);
    }

    @Override
    public Object deserialize(byte[] bytes) throws SerializationException {
        return valueSerializer.deserialize(bytes);
    }
}
