package com.ba.captwo.eda.demo.routes;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.test.junit4.CamelTestSupport;
import org.apache.camel.test.spring.CamelSpringTestSupport;
import org.junit.Test;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.context.support.StaticWebApplicationContext;

/**
 * Created by u760245 on 12/07/2014.
 */
public class PersonCoreserviceRouteIT extends CamelSpringTestSupport {

    @Test
    public void testPayloadIsTransformed()
            throws InterruptedException {
        MockEndpoint mockOut = getMockEndpoint("log:output");
        mockOut.setExpectedMessageCount(1);
        mockOut.message(0).body().isNotNull();
        template.sendBodyAndHeader("direct-vm:PersonCoreService", 1921, "pid", 1921);
        assertMockEndpointsSatisfied();
    }

    @Override
    protected AbstractApplicationContext createApplicationContext() {
        return new ClassPathXmlApplicationContext("classpath:beans_embedded_db.xml");
    }
}
