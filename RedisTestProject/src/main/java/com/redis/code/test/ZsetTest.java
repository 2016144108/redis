package com.redis.code.test;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;

@SpringBootTest
public class ZsetTest {

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
    void TestZset() {
        ZSetOperations<String,String> opsForZset = redisTemplate.opsForZSet();
        RedisOperations<String, String> operations =opsForZset.getOperations();

        opsForZset.add("zsets","java",1);
        opsForZset.add("zsets","jsp",2);
        opsForZset.add("zsets","php",3);

        System.out.println(opsForZset.range("zsets",0,-1));
        System.out.println(opsForZset.rangeWithScores("zsets",0,-1));

        System.out.println(opsForZset.reverseRange("zsets",0,-1));
        System.out.println(opsForZset.reverseRangeWithScores("zsets",0,-1));

        System.out.println(opsForZset.rangeByScore("zsets",2,3));
        System.out.println(opsForZset.rangeByScoreWithScores("zsets",2,3));

        System.out.println(opsForZset.reverseRangeByScore("zsets",2,3));
        System.out.println(opsForZset.reverseRangeByScoreWithScores("zsets",2,3));

        System.out.println(opsForZset.count("zsets",1,2));

        opsForZset.remove("zsets","jsp","php");
        System.out.println(opsForZset.rangeWithScores("zsets",0,-1));
    }
}
