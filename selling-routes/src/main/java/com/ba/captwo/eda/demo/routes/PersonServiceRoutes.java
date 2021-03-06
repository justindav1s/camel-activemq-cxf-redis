package com.ba.captwo.eda.demo.routes;


import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



/**
 * Created by u760245 on 05/07/2014.
 */
public class PersonServiceRoutes extends RouteBuilder {

    private final Logger log = LoggerFactory.getLogger(PersonServiceRoutes.class);

    private String uri = "cxfrs:bean:personServer";

    @Override
    public void configure() throws Exception {

        from(uri).startupOrder(2)                                                               .routeId("PersonServiceRoute")
                .to("log:input")                                                                .id("PersonServiceRoute : Log input")
                .to("RESTRequestProcessor")
                .to("PersonProcessor")
                .to("direct:PersonOpsCBR")                                                      .id("PersonServiceRoute : Route to Endpoint")
                .to("log:output")
                .to("PersonResponseProcessor")
                .end();

        from("direct:PersonOpsCBR").startupOrder(1)                                             .routeId("PersonServiceRoute : CBR")
            .choice()                                                                           .id("PersonServiceRoute : Which Operation ?")
                .when(header(Exchange.HTTP_PATH).isEqualTo("/create"))                          .id("PersonServiceRoute : when /create")
                    .to("direct-vm:selling.services.person.create")
                .when(header(Exchange.HTTP_PATH).isEqualTo("/read"))                            .id("PersonServiceRoute : when /read")
                    .to("direct-vm:selling.services.person.read")
                .when(header(Exchange.HTTP_PATH).isEqualTo("/update"))                          .id("PersonServiceRoute : when /update")
                    .to("direct-vm:selling.services.person.update")
                .when(header(Exchange.HTTP_PATH).isEqualTo("/delete"))                          .id("PersonServiceRoute : when /delete")
                    .to("direct-vm:selling.services.person.delete")
                .when(header(Exchange.HTTP_PATH).isEqualTo("/list"))                            .id("PersonServiceRoute : when /list")
                    .to("direct-vm:selling.services.person.list");

    }
}
