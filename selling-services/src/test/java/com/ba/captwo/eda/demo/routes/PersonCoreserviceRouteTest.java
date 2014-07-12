package com.ba.captwo.eda.demo.routes;

import com.ba.captwo.eda.demo.model.Person;
import org.apache.camel.CamelContext;
import org.apache.camel.EndpointInject;
import org.apache.camel.ProducerTemplate;
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
@ContextConfiguration(locations = { "/camel_beans.xml" })
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@MockEndpoints("log:*")
@DisableJmx(false)


public class PersonCoreserviceRouteTest {

    private final static Logger log = LoggerFactory.getLogger(PersonCoreserviceRouteTest.class);

    @Autowired
    protected CamelContext camelContext;

    @EndpointInject(uri = "mock:log:output")
    protected MockEndpoint mockEndPoint;

    @Test
    public void testPositive() throws Exception {

        int pid = 1921;

        Person p = new Person();
        p.setPersonID(pid);

        mockEndPoint.setExpectedMessageCount(1);
        mockEndPoint.message(0).body().isNotNull();
        mockEndPoint.message(0).header("pid").isEqualTo(pid);
        mockEndPoint.message(0).body().isEqualTo(p);

        ProducerTemplate t = camelContext.createProducerTemplate();
        t.sendBodyAndHeader("direct-vm:PersonCoreService", pid, "pid", pid);
        MockEndpoint.assertIsSatisfied(camelContext);
    }
}
