package com.redis.code.test;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import java.util.ArrayList;
import java.util.HashMap;

@SpringBootTest
public class ListTest {

    @Autowired
    private StringRedisTemplate redisTemplate;
    public StringRedisTemplate getRedisTemplate() {
        return redisTemplate;
    }
    public void setRedisTemplate(StringRedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Test
    void ContextLoads(){
        System.out.println(redisTemplate);
    }

    @Test
    void TestList(){
        ListOperations<String,String> opsForList =redisTemplate.opsForList();
        RedisOperations<String,String>operations =opsForList.getOperations();

        //lpush key value[value...]
        opsForList.leftPushAll("lists","e","d","c","b","a");
        //lrange key start stop
        System.out.println(opsForList.range("lists",0,-1));

        //rpush key value[value...]
        opsForList.rightPushAll("lists","f","g");
        System.out.println(opsForList.range("lists",0,-1));

        //lindex key index
        System.out.println(opsForList.index("lists",2));

        //llen key
        System.out.println(opsForList.size("lists"));

        //lrem key count value
        opsForList.leftPushAll("lists","i","i","i","i");
        opsForList.rightPushAll("lists","i","i","i","i");
        opsForList.remove("lists",2,"i");
        System.out.println(opsForList.range("lists",0,-1));
        opsForList.remove("lists",-2,"i");
        System.out.println(opsForList.range("lists",0,-1));
        opsForList.remove("lists",0,"i");
        System.out.println(opsForList.range("lists",0,-1));

        //lset key index value
        opsForList.set("lists",0,"b");
        System.out.println(opsForList.range("lists",0,-1));
        opsForList.set("lists",0,"a");

        //lpop key
        opsForList.leftPop("lists");
        System.out.println(opsForList.range("lists",0,-1));

        //rpop key
        opsForList.rightPop("lists");
        System.out.println(opsForList.range("lists",0,-1));
    }
}
