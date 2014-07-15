package com.ba.captwo.eda.demo.routes;

import com.ba.captwo.eda.demo.model.Person;
import org.apache.cxf.helpers.IOUtils;
import org.apache.cxf.jaxrs.client.WebClient;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.ws.rs.core.Response;
import java.io.InputStream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class PersonRouteResourceIT {
    private static String endpointUrl;
    private final static Logger log = LoggerFactory.getLogger(PersonRouteResourceIT.class);

    @BeforeClass
    public static void beforeClass() {
        endpointUrl = System.getProperty("service.url");
    }



    @Test
    public void testRead() throws Exception {

        log.info("Test READ");
        log.info("endpointUrl : "+endpointUrl);

        WebClient client = WebClient.create(endpointUrl + "/personroute/read;pid="+1921);
        Response r = client.accept("application/json").get();
        assertEquals(Response.Status.OK.getStatusCode(), r.getStatus());
        String value = IOUtils.toString((InputStream)r.getEntity());
        log.info("Read response : "+value);

        ObjectMapper mapper = new ObjectMapper();
        Person p = mapper.readValue(value, Person.class);
        log.info("Read Person : "+p.toString());

        assertNotNull(p);
        assertNotNull(p.getFirstName());
        assertNotNull(p.getLastName());
        assertNotNull(p.getAddress());
        assertNotNull(p.getCity());
    }

}
