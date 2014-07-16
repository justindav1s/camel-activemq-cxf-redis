package com.ba.captwo.eda.demo.routes;

import com.ba.captwo.eda.demo.model.Booking;
import com.ba.captwo.eda.demo.model.Person;
import com.ba.captwo.eda.demo.model.Reservation;
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

/**
 * Created by u760245 on 06/07/2014.
 */
public class ReservationRouteIT {

    private static String endpointUrl;
    private final static Logger log = LoggerFactory.getLogger(ReservationRouteIT.class);

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
    public void testCreateReservation() throws Exception {
        log.info("***** Test CreateReservation");

        Booking b = new Booking();
        b.setPersonID(1);
        b.setFlightNum("BA009");
        b.setTickets(2);
        b.setCabin("J");

        Person p = new Person();
        p.setLastName("Windsor");
        p.setFirstName("William");
        p.setAddress("Kensington Palace");
        p.setCity("London");

        String uri = "/reservation/create;fname=" + p.getFirstName() +
                                        ";lname=" + p.getLastName() +
                                        ";address=" + p.getAddress() +
                                        ";city=" + p.getCity() +
                                        ";fnum=" + b.getFlightNum() +
                                        ";ticks=" + b.getTickets() +
                                        ";cab=" + b.getCabin();

        log.info("***** Calling : "+uri);

        WebClient client = WebClient.create(endpointUrl + uri);
        Response r = client.accept("application/json").get();
        assertEquals(Response.Status.OK.getStatusCode(), r.getStatus());
        String value = IOUtils.toString((InputStream) r.getEntity());
        log.info("***** Test CreateReservation : " + value);

        ObjectMapper mapper = new ObjectMapper();
        Reservation res = mapper.readValue(value, Reservation.class);
        log.info("Reservation : "+p.getPersonID());
        assertNotNull(res.getPerson());
        assertNotNull(res.getBooking());
    }
}
