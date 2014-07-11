package com.ba.captwo.eda.demo.resources;

import com.ba.captwo.eda.demo.model.Error;
import com.ba.captwo.eda.demo.model.Flight;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.ArrayList;

/**
 * Created by u760245 on 05/07/2014.
 */

@Path("/flight")
public interface FlightResource {

    @GET
    @Produces("application/json")
    @Consumes("application/json")
    @Path("/create")
    public Response createFlight(
            @MatrixParam("fnum") String fnum,
            @MatrixParam("orig") String orig,
            @MatrixParam("dest") String dest);

    @GET
    @Produces("application/json")
    @Consumes("application/json")
    @Path("/read")
    public Response readFlight(
            @MatrixParam("fnum") String fnum);

    @GET
    @Produces("application/json")
    @Consumes("application/json")
    @Path("/update")
    public Response updateFlight(
            @MatrixParam("fnum") String fnum,
            @MatrixParam("orig") String orig,
            @MatrixParam("dest") String dest);

    @GET
    @Produces("application/json")
    @Consumes("application/json")
    @Path("/delete")
    public Response deleteFlight(
            @MatrixParam("fnum") String fnum);

    @GET
    @Produces("application/json")
    @Consumes("application/json")
    @Path("/list")
    public Response listFlights();
}
