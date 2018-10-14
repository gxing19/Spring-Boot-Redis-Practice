package com.springboot.redis.repository;

import com.springboot.redis.entity.Actor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//继承JpaRepository 或 CrudRepository 接口
@Repository
public interface ActorRepository extends JpaRepository<Actor, Long> {
}
