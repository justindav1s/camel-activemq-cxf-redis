package com.ba.cap2.redis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;

import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Created by u760245 on 22/07/2014.
 */
public class RedisTest {

    private final static Logger log = LoggerFactory.getLogger(RedisTest.class);

    RandomString rString = new RandomString();
    private final Random random = new Random();

    public static void main(String[] args)  {

        RedisTest rt = new RedisTest();
        //rt.sendEvent();

        Jedis jedis = new Jedis("localhost");
        for (int i = 0; i < 1; i++)   {
            log.debug("inserting : BA0"+i);
            rt.insertKey(jedis, "FLIGHTS:BA0"+i, rt.getRandomMap());

        }

        for (int i = 0; i < 1; i++)   {
            log.debug("inserting : Jane"+i);
            rt.insertKey(jedis, "PAX:Janet"+i, rt.getRandomMap());
        }

        for (int i = 0; i < 1; i++)   {
            log.debug("inserting : John"+i);
            rt.insertKey(jedis, "PAX:John"+i, rt.getRandomMap());
        }

    }

    private void sendEvent()   {
        Jedis jedis = new Jedis("localhost");
        log.debug("Jedis : "+jedis);

        jedis.publish("flight.events.par", "111111 flight.events.par DATA");
        log.debug("----");
        try {Thread.currentThread().sleep(1000);} catch (Exception e){}
        jedis.publish("flight.events.lon.t5", "222222 flight.events.lon.t5 DATA");
        log.debug("----");
        try {Thread.currentThread().sleep(1000);} catch (Exception e){}
        jedis.publish("flight.events.lon.t5.ba009", "3333333 flight.events.lon.t5.ba009 DATA");
        log.debug("----");
        try {Thread.currentThread().sleep(1000);} catch (Exception e){}
        jedis.publish("flight.events.lon.t5.ba010", "4444444 flight.events.lon.t5.ba010 DATA");
        log.debug("----");
        try {Thread.currentThread().sleep(1000);} catch (Exception e){}
        jedis.publish("flight.events.bkk.t1.ba009", "5555555 flight.events.bkk.t1.ba009 DATA");
        log.debug("----");
        try {Thread.currentThread().sleep(1000);} catch (Exception e){}
        jedis.publish("security.events.bkk.t1.ba009", "66666 security.events.bkk.t1.ba009 DATA");
        log.debug("----");
    }

    private void insertTest()   {

        String[] keys = {"A", "A:B", "A:B:C", "A:B:C:D", "A:B:C:D","A:B:E" };
        String[] values = {"SET VAL A", "SET VAL A:C", "SET VAL A:B:C", "SET VAL A:B:C:D", "SET VAL A:B:C:D","SET VAL A:B:E" };

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

    private void insertKey(Jedis jedis, String key, Map<String, String> map)    {

            jedis.hmset(key, map);

    }


    private Map<String,String> getRandomMap()   {
        int mapsize = 40 + random.nextInt(10);
        Map<String,String> rMap = new HashMap<String, String>();
        for (int i = 0; i < mapsize; i++)   {
            rMap.put(getRandomsString(20, 10), getRandomsString(199, 1));
        }
        return rMap;
    }



    private String getRandomsString(int start, int addRange)   {
           int stringLength = start+random.nextInt(addRange);
           return rString.nextString(stringLength);

    }

    private void saveMap(String key, Map<String, String> map)   {

        HashMap<String, HashMap<String, String>> saveMe = new HashMap<String, HashMap<String, String>>();
        String filename = "key_"+key+".out";


    }



}
