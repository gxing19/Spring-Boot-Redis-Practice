package com.springboot.redis.test;

import com.springboot.redis.entity.Actor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.DigestUtils;

import java.util.Date;

/**
 * @name: SaveDataByDataType
 * @desc: 使用不同的数据类型存储数据
 * @author: gxing
 * @date: 2018-11-07 09:24
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class SaveDataByDataType {

    @Autowired
    private RedisTemplate<Object, Object> redisTemplate;

    @Test
    public void contextLoads() {

    }

    /**
     * 使用String存储
     */
    @Test
    public void saveActorToString() {
        Actor actor = new Actor();
        for (Long i = 0l; i < 100; i++) {
            actor.setActorId(i).setFirstName("first_" + i)
                    .setLastName("last_" + i).setLastUpdate(new Date());
            redisTemplate.opsForValue().set(String.valueOf(actor.getActorId()), actor);
        }
    }

    /**
     * 使用HASH存储
     */
    @Test
    public void saveActorToHash() {
        Actor actor = new Actor();
        for (Long i = 0l; i < 100; i++) {
            actor.setActorId(i).setFirstName("first_" + i)
                    .setLastName("last_" + i).setLastUpdate(new Date());
            /*redisTemplate.opsForHash().put("actor" + i, "lastName",actor.getLastName());
            redisTemplate.opsForHash().put("actor" + i, "firstName",actor.getFirstName());
            redisTemplate.opsForHash().put("actor" + i, "lastUpdate",actor.getLastUpdate());*/

            //每个KEY 存1000个字段; 10000条数据占内存 1.77M
            Long a = i/1000;
            redisTemplate.opsForHash().put("actor:" + a, String.valueOf(i), actor);
        }
    }

    /**
     * 使用HASH 分布存储50000条用户数据
     */
    @Test
    public void saveToHashTest() {
        String nickName1 = null;
        String nickName2 = null;
        String nickName3 = null;
        int profile_id = 0;

        for (int i = 0; i < 50000; i++) {
            nickName1 = "Tom";
            nickName2 = "Kitty";
            nickName3 = "Andy";

            nickName1 = nickName1 + i;
            nickName2 = nickName2 + i;
            nickName3 = nickName3 + i;
            profile_id = i;

            String digest = DigestUtils.md5DigestAsHex((nickName1 + nickName2 + nickName3).getBytes());
            String key = digest.substring(0, 2);

            //可以用管道技术pipeline,一次发送多条命令
            redisTemplate.opsForHash().put(key, nickName1, String.valueOf(profile_id));
            redisTemplate.opsForHash().put(key, nickName2, String.valueOf(profile_id));
            redisTemplate.opsForHash().put(key, nickName3, String.valueOf(profile_id));

            /*
            设置：hash-max-zipmap-entries 1000
            5万条数据占内存3.90M
            取三个呢称的md5的前两位，就有256个key(16*16)
            将每个呢称作为key 的 field，profile_id作为 value
            */
        }
    }
}