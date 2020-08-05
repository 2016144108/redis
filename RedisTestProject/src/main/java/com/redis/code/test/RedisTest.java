package com.redis.code.test;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.*;
import java.util.concurrent.TimeUnit;

@SpringBootTest
public class RedisTest {

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
    void TestRedis() {
        //keys pattern
        System.out.println(redisTemplate.keys("*"));

        //exists key[key...]
        System.out.println(redisTemplate.hasKey("a1"));

        ValueOperations<String,String> opsForValue =redisTemplate.opsForValue();
        opsForValue.set("ss","1");
        //expire key seconds
        redisTemplate.expire("ss",60, TimeUnit.SECONDS);
        //ttl key
        System.out.println(redisTemplate.getExpire("ss"));

        //type key
        System.out.println(redisTemplate.type("sets"));

        //输出随机键
        System.out.println(redisTemplate.randomKey());

        //重命名
        redisTemplate.rename("ss","sss");

        //del key[key...]
        redisTemplate.delete("ss");

        System.out.println(redisTemplate.getStringSerializer());
        System.out.println(redisTemplate.getValueSerializer());
        System.out.println(redisTemplate.getHashValueSerializer());
        System.out.println(redisTemplate.getDefaultSerializer());
        System.out.println(redisTemplate.getKeySerializer());
        System.out.println(redisTemplate.getHashKeySerializer());

        redisTemplate.execute(new SessionCallback<Object>(){
            @Override
            public <K, V> Object execute(RedisOperations<K, V> operations) throws DataAccessException {
                operations.multi();
                opsForValue.set("ss","1");
                System.out.println(opsForValue.get("ss"));
                redisTemplate.delete("ss");
                //执行事务
                operations.exec();
                return null;
            }
            /* //事务初始化multi
        redisTemplate.multi();
        //事务执行exec
        redisTemplate.exec();
        //事务取消discard
        redisTemplate.discard();
        //监控与取消监控
       redisTemplate.watch("ss");
       redisTemplate.unwatch();
        */
        });

        /*//从服务器挂起
        redisTemplate.slaveOf("127.0.0.1",8000);
        //从服务器升级
        redisTemplate.slaveOfNoOne();*/

        //其他方法集
        //RedisOperations<String,String> operations=opsForValue.getOperations();

       /* redisTemplate.execute(new RedisCallback<Object>() {
            @Override
            public Object doInRedis(RedisConnection redisConnection) throws DataAccessException {
                System.out.println(redisConnection.ping());
                System.out.println(redisConnection.dbSize());
                redisConnection.select(1);
                redisConnection.flushDb();
                redisConnection.flushAll();
                return null;
            }
        });*/
    }
}
