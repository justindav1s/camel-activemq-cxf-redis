package com.ba.captwo.eda.demo.resources;

import com.ba.captwo.eda.demo.db.BookingDAO;
import com.ba.captwo.eda.demo.model.Error;
import com.ba.captwo.eda.demo.model.Booking;
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
@Component("BookingResource")
@Path("/booking")
public class BookingResource {

    private final Logger log = LoggerFactory.getLogger(BookingResource.class);

    @Autowired
    BookingDAO bookingDAO;


    @GET
    @Produces("application/json")
    @Consumes("application/json")
    @Path("/create")
    public Response createBooking(
            @MatrixParam("pid") int pid,
            @MatrixParam("fnum") String fnum,
            @MatrixParam("ticks") int tickets,
            @MatrixParam("cab") String cabin)    {

        log.debug("createBooking");
        log.debug("pid : " + pid);
        log.debug("fnum : " + fnum);
        log.debug("tickets : " + tickets);
        log.debug("cabin : " + cabin);


        if ((fnum == null) || (cabin == null))  {
            Error err = new Error();
            err.setMessage("Incomplate Request");
            return Response.status(Response.Status.BAD_REQUEST).entity(err).build();
        }

        Response response = null;

        Booking p = new Booking();
        p.setPersonID(pid);
        p.setFlightNum(fnum);
        p.setTickets(tickets);
        p.setCabin(cabin);

        try {
            p = bookingDAO.createBooking(p);
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
    public Response readBooking(
            @MatrixParam("pnr") int pnr)    {

        log.debug("readBooking");
        log.debug("pnr : " + pnr);


        Response response = null;

        if ((pnr == 0))  {
            Error err = new Error();
            err.setMessage("Incomplate Request");
            return Response.status(Response.Status.BAD_REQUEST).entity(err).build();
        }

        Booking p = null;

        try {
            p = bookingDAO.readBooking(pnr);
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
    public Response updateBooking(
            @MatrixParam("pnr") int pnr,
            @MatrixParam("pid") int pid,
            @MatrixParam("fnum") String fnum,
            @MatrixParam("ticks") int tickets,
            @MatrixParam("cab") String cabin)    {

        log.debug("updateBooking");
        log.debug("pnr : " + pnr);
        log.debug("pid : " + pid);
        log.debug("fnum : " + fnum);
        log.debug("tickets : " + tickets);
        log.debug("cabin : " + cabin);

        Response response = null;

        if ((fnum == null) || (cabin == null))  {
            Error err = new Error();
            err.setMessage("Incomplate Request");
            return Response.status(Response.Status.BAD_REQUEST).entity(err).build();
        }

        Booking p = new Booking();
        p.setBookingId(pnr);
        p.setPersonID(pid);
        p.setFlightNum(fnum);
        p.setTickets(tickets);
        p.setCabin(cabin);

        try {
            p = bookingDAO.updateBooking(p);
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
    public Response deleteBooking(
            @MatrixParam("pnr") int pnr)    {

        log.debug("deleteBooking");
        log.debug("pnr : " + pnr);

        Response response = null;

        if ((pnr == 0))  {
            Error err = new Error();
            err.setMessage("Incomplate Request");
            return Response.status(Response.Status.BAD_REQUEST).entity(err).build();
        }

        Booking p = null;

        try {
            bookingDAO.deleteBooking(pnr);
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
    public Response listBookings()    {

        Response response = null;

        Booking p = null;

        try {
            ArrayList<Booking> bookings = bookingDAO.listBookings();
            response = Response.status(Response.Status.OK).entity(bookings).build();
        }
        catch (Exception e) {
            Error err = new Error();
            err.setMessage(e.getMessage());
            response = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(err).build();
        }

        return response;
    }
}
