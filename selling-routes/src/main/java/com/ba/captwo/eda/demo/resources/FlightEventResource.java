package com.ba.captwo.eda.demo.resources;

import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.xml.ws.Response;

/**
 * Created by u760245 on 08/07/2014.
 */
@Component("FlightEventResource")
public class FlightEventResource {

    @GET
    @Produces("application/json")
    @Consumes("application/json")
    @Path("/send")
    public Response sendFlightEvent(
            @MatrixParam("fnum") String fnum,
            @MatrixParam("event") String event,
            @MatrixParam("time") String timestamp){
        return null;
    }
}
