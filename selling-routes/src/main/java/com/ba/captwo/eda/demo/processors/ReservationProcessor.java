package com.ba.captwo.eda.demo.processors;

import com.ba.captwo.eda.demo.model.*;
import com.ba.captwo.eda.demo.model.Error;
import com.ba.captwo.eda.demo.utils.RouteUtils;
import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.Processor;
import org.apache.cxf.message.MessageContentsList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.ws.rs.core.Response;

/**
 * Created by u760245 on 06/07/2014.
 */
@Component("ReservationProcessor")
public class ReservationProcessor implements Processor {

    private final Logger log = LoggerFactory.getLogger(ReservationProcessor.class);

    public void process(Exchange exchange) throws Exception {

        RouteUtils.messageLog("ReservationProcessor", exchange.getIn());

        Person p = (Person)exchange.getIn().getHeader("person");
        Booking b = (Booking)exchange.getIn().getHeader("booking");
        Error e = (com.ba.captwo.eda.demo.model.Error)exchange.getIn().getHeader("err");

        Reservation r = new Reservation();
        r.setBooking(b);
        r.setPerson(p);
        r.setError(e);

        Response response = Response.status(Response.Status.OK).entity(RouteUtils.toJson(r)).build();

        exchange.getIn().setBody(response);
    }
}