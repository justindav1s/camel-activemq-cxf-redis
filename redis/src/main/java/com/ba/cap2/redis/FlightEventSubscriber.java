package com.ba.cap2.redis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisMonitor;


public class FlightEventSubscriber {

    private final static Logger log = LoggerFactory.getLogger(FlightEventSubscriber.class);
	public static final String FLIGHT_EVENTS_CHAN = "flight.events";
	
    public static void main( String[] args ) {

		Jedis jedis = new Jedis("localhost");
        log.debug("Jedis : "+jedis);
		
    	RedisListener listener = new RedisListener();
    	jedis.subscribe(listener, FLIGHT_EVENTS_CHAN);
    }
	
}
