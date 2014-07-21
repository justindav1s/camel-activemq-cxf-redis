package com.ba.captwo.eda.demo.processors;

import com.ba.captwo.eda.demo.model.Booking;
import com.ba.captwo.eda.demo.utils.RouteUtils;
import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.Processor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * Created by u760245 on 06/07/2014.
 */
@Component("BookingProcessor")
public class BookingProcessor implements Processor {

    private final Logger log = LoggerFactory.getLogger(BookingProcessor.class);

    public void process(Exchange exchange) throws Exception {

        RouteUtils.messageLog("BookingProcessor", exchange.getIn());
        Map<String, String> params = (Map<String, String>)exchange.getIn().getBody();
        Booking b = new Booking();
        b.setPersonID(1100);
        b.setFlightNum(params.get("fnum"));
        b.setTickets(Integer.parseInt(params.get("ticks")));
        b.setCabin(params.get("fnum"));

        exchange.getIn().getHeaders().put("booking", b);

    }

}
