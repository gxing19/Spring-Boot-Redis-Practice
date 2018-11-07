package com.springboot.redis.test;

import com.alibaba.fastjson.JSON;
import com.springboot.redis.config.RedisConfig;
import io.lettuce.core.api.async.RedisTransactionalAsyncCommands;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @name: RedisTransaction
 * @desc: Redis事务
 * @author: gxing
 * @date: 2018-11-07 09:52
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisTransaction {

    private static final Logger logger = LogManager.getLogger(RedisTransaction.class);

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private RedisConnectionFactory redisConnectionFactory;

    @Test
    public void redisTransactionTest(){

        redisTemplate.multi();
        redisTemplate.opsForValue().set("name","Tom Tom");
//        redisTemplate.opsForValue().increment("filmName",1);
        redisTemplate.opsForValue().set("filmName","Tom Love Love Love Jerry");
        List<Object> execList = redisTemplate.exec();

        /*RedisConnection connection = redisConnectionFactory.getConnection();
        //乐观锁
        connection.watch("name".getBytes());
        //开启事务
        connection.multi();
        connection.set("age".getBytes(),String.valueOf(34).getBytes());
        connection.set("filmName".getBytes(),"Tom Love Jerry".getBytes());
        //乐观锁:在exec()处打断点,使用其它客户端修改watch(key)的值,此处再方放行,事务执行失败返回null
        List<Object> execList = connection.exec();*/
        logger.info("execList:{}", JSON.toJSONString(execList));
    }
}