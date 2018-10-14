package com.springboot.redis;

import com.alibaba.fastjson.JSON;
import com.springboot.redis.config.RedisConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisApplicationTests {

    @Autowired
    private RedisTemplate<Object, Object> redisTemplate;

    @Test
    public void contextLoads() {

    }

    @Test
    public void setValueByPipe(){

        RedisCallback<Object> redisCallback = new RedisCallback<Object>() {

            //doInRedis中的redis操作不会立刻执行
            @Override
            public Object doInRedis(RedisConnection connection) throws DataAccessException {
                connection.openPipeline();

                /*---执行业务 多个 Redis 命令: start -----*/
                for (int i = 0; i < 10000; i++) {
                    String key = "key:" + i;
                    String value = "value" + i;
                    connection.set(key.getBytes(), value.getBytes());
                    connection.get(key.getBytes());
                }
                /*----------- end -----------*/

                //所有redis操作会在connection.closePipeline()之后一并提交到redis并执行，这是pipeline方式的优势
//                List<Object> resultList = connection.closePipeline();
                return null;
            }
        };

        //所有操作的执行结果为executePipelined()的返回值
        List<Object> resultList = redisTemplate.executePipelined(redisCallback, redisTemplate.getStringSerializer());
        System.out.println(JSON.toJSONString(resultList));
    }
}
