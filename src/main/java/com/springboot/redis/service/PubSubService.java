package com.springboot.redis.service;

/**
 * @name: PubSubService
 * @desc: TODO
 * @author: gxing
 * @date: 2018-11-09 15:15
 **/
public interface PubSubService {

    void publishMsg(String msg);
}
