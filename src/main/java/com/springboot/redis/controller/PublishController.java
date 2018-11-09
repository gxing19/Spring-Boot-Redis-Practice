package com.springboot.redis.controller;

import com.springboot.redis.service.PubSubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @name: PublishController
 * @desc: TODO
 * @author: gxing
 * @date: 2018-11-09 15:29
 **/
@RestController
@RequestMapping("/publish")
public class PublishController {

    @Autowired
    private PubSubService pubSubService;

    @RequestMapping("/msg")
    public void publishMsg(String msg){
        pubSubService.publishMsg(msg);

    }
}