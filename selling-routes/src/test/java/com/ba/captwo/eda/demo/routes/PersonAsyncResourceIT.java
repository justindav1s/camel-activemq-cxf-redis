package com.ba.captwo.eda.demo.routes;

import com.ba.captwo.eda.demo.model.Person;
import org.apache.cxf.helpers.IOUtils;
import org.apache.cxf.jaxrs.client.WebClient;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.core.Response;
import java.io.InputStream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class PersonAsyncResourceIT {
    private static String endpointUrl;
    private final static Logger log = LoggerFactory.getLogger(PersonAsyncResourceIT.class);

    Person myPerson;

    @BeforeClass
    public static void beforeClass() {
        log.info("***** System.getProperty : " + System.getProperty("service.url"));
        endpointUrl = System.getProperty("service.url");

        if(endpointUrl == null) {
            endpointUrl = "http://localhost:8080/routes";
        }

        log.info("***** endpointUrl : " + endpointUrl);
    }



    @Test
    public void testRead() throws Exception {

        log.info("Test READ START");

        String uri = endpointUrl + "/personasync/read;pid="+myPerson.getPersonID();

        log.info("***** URI :" + endpointUrl + uri);

        WebClient client = WebClient.create(uri);
        Response r = client.accept("application/json").get();
        assertEquals(Response.Status.OK.getStatusCode(), r.getStatus());
        String value = IOUtils.toString((InputStream)r.getEntity());
        log.info("Read response : "+value);

        ObjectMapper mapper = new ObjectMapper();
        Person p2 = mapper.readValue(value, Person.class);
        log.info("Read Person : "+p2.toString());

        assertNotNull(p2);
        assertNotNull(p2.getFirstName());
        assertNotNull(p2.getLastName());
        assertNotNull(p2.getAddress());
        assertNotNull(p2.getCity());

        log.info("Test READ END");

    }

    @Before
    public void createPerson()  throws Exception {

        log.info("createPerson START");

        Person p = buildPerson();

        String uri = "/personasync/create;fname="+p.getFirstName()+";lname="+p.getLastName()+";address="+p.getAddress()+";city="+p.getCity();

        log.info("***** URI :" + endpointUrl + uri);

        WebClient client = WebClient.create(endpointUrl + uri);
        Response r = client.accept("application/json").get();
        String value = IOUtils.toString((InputStream) r.getEntity());
        log.info("Create response : " + value);

        ObjectMapper mapper = new ObjectMapper();
        p = mapper.readValue(value, Person.class);
        log.info("Create pd : " + p.getPersonID());
        myPerson = p;
        log.info("createPerson END");
    }


    @After
    public void deletePerson()  throws Exception {

        log.info("deletePerson START");

        String uri = "/personasync/delete;;pid="+myPerson.getPersonID();

        log.info("***** URI :" + endpointUrl + uri);

        WebClient client = WebClient.create(endpointUrl + uri);
        Response r = client.accept("application/json").get();
        String value = IOUtils.toString((InputStream) r.getEntity());
        log.info("delete response : " + value);

        log.info("deletePerson END");

    }

    private Person buildPerson()    {
        Person testPerson = new Person();
        testPerson.setLastName("Fry");
        testPerson.setFirstName("Stephen");
        testPerson.setAddress("Norwich");
        testPerson.setCity("Norfolk");
        return testPerson;
    }
}
