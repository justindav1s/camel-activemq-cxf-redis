package com.ba.captwo.eda.demo.routes;

import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


/**
 * Created by u760245 on 05/07/2014.
 */
public class PersonServiceRoutes extends RouteBuilder {

    private final Logger log = LoggerFactory.getLogger(PersonServiceRoutes.class);


    private String uri = "cxfrs:bean:personServer";

    @Override
    public void configure() throws Exception {

        from(uri).startupOrder(2)                                                               .routeId("MakeReservationParallel")
                .to("log:input")                                                                .id("PersonServiceRoute : Log input")
                .to("RESTRequestProcessor")
                .to("direct:PersonOpsCBR")
                .to("log:output")
                .to("PersonResponseProcessor");

        from("direct:PersonOpsCBR").startupOrder(1)
            .choice()
                .when(header(Exchange.HTTP_PATH).isEqualTo("/create"))
                    .to("direct-vm:selling.services.person.create")
                .when(header(Exchange.HTTP_PATH).isEqualTo("/read"))
                    .to("direct-vm:selling.services.person.read")
                .when(header(Exchange.HTTP_PATH).isEqualTo("/update"))
                    .to("direct-vm:selling.services.person.update")
                .when(header(Exchange.HTTP_PATH).isEqualTo("/delete"))
                    .to("direct-vm:selling.services.person.read")
                .when(header(Exchange.HTTP_PATH).isEqualTo("/list"))
                    .to("direct-vm:selling.services.person.list");

    }
}
