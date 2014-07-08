package com.ba.captwo.eda.demo.resources;

import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.xml.ws.Response;

/**
 * Created by u760245 on 05/07/2014.
 */
@Component("ReservationResource")
public class ReservationResource {

    @GET
    @Produces("application/json")
    @Consumes("application/json")
    @Path("/create")
    public Response createReservation(
            @MatrixParam("fname") String fname,
            @MatrixParam("lname") String lname,
            @MatrixParam("address") String address,
            @MatrixParam("city") String city,
            @MatrixParam("fnum") String fnum,
            @MatrixParam("ticks") int tickets,
            @MatrixParam("cab") String cabin){
        return null;
    }
}
