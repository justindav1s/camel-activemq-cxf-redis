package com.ba.captwo.eda.demo.routes;

import com.ba.captwo.eda.demo.model.Booking;
import com.ba.captwo.eda.demo.model.Person;
import com.ba.captwo.eda.demo.processors.RESTProducerEndpoint;
import com.ba.captwo.eda.demo.processors.ReservationAggregator;
import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


/**
 * Created by u760245 on 05/07/2014.
 */
@Component("PersonServiceRoute")
public class PersonServiceRoute extends RouteBuilder {

    private final Logger log = LoggerFactory.getLogger(PersonServiceRoute.class);


    private String uri = "cxfrs:bean:personServer";

    @Override
    public void configure() throws Exception {

        from(uri).startupOrder(3)                                                               .routeId("MakeReservationParallel")
                .to("log:input")                                                                .id("PersonServiceRoute : Log input")
                .to("RESTRequestProcessor")                                                     .id("PersonServiceRoute : Parse Request Params")
                .setBody(constant("OK"))                                                        .id("PersonServiceRoute : Respond to Client")
                .end();

    }
}
