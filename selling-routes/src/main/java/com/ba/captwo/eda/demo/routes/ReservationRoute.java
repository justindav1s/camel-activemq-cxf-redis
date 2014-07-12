package com.ba.captwo.eda.demo.routes;

import com.ba.captwo.eda.demo.model.Booking;
import com.ba.captwo.eda.demo.model.Person;
import com.ba.captwo.eda.demo.processors.RESTProducerEndpoint;
import com.ba.captwo.eda.demo.processors.ReservationAggregator;
import com.ba.captwo.eda.demo.resources.ReservationResource;
import org.apache.camel.Processor;
import java.util.concurrent.Executors;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.Exchange;
import org.apache.camel.component.cxf.common.message.CxfConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


/**
 * Created by u760245 on 05/07/2014.
 */
@Component("ReservationRoute")
public class ReservationRoute extends RouteBuilder {

    private final Logger log = LoggerFactory.getLogger(ReservationRoute.class);


    private String uri = "cxfrs:bean:reservationServer";


    @Value("${remote.rest.server}") public String remote_rest_server;

    private String createPersonQuery = "/selling/person/create;lname=${in.header.lname};fname=${in.header.fname};address=${in.header.address};city=${in.header.city}";
    private String createBookingQuery = "/selling/booking/create;pid=0;fnum=${in.header.fnum};ticks=${in.header.ticks};cab=${in.header.cab}";


    @Override
    public void configure() throws Exception {

        from(uri).startupOrder(3)                                                               .routeId("MakeReservationParallel")
                .to("log:input")                                                                .id("ReservationRoute : Log input")
                .to("RESTRequestProcessor")                                                     .id("ReservationRoute : Split Request Params for each Service")
                .multicast(new ReservationAggregator())                                         .id("ReservationRoute : Aggregate Service Responses")
                .parallelProcessing().timeout(1500)
                .to("direct:CallPersonService","direct:CallBookingService")                     .id("ReservationRoute : Multicast to REST Services")
                .end()
                .to("ReservationProcessor").id("Compose Response");

        from("direct:CallPersonService").startupOrder(1)                                        .routeId("CallPersonServiceRoute")
                .setHeader(Exchange.HTTP_BASE_URI, constant(remote_rest_server))                .id("PersonService : Set URL")
                .setHeader(Exchange.HTTP_QUERY, simple(createPersonQuery))                      .id("PersonService : Set Query String")
                .setHeader(Exchange.HTTP_METHOD, constant("GET"))                               .id("PersonService : Set HTTP Method")
                .setHeader(RESTProducerEndpoint.RESPONSE_TYPE, constant(Person.class.getName())).id("PersonService : Set Response Type")
                .to("RESTProducerEndpoint")                                                     .id("PersonService : Send REST Request");

        from("direct:CallBookingService").startupOrder(2)                                       .routeId("CallBookingService")
                .setHeader(Exchange.HTTP_BASE_URI, constant(remote_rest_server))                .id("BookingService : Set URL")
                .setHeader(Exchange.HTTP_QUERY, simple(createBookingQuery))                     .id("BookingService : Set Query String")
                .setHeader(Exchange.HTTP_METHOD, constant("GET"))                               .id("BookingService : Set HTTP Method")
                .setHeader(RESTProducerEndpoint.RESPONSE_TYPE, constant(Booking.class.getName())).id("BookingService : Set Response Type")
                .to("RESTProducerEndpoint")                                                      .id("BookingService : Send REST Request");

    }
}
