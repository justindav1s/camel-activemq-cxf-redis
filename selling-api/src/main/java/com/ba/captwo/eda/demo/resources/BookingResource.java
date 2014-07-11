package com.ba.captwo.eda.demo.resources;

import com.ba.captwo.eda.demo.model.Booking;
import com.ba.captwo.eda.demo.model.Error;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.ArrayList;

/**
 * Created by u760245 on 05/07/2014.
 */
@Path("/booking")
public interface BookingResource {

    @GET
    @Produces("application/json")
    @Consumes("application/json")
    @Path("/create")
    public Response createBooking(
            @MatrixParam("pid") int pid,
            @MatrixParam("fnum") String fnum,
            @MatrixParam("ticks") int tickets,
            @MatrixParam("cab") String cabin);

    @GET
    @Produces("application/json")
    @Consumes("application/json")
    @Path("/read")
    public Response readBooking(
            @MatrixParam("pnr") int pnr);

    @GET
    @Produces("application/json")
    @Consumes("application/json")
    @Path("/update")
    public Response updateBooking(
            @MatrixParam("pnr") int pnr,
            @MatrixParam("pid") int pid,
            @MatrixParam("fnum") String fnum,
            @MatrixParam("ticks") int tickets,
            @MatrixParam("cab") String cabin);

    @GET
    @Produces("application/json")
    @Consumes("application/json")
    @Path("/delete")
    public Response deleteBooking(
            @MatrixParam("pnr") int pnr);

    @GET
    @Produces("application/json")
    @Consumes("application/json")
    @Path("/list")
    public Response listBookings();
}
