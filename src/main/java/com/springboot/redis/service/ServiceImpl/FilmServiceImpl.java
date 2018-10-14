package com.springboot.redis.service.ServiceImpl;

import com.springboot.redis.service.FilmService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class FilmServiceImpl implements FilmService {

    private static final Logger logger = LogManager.getLogger(FilmServiceImpl.class);

    @Autowired
    private RedisTemplate redisTemplate;

}
