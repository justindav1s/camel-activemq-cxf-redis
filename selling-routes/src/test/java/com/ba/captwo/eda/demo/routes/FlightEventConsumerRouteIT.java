package com.ba.captwo.eda.demo.routes;

import com.ba.captwo.eda.demo.model.FlightEvent;
import com.ba.captwo.eda.demo.model.FlightStates;
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
import java.text.SimpleDateFormat;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by u760245 on 08/07/2014.
 */
public class FlightEventConsumerRouteIT {


    private static String endpointUrl;
    private final static Logger log = LoggerFactory.getLogger(FlightEventConsumerRouteIT.class);

    @BeforeClass
    public static void beforeClass() {
        log.info("***** System.getProperty : " + System.getProperty("service.url"));
        endpointUrl = System.getProperty("service.url");

        if (endpointUrl == null) {
            endpointUrl = "http://localhost:8080/routes";
        }

        log.info("***** endpointUrl : " + endpointUrl);
    }

    @Test
    public void testSendFlightEvent() throws Exception {
        log.info("***** Test SendFlightEvent");

        FlightEvent fe = new FlightEvent();
        fe.setFlightNum("BA009");
        fe.setTimestamp(System.currentTimeMillis());
        fe.setEvent(FlightStates.PLANNED);

        String uri = "/flightevent/send;fnum=" + fe.getFlightNum() +
                                      ";event=" + fe.getEvent() +
                                      ";time=" + fe.getTimestamp();

        log.info("***** Calling : "+uri);

        WebClient client = WebClient.create(endpointUrl + uri);
        Response r = client.accept("application/json").get();
        assertEquals(Response.Status.OK.getStatusCode(), r.getStatus());
        String value = IOUtils.toString((InputStream) r.getEntity());
        log.info("***** Test SendFlightEvent Response : " + value);
        assertNotNull(value);
        assertEquals("OK", value);

    }
}
