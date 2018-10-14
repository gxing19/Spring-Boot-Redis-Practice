package com.springboot.redis.service;

import com.springboot.redis.entity.Actor;

public interface ActorService {

    Actor queryById(Long actorId);

    Actor queryByActorId(Long actorId);
}
