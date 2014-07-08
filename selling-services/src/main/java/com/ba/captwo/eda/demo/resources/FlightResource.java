package com.ba.captwo.eda.demo.resources;

import com.ba.captwo.eda.demo.db.FlightDAO;
import com.ba.captwo.eda.demo.db.FlightDAO;
import com.ba.captwo.eda.demo.model.Error;
import com.ba.captwo.eda.demo.model.Flight;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.ArrayList;

/**
 * Created by u760245 on 05/07/2014.
 */
@Component("FlightResource")
@Path("/flight")
public class FlightResource {

    private final Logger log = LoggerFactory.getLogger(FlightResource.class);

    @Autowired
    FlightDAO flightDAO;


    @GET
    @Produces("application/json")
    @Consumes("application/json")
    @Path("/create")
    public Response createFlight(
            @MatrixParam("fnum") String fnum,
            @MatrixParam("orig") String orig,
            @MatrixParam("dest") String dest)    {

        log.debug("createFlight");
        log.debug("fnum : " + fnum);
        log.debug("orig : " + orig);
        log.debug("dest : " + dest);

        Response response = null;

        if ((fnum == null) || (orig == null) || (dest == null))  {
            Error err = new Error();
            err.setMessage("Incomplate Request");
            return Response.status(Response.Status.BAD_REQUEST).entity(err).build();
        }

        Flight p = new Flight();
        p.setFlightnum(fnum);
        p.setOrigin(orig);
        p.setDestination(dest);

        try {
            p = flightDAO.createFlight(p);
            response = Response.status(Response.Status.OK).entity(p).build();
        }
        catch (Exception e) {
            Error err = new Error();
            err.setMessage(e.getMessage());
            response = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(err).build();
        }

        return response;
    }

    @GET
    @Produces("application/json")
    @Consumes("application/json")
    @Path("/read")
    public Response readFlight(
            @MatrixParam("fnum") String fnum)    {

        log.debug("deleteFlight");
        log.debug("fnum : " + fnum);

        Response response = null;

        if ((fnum == null))  {
            Error err = new Error();
            err.setMessage("Incomplate Request");
            return Response.status(Response.Status.BAD_REQUEST).entity(err).build();
        }

        Flight p = null;

        try {
            p = flightDAO.readFlight(fnum);
            response = Response.status(Response.Status.OK).entity(p).build();
        }
        catch (Exception e) {
            Error err = new Error();
            err.setMessage(e.getMessage());
            response = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(err).build();
        }

        return response;
    }

    @GET
    @Produces("application/json")
    @Consumes("application/json")
    @Path("/update")
    public Response updateFlight(
            @MatrixParam("fnum") String fnum,
            @MatrixParam("orig") String orig,
            @MatrixParam("dest") String dest)    {

        log.debug("updateFlight");
        log.debug("fnum : " + fnum);
        log.debug("orig : " + orig);
        log.debug("dest : " + dest);

        Response response = null;

        if ((fnum == null) || (orig == null) || (dest == null))  {
            Error err = new Error();
            err.setMessage("Incomplate Request");
            return Response.status(Response.Status.BAD_REQUEST).entity(err).build();
        }

        Flight p = new Flight();
        p.setFlightnum(fnum);
        p.setOrigin(orig);
        p.setDestination(dest);

        try {
            p = flightDAO.updateFlight(p);
            response = Response.status(Response.Status.OK).entity(p).build();
        }
        catch (Exception e) {
            Error err = new Error();
            err.setMessage(e.getMessage());
            response = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(err).build();
        }

        return response;
    }

    @GET
    @Produces("application/json")
    @Consumes("application/json")
    @Path("/delete")
    public Response deleteFlight(
            @MatrixParam("fnum") String fnum)    {

        log.debug("deleteFlight");
        log.debug("fnum : " + fnum);

        Response response = null;

        if ((fnum == null))  {
            Error err = new Error();
            err.setMessage("Incomplate Request");
            return Response.status(Response.Status.BAD_REQUEST).entity(err).build();
        }

        Flight p = null;

        try {
            flightDAO.deleteFlight(fnum);
            response = Response.status(Response.Status.OK).entity("OK").build();
        }
        catch (Exception e) {
            Error err = new Error();
            err.setMessage(e.getMessage());
            response = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(err).build();
        }

        return response;
    }

    @GET
    @Produces("application/json")
    @Consumes("application/json")
    @Path("/list")
    public Response listFlights()    {

        Response response = null;

        Flight p = null;

        try {
            ArrayList<Flight> flights = flightDAO.listFlights();
            response = Response.status(Response.Status.OK).entity(flights).build();
        }
        catch (Exception e) {
            Error err = new Error();
            err.setMessage(e.getMessage());
            response = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(err).build();
        }

        return response;
    }
}
