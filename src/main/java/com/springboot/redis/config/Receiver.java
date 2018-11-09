package com.springboot.redis.config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @name: Receiver
 * @desc: TODO
 * @author: gxing
 * @date: 2018-11-09 15:10
 **/
public class Receiver {

    private static final Logger logger = LogManager.getLogger(Receiver.class);

    public void receiveMessage(String message) {
        logger.info("Received <" + message + ">");
    }
}