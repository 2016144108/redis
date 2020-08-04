package com.redis.code.test;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.ArrayList;
import java.util.HashMap;

@SpringBootTest
public class SetTest {

    @Autowired
    private StringRedisTemplate redisTemplate;

    public StringRedisTemplate getRedisTemplate() {
        return redisTemplate;
    }

    public void setRedisTemplate(StringRedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Test
    void ContextLoads() {
        System.out.println(redisTemplate);
    }

    @Test
    void TestSet() {
        SetOperations<String,String>opsForSet = redisTemplate.opsForSet();
        RedisOperations<String, String> operations =opsForSet.getOperations();
        //sadd key member[member...]
        opsForSet.add("sets","a","b","c","d","e","f","g");

        //smembers key
        System.out.println(opsForSet.members("sets"));

        //sismember key member
        System.out.println(opsForSet.isMember("sets","a"));

        //scard key
        System.out.println(opsForSet.size("sets"));

        //srem key member[member...]
        opsForSet.remove("sets","g");
        System.out.println(opsForSet.members("sets"));

        //srandmember key [count]
        System.out.println(opsForSet.members("sets"));
        System.out.println(opsForSet.randomMember("sets"));
        System.out.println(opsForSet.randomMembers("sets",4));

        //spop key [count]
        opsForSet.pop("sets");
        System.out.println(opsForSet.members("sets"));
        opsForSet.pop("sets",2);
        System.out.println(opsForSet.members("sets"));
    }
}
