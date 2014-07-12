package com.ba.captwo.eda.demo.routes;

import com.ba.captwo.eda.demo.model.Booking;
import com.ba.captwo.eda.demo.model.Person;
import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


/**
 * Created by u760245 on 05/07/2014.
 */
//@Component("PersonCoreserviceRoute")
public class PersonCoreserviceRoute extends RouteBuilder {

    private final Logger log = LoggerFactory.getLogger(PersonCoreserviceRoute.class);

    private String uri = "direct-vm:PersonCoreService";

    @Override
    public void configure() throws Exception {

        from(uri).startupOrder(1)                                                         .routeId("PersonCoreserviceRoute")
                .to("log:input")                                                          .id("PersonCoreserviceRoute : Log input")
                .beanRef("PersonCoreServiceBean", "readPerson(${in.header.pid})")         .id("PersonCoreserviceRoute : execute")
                .to("log:output");

    }
}