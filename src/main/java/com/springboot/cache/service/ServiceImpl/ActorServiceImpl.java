package com.springboot.cache.service.ServiceImpl;

import com.springboot.cache.dao.ActorRedisDao;
import com.springboot.cache.entity.Actor;
import com.springboot.cache.repository.ActorRepository;
import com.springboot.cache.service.ActorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class ActorServiceImpl implements ActorService {

    //调用的是CrudRepository的方法
    @Autowired
    private ActorRepository actorRepository;

    //调用的是RedisTemplate
    @Autowired
    private ActorRedisDao actorRedisDao;

    /**
     * 先查Redis缓存,没有从库里查再写入缓存,手动写入Redis库
     * @param actorId
     * @return
     */
    public Actor queryById(Long actorId) {
        Actor actor = (Actor) actorRedisDao.getActor(String.valueOf(actorId));
//        return actor != null ? actor : actorRepository.findById(actorId).get();
        if(actor == null){
            System.out.println("------redis 缓存不存在 actorId = " + actorId + " 的数据--------");
            actor = actorRepository.findById(actorId).get();
            actorRedisDao.save(String.valueOf(actor.getActorId()), actor);
        }else {
            System.out.println("--------actorId = " + actorId + " 的数据来自于redis缓存---------");
        }
        return actor;
    }

    /**
     * 使用Cache注解
     * 先查缓存,没有再查库,再写入缓存
     * 缓存注解使用:http://112.74.59.39/2018/06/01/spring-cache-annotation/
     * @param actorId
     * @return
     */
    @Override
    @Cacheable(key = "#actorId",value = "actor")
    public Actor queryByActorId(Long actorId) {
        Actor actor = actorRepository.findById(actorId).get();
        System.out.println("--------数据来自于数据库---------");
        return actor;
    }
}
