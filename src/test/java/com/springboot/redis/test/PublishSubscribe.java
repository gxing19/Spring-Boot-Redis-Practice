package com.springboot.redis.test;

import com.springboot.redis.service.PubSubService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @name: PublishSubscribe
 * @desc: TODO
 * @author: gxing
 * @date: 2018-11-09 15:22
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class PublishSubscribe {

    private static final Logger logger = LogManager.getLogger(PublishSubscribe.class);

    @Autowired
    private PubSubService pubSubService;

    @Test
    public void publishMsg(){
        String msg = "";
        pubSubService.publishMsg(msg);
    }

}