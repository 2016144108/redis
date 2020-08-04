package com.redis.code.test;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import java.util.ArrayList;
import java.util.HashMap;

@SpringBootTest
public class StringTest {

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
    void TestString(){
        ValueOperations<String,String> opsForValue =redisTemplate.opsForValue();
        //set key value
        opsForValue.set("main","hello");

        //get key
        String value=opsForValue.get("main");
        System.out.println(value);

        opsForValue.set("number","1");
        value=opsForValue.get("number");
        System.out.println(value);
        //incr key
        opsForValue.increment("number");
        value=opsForValue.get("number");
        System.out.println(value);
        //decr key
        opsForValue.decrement("number");
        value=opsForValue.get("number");
        System.out.println(value);

        //append key value
        opsForValue.append("main",",geyang");
        value=opsForValue.get("main");
        System.out.println(value);

        //strlen key
        System.out.println(opsForValue.size("main"));

        //getrange key start end
        System.out.println(opsForValue.get("main",0,-1));

        //setrange key offset value
        opsForValue.set("main","gggggg",6);
        value=opsForValue.get("main");
        System.out.println(value);

        //mset key value[key value...]
        HashMap<String,String> map=new HashMap<>();
        map.put("s1","1");
        map.put("s2","2");
        map.put("s3","3");
        opsForValue.multiSet(map);

        //mset key value[key value...]
        ArrayList<String> keys=new ArrayList<>();
        keys.add("s1");
        keys.add("s2");
        keys.add("s3");
        System.out.println(opsForValue.multiGet(keys));
    }
}
