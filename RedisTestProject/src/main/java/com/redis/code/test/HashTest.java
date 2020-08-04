package com.redis.code.test;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.ArrayList;
import java.util.HashMap;

@SpringBootTest
public class HashTest {

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
    void TestHash() {
        HashOperations<String, Object, Object> opsForHash = redisTemplate.opsForHash();
        RedisOperations<String, String> operations = (RedisOperations<String, String>) opsForHash.getOperations();

        //hset key(redis哈希表中的键值) field(存储哈希表中的键，在redis为字段) value(存储哈希表中的值)
        opsForHash.put("hashs", "java", "java2");

        //hget key field
        System.out.println(opsForHash.get("hashs", "java"));

        //hmset key field value[field value...]
        HashMap<String, Object> map = new HashMap<>();
        map.put("jsp", "jsp2");
        map.put("php", "php2");
        map.put("c++", "c++2");
        opsForHash.putAll("hashs", map);

        //hmget key field[field...]
        ArrayList<Object> list = new ArrayList<>();
        list.add("java");
        list.add("jsp");
        list.add("php");
        list.add("c++");
        System.out.println(opsForHash.multiGet("hashs", list));

        //hkeys key
        System.out.println(opsForHash.keys("hashs"));

        //hvals key
        System.out.println(opsForHash.values("hashs"));

        //hgetall key
        System.out.println(opsForHash.entries("hashs"));

        //hexists key field
        System.out.println(opsForHash.hasKey("hashs","java"));

        //hdel key field[field
        opsForHash.delete("hashs","java");
        System.out.println(opsForHash.entries("hashs"));
    }
}
