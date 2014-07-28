package com.ba.cap2.redis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;


public class FlightEventSubscriber {

    private final static Logger log = LoggerFactory.getLogger(FlightEventSubscriber.class);
    public static final String FLIGHT_EVENTS = "flight.events";
    public static final String FLIGHT_EVENTS_LON = "flight.events.lon";
    public static final String FLIGHT_EVENTS_LON_T5 = "flight.events.lon.t5";
    public static final String FLIGHT_EVENTS_LON_T5_BA009 = "flight.events.lon.t5.ba009";
    public static final String FLIGHT_EVENTS_LON_T5_BA009_READINESS = "flight.events.lon.t5.ba009.readiness";
    public static final String FLIGHT_EVENTS_LON_T5_BA009_SCHEDULE = "flight.events.lon.t5.ba009.schedule";
    public static final String FLIGHT_EVENTS_BA009 = "flight.events.*.*.ba009";
    public static final String FLIGHT_EVENTS_BKK = "*.*.bkk";
    public static void main( String[] args ) {

		Jedis jedis = new Jedis("localhost");
        log.debug("Jedis : "+jedis);

        RedisSubcriberRunable sub1 = new RedisSubcriberRunable(new Jedis("localhost"), FLIGHT_EVENTS);
        RedisSubcriberRunable sub2 = new RedisSubcriberRunable(new Jedis("localhost"), FLIGHT_EVENTS_LON);
        RedisSubcriberRunable sub3 = new RedisSubcriberRunable(new Jedis("localhost"), FLIGHT_EVENTS_LON_T5);
        RedisSubcriberRunable sub4 = new RedisSubcriberRunable(new Jedis("localhost"), FLIGHT_EVENTS_LON_T5_BA009);
        RedisSubcriberRunable sub5 = new RedisSubcriberRunable(new Jedis("localhost"), FLIGHT_EVENTS_LON_T5_BA009_READINESS);
        RedisSubcriberRunable sub6 = new RedisSubcriberRunable(new Jedis("localhost"), FLIGHT_EVENTS_LON_T5_BA009_SCHEDULE);
        RedisSubcriberRunable sub7 = new RedisSubcriberRunable(new Jedis("localhost"), FLIGHT_EVENTS_BA009);
        RedisSubcriberRunable sub8 = new RedisSubcriberRunable(new Jedis("localhost"), FLIGHT_EVENTS_BKK);

        Thread t1 = new Thread(sub1);
        Thread t2 = new Thread(sub2);
        Thread t3 = new Thread(sub3);
        Thread t4 = new Thread(sub4);
        Thread t5 = new Thread(sub5);
        Thread t6 = new Thread(sub6);
        Thread t7 = new Thread(sub7);
        Thread t8 = new Thread(sub8);

        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();
        t6.start();
        t7.start();
        t8.start();

        do {
           int i = 1;
        }while (true);

    }
	
}
