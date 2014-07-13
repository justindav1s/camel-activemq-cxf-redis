package com.ba.captwo.eda.demo.processors;

import com.ba.captwo.eda.demo.model.Booking;
import com.ba.captwo.eda.demo.model.Error;
import com.ba.captwo.eda.demo.model.Person;
import com.ba.captwo.eda.demo.model.Reservation;
import com.ba.captwo.eda.demo.utils.RouteUtils;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.ws.rs.core.Response;

/**
 * Created by u760245 on 06/07/2014.
 */
@Component("PersonResponseProcessor")
public class PersonResponseProcessor implements Processor {

    private final Logger log = LoggerFactory.getLogger(PersonResponseProcessor.class);

    public void process(Exchange exchange) throws Exception {

        log.debug("***THREAD : "+ Thread.currentThread().toString());

        RouteUtils.messageLog("PersonResponseProcessor", exchange.getIn());

        Response response = null;

        if (exchange.getIn().getBody() != null)    {

            Object body =  exchange.getIn().getBody();
            log.debug("Body Type  : "+body.getClass());
            response = Response.status(Response.Status.OK).entity(body.toString()).build();

        }
        else    {
            response = Response.status(Response.Status.OK).entity("NULL RESPONSE").build();
        }

        exchange.getOut().setBody(response);
    }
}