package com.springboot.redis.service.ServiceImpl;

import com.springboot.redis.service.PubSubService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.concurrent.CountDownLatch;

/**
 * @name: PublishSubscribe
 * @desc: TODO
 * @author: gxing
 * @date: 2018-11-09 15:13
 **/
@Service
public class PubSubServiceImpl implements PubSubService {

    private static final Logger logger = LogManager.getLogger(PubSubServiceImpl.class);

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private CountDownLatch countDownLatch;

    @Override
    public void publishMsg(String msg) {
        if(StringUtils.isEmpty(msg)){
            msg = "2018-2018 NBA Sport Start";
        }
        stringRedisTemplate.convertAndSend("NBA-NEWS", msg);
    }
}