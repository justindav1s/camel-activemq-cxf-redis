package com.ba.cap2.redis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;

/**
 * Created by u760245 on 22/07/2014.
 */
public class RedisTest {

    private final static Logger log = LoggerFactory.getLogger(RedisTest.class);

    public static void main(String[] args)  {

        String[] keys = {"A", "A:B", "A:B:C", "A:B:C:D", "A:B:C:D","A:B:E" };
        String[] values = {"SET VAL A", "SET VAL A:B", "SET VAL A:B:C", "SET VAL A:B:C:D", "SET VAL A:B:C:D","SET VAL A:B:E" };

        Jedis jedis = new Jedis("localhost");
        log.debug("Jedis : "+jedis);
        jedis.flushAll();

        for (int i = 0; i < keys.length; i++)   {
            log.debug("Setting key : "+keys[i]+" to value : "+values);
            jedis.set(keys[i], values[i]);
        }

        //jedis.hset(keys[1], "HSET KEY1", "HSET VAL1");
        //jedis.hset(keys[1], "HSET KEY2", "HSET VAL2");
    }
}
