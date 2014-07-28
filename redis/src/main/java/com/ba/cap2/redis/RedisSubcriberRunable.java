package com.ba.cap2.redis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;

/**
 * Created by u760245 on 23/07/2014.
 */
public class RedisSubcriberRunable implements Runnable{

    String chan = null;
    Jedis jedis = null;

    private final Logger log = LoggerFactory.getLogger(FlightEventSubscriber.class);

    RedisSubcriberRunable(Jedis _jedis, String _chan)  {
        jedis = _jedis;
        chan = _chan;
    }

    @Override
    public void run() {

        RedisListener listener = new RedisListener();
        log.debug("Subscribing to : "+chan);
        jedis.psubscribe(listener, chan+".*");

    }
}
