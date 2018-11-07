package com.springboot.redis.config;

import com.alibaba.fastjson.support.spring.FastJsonRedisSerializer;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.net.UnknownHostException;

@Configuration
public class RedisConfig {

    /**
     * redis默认使用jdk的二进制数据来序列化
     * 以下自定义使用jackson来序列化
     *
     * @param redisConnectionFactory
     * @return
     * @throws UnknownHostException
     */
    @Bean
    @SuppressWarnings({"rawtypes", "unchecked"})
    public RedisTemplate<Object, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<Object, Object> template = new RedisTemplate<Object, Object>();
        template.setConnectionFactory(redisConnectionFactory);

//        Jackson2JsonRedisSerializer serializer = new Jackson2JsonRedisSerializer(Object.class);
        FastJsonRedisSerializer serializer = new FastJsonRedisSerializer(Object.class);

        /*
        ObjectMapper om = new ObjectMapper();
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        jackson2JsonRedisSerializer.setObjectMapper(om);
        */

        template.setKeySerializer(new StringRedisSerializer()); //1
        template.setValueSerializer(serializer); //2

        template.setHashKeySerializer(new StringRedisSerializer());
        template.setHashValueSerializer(serializer);

        template.setEnableTransactionSupport(true);

        template.afterPropertiesSet();
        return template;
    }

}
