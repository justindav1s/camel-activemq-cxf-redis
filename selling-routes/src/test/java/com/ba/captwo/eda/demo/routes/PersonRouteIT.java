package com.ba.captwo.eda.demo.routes;

import com.ba.captwo.eda.demo.model.Booking;
import com.ba.captwo.eda.demo.model.Person;
import com.ba.captwo.eda.demo.model.Reservation;
import org.apache.cxf.helpers.IOUtils;
import org.apache.cxf.jaxrs.client.WebClient;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.core.Response;
import java.io.InputStream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by u760245 on 06/07/2014.
 */
public class PersonRouteIT {

    private static String endpointUrl;
    private final static Logger log = LoggerFactory.getLogger(PersonRouteIT.class);

    @BeforeClass
    public static void beforeClass() {
        endpointUrl = System.getProperty("service.url");
    }

    @Test
    public void testReadPerson() throws Exception {
        log.info("***** Test testReadPerson");

        String uri = "/person/read;pid=1921";

        log.info("***** Calling : "+uri);

        WebClient client = WebClient.create(endpointUrl + uri);
        Response r = client.accept("application/json").get();
        assertEquals(Response.Status.OK.getStatusCode(), r.getStatus());
        String value = IOUtils.toString((InputStream) r.getEntity());
        log.info("***** Test testReadPerson : " + value);

        /**
        ObjectMapper mapper = new ObjectMapper();
        Person p = mapper.readValue(value, Person.class);
        log.info("Person : "+p.toString());
        assertNotNull(p.getFirstName());
        assertNotNull(p.getLastName());
         */
    }
}
