package com.ba.captwo.eda.demo.routes;

import org.apache.camel.ExchangePattern;
import org.apache.camel.builder.RouteBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

/**
 * Created by u760245 on 08/07/2014.
 */

@Component("FlightEventComsumerRoute")
public class FlightEventConsumerRoute extends RouteBuilder {

    private final Logger log = LoggerFactory.getLogger(FlightEventConsumerRoute.class);

    @Value("${redis.uri}") public String redis_uri;
    @Value("${flight.events.input.endpoint}") private String input_uri;
    @Value("${flight.events.amq.topic}") private String inputTopic;
    @Value("${flight.events.amq.queue}") private String inputQueue;
    @Value("${flight.events.redis.channel}") private String publishChannel;


    @Override
    public void configure() throws Exception {

        from(input_uri).routeId("CXFRS-Consume").startupOrder(2)        .id("CXFRS-Consume : Receive Request")
                .to("log:input")                                        .id("CXFRS-Consume : Log input")
                .to("RESTRequestProcessor")                             .id("CXFRS-Consume : Transform REST params to Message Headers")
                .to(ExchangePattern.InOnly, inputTopic)                 .id("CXFRS-Consume : Send Message to JMS Topic")
                .to(ExchangePattern.InOnly, inputQueue)                 .id("CXFRS-Consume : Send Message to JMS Queue")
                .setBody(constant("OK"))                                .id("CXFRS-Consume : Respond to Client")
                .end();

        // > JMS Destination >

        from(inputTopic).routeId("SendToRedis").startupOrder(1)                     .id("SendToRedis")
                .to("log:input")                                                    .id("SendToRedis : Log input")
                .setHeader("CamelRedis.Command", constant("HSET"))                  .id("SendToRedis : Set Redis COMMAND")
                .setHeader("CamelRedis.Key", simple("${in.header.fnum}"))           .id("SendToRedis : Set Redis KEY")
                .setHeader("CamelRedis.Field", simple("${in.header.event}"))        .id("SendToRedis : Set Redis FIELD")
                .setHeader("CamelRedis.Value", simple("${in.header.time}"))         .id("SendToRedis : Set Redis VALUE")
                .setHeader("CamelRedis.Timeout", constant(12*60*60))                .id("SendToRedis : Set Redis MSG TIMEOUT")
                .to(redis_uri)                                                      .id("SendToRedis : Send to Redis")
                .setHeader("CamelRedis.Command", constant("PUBLISH"))               .id("SendToRedis : Set Redis COMMAND")
                .setHeader("CamelRedis.Channel", constant(publishChannel))          .id("SendToRedis : Set Redis KEY")
                .setHeader("CamelRedis.Message", simple("${in.header.fnum}"))       .id("SendToRedis : Set Redis FIELD")
                .to(redis_uri)                                                      .id("SendToRedis : Send to Redis")
                .end();

    }
}
