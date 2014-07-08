package com.ba.captwo.eda.demo.processors;

import com.ba.captwo.eda.demo.model.*;
import com.ba.captwo.eda.demo.utils.RouteUtils;
import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.processor.aggregate.AggregationStrategy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by u760245 on 06/07/2014.
 */
@Component("ReservationAggregator")
public class ReservationAggregator implements AggregationStrategy {

    private final Logger log = LoggerFactory.getLogger(ReservationAggregator.class);

    @Override
    public Exchange aggregate(Exchange oldExchange, Exchange newExchange) {

        if (oldExchange != null) RouteUtils.messageLog("ReservationAggregator OLD", oldExchange.getIn());
        if (newExchange != null) RouteUtils.messageLog("ReservationAggregator NEW", newExchange.getIn());

        if (newExchange.getIn().getHeaders().containsKey(RESTProducerEndpoint.RESPONSE))    {
            Object inObj =  newExchange.getIn().getHeaders().get(RESTProducerEndpoint.RESPONSE);
            log.info("Response Object Type : "+inObj.getClass());

            if (inObj instanceof Booking)   {
                Object d = (oldExchange == null) ? (newExchange.getIn().getHeaders().put("booking", inObj)) : (oldExchange.getIn().getHeaders().put("booking", inObj));
            }
            else if (inObj instanceof Person)   {
                Object d = (oldExchange == null) ? (newExchange.getIn().getHeaders().put("person", inObj)) : (oldExchange.getIn().getHeaders().put("person", inObj));
            }
            else if (inObj instanceof com.ba.captwo.eda.demo.model.Error)   {
                Object d = (oldExchange == null) ? (newExchange.getIn().getHeaders().put("error", inObj)) : (oldExchange.getIn().getHeaders().put("err", inObj));
            }
        }

        if (oldExchange == null) {
            return newExchange;
        } else {
            return oldExchange;
        }


    }
}
