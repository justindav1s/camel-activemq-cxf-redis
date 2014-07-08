package com.ba.cap2.redis;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPubSub;

import java.util.Set;

class RedisListener extends JedisPubSub {

    private final Logger log = LoggerFactory.getLogger(FlightEventSubscriber.class);
	private Jedis jedis = null;
	
	public RedisListener()	{
		jedis = new Jedis("localhost");
        log.debug("Jedis : "+jedis);
	}
	
    public void onMessage(String channel, String message) {
        log.debug("onMessage : channel : "+channel+ " message : "+message+" : "+message.length());
    	String flightnum = message;
        Set<String> keys = jedis.keys("*");
        log.debug("keys : "+  keys);

        log.debug("Flightnum : "+flightnum+" data : "+jedis.hgetAll(flightnum));

    	//do stuff with the data that has changed
    }

    public void onSubscribe(String channel, int subscribedChannels) {
        log.debug("onSubscribe : channel : "+channel+ " subscribedChannels : "+subscribedChannels);
    }

    public void onUnsubscribe(String channel, int subscribedChannels) {
    	log.debug("onUnsubscribe : channel : "+channel+ " subscribedChannels : "+subscribedChannels);
    }

    public void onPSubscribe(String pattern, int subscribedChannels) {
    	log.debug("onPSubscribe : pattern : "+pattern+ " subscribedChannels : "+subscribedChannels);
    }

    public void onPUnsubscribe(String pattern, int subscribedChannels) {
    	log.debug("onPUnsubscribe : pattern : "+pattern+ " subscribedChannels : "+subscribedChannels);
    }

    public void onPMessage(String pattern, String channel, String message) {    	
    	log.debug("onPMessage : pattern : "+pattern+" channel : "+channel+ " message : "+message);
    }
    
}
