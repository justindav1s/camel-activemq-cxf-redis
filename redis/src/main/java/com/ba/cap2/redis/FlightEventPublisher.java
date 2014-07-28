package com.ba.cap2.redis;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;


public class FlightEventPublisher {

    private final static Logger log = LoggerFactory.getLogger(FlightEventPublisher.class);
	
	private Jedis jedis = null;
	
    public static void main( String[] args ) {

    	String flightnum = "BA0009";
    	
		Jedis jedis = new Jedis("localhost");
        log.debug("Jedis : "+jedis);
		jedis.flushAll();
        
    	FlightEventPublisher publisher = new FlightEventPublisher(jedis);
    	publisher.setflightEvent(flightnum, FlightStates.PLANNED, new Date());
    	publisher.setflightEvent(flightnum, FlightStates.READYFORPUSHBACK, new Date());
    	publisher.setflightEvent(flightnum, FlightStates.DEPARTED, new Date());
    	publisher.setflightEvent(flightnum, FlightStates.ARRIVED, new Date());
    	//jedis.disconnect();
    }
	
	public FlightEventPublisher(Jedis _jedis)	{			
		jedis = _jedis;		
	}
	
	public void setflightEvent(String flightnum, String event, Date timestamp)	{
        log.debug("Publishing to flightnum : "+flightnum+" data : "+event);
		jedis.hset(flightnum, event, timestamp.toString());
		jedis.publish(FlightEventSubscriber.FLIGHT_EVENTS, flightnum);
        log.debug("Flightnum : "+flightnum+" data : "+jedis.hgetAll(flightnum));
	}
	
}
