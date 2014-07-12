package com.ba.captwo.eda.demo.routes;

import com.ba.captwo.eda.demo.model.Booking;
import com.ba.captwo.eda.demo.model.Person;
import org.apache.camel.CamelContext;
import org.apache.camel.EndpointInject;
import org.apache.camel.Produce;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.ValueBuilder;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.test.spring.CamelSpringJUnit4ClassRunner;
import org.apache.camel.test.spring.DisableJmx;
import org.apache.camel.test.spring.MockEndpoints;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;

/**
 * Created by u760245 on 12/07/2014.
 */
@RunWith(CamelSpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/beans_remote_db.xml" })
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@MockEndpoints("log:*")
@DisableJmx(false)


public class PersonCoreserviceRoute2IT {

    private final static Logger log = LoggerFactory.getLogger(PersonCoreserviceRoute2IT.class);

    @Autowired
    protected CamelContext camelContext;

    @EndpointInject(uri = "mock:log:output")
    protected MockEndpoint mockLog;

    @Test
    public void testPositive() throws Exception {

        log.info("****** camelContext : "+ camelContext);
        mockLog.setExpectedMessageCount(1);
        mockLog.message(0).body().isNotNull();
        mockLog.message(0).header("pid").isEqualTo(1921);
        ValueBuilder vb = mockLog.message(0).body().convertTo(Booking.class);

        ProducerTemplate t = camelContext.createProducerTemplate();
        t.sendBodyAndHeader("direct-vm:PersonCoreService", 1921, "pid", 1921);
        MockEndpoint.assertIsSatisfied(camelContext);
    }
}
