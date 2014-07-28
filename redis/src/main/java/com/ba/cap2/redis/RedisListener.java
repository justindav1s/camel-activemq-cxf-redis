package com.ba.cap2.redis;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPubSub;

import java.util.Set;

class RedisListener extends JedisPubSub {

    private final Logger log = LoggerFactory.getLogger(RedisListener.class);
	private Jedis jedis = null;
	
	public RedisListener()	{
	}
	
    public void onMessage(String channel, String message) {
        log.debug("onMessage : Received from channel : "+channel+ " message : "+message+" : "+message.length());
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
    	log.debug("onPMessage : Received from channel : ["+channel+ "] pattern : ["+pattern+"] message : ["+message+"]");
    }
    
}
