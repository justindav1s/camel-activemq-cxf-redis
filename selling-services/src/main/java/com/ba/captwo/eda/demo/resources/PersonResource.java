package com.ba.captwo.eda.demo.resources;

import com.ba.captwo.eda.demo.db.PersonDAO;
import com.ba.captwo.eda.demo.model.*;
import com.ba.captwo.eda.demo.model.Error;
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
@Component("PersonResource")
@Path("/person")
public class PersonResource {

    private final Logger log = LoggerFactory.getLogger(PersonResource.class);

    @Autowired
    PersonDAO personDAO;


    @GET
    @Produces("application/json")
    @Consumes("application/json")
    @Path("/create")
    public Response createPerson(
            @MatrixParam("fname") String fname,
            @MatrixParam("lname") String lname,
            @MatrixParam("address") String address,
            @MatrixParam("city") String city)    {

        log.debug("createPerson");
        log.debug("fname : " + fname);
        log.debug("lname : " + lname);
        log.debug("address : " + address);
        log.debug("city : " + city);

        Response response = null;

        if ((fname == null) || (lname == null) || (address == null))  {
            Error err = new Error();
            err.setMessage("Incomplate Request");
            return Response.status(Response.Status.BAD_REQUEST).entity(err).build();
        }

        Person p = new Person();
        p.setFirstName(fname);
        p.setLastName(lname);
        p.setAddress(address);
        p.setCity(city);

        try {
            p = personDAO.createPerson(p);
            response = Response.status(Response.Status.OK).entity(p).build();
        }
        catch (Exception e) {
            com.ba.captwo.eda.demo.model.Error err = new Error();
            err.setMessage(e.getMessage());
            response = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(err).build();
        }

        return response;
    }

    @GET
    @Produces("application/json")
    @Consumes("application/json")
    @Path("/read")
    public Response readPerson(
            @MatrixParam("pid") int pid)    {

        log.debug("readPerson");
        log.debug("pid : " + pid);

        Response response = null;

        Person p = null;

        try {
            p = personDAO.readPerson(pid);
            response = Response.status(Response.Status.OK).entity(p).build();
        }
        catch (Exception e) {
            com.ba.captwo.eda.demo.model.Error err = new Error();
            err.setMessage(e.getMessage());
            response = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(err).build();
        }

        return response;
    }

    @GET
    @Produces("application/json")
    @Consumes("application/json")
    @Path("/update")
    public Response updatePerson(
            @MatrixParam("pid") int pid,
            @MatrixParam("fname") String fname,
            @MatrixParam("lname") String lname,
            @MatrixParam("address") String address,
            @MatrixParam("city") String city)    {

        log.debug("updatePerson");
        log.debug("pid : " + pid);
        log.debug("fname : " + fname);
        log.debug("lname : " + lname);
        log.debug("address : " + address);
        log.debug("city : " + city);


        Response response = null;

        if ((fname == null) || (lname == null) || (address == null))  {
            Error err = new Error();
            err.setMessage("Incomplate Request");
            return Response.status(Response.Status.BAD_REQUEST).entity(err).build();
        }

        Person p = new Person();
        p.setPersonID(pid);
        p.setFirstName(fname);
        p.setLastName(lname);
        p.setAddress(address);
        p.setCity(city);

        try {
            p = personDAO.updatePerson(p);
            response = Response.status(Response.Status.OK).entity(p).build();
        }
        catch (Exception e) {
            com.ba.captwo.eda.demo.model.Error err = new Error();
            err.setMessage(e.getMessage());
            response = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(err).build();
        }

        return response;
    }

    @GET
    @Produces("application/json")
    @Consumes("application/json")
    @Path("/delete")
    public Response deletePerson(
            @MatrixParam("pid") int pid)    {

        log.debug("deletePerson");
        log.debug("pid : " + pid);

        Response response = null;

        Person p = null;

        try {
            personDAO.deletePerson(pid);
            response = Response.status(Response.Status.OK).entity("OK").build();
        }
        catch (Exception e) {
            com.ba.captwo.eda.demo.model.Error err = new Error();
            err.setMessage(e.getMessage());
            response = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(err).build();
        }

        return response;
    }

    @GET
    @Produces("application/json")
    @Consumes("application/json")
    @Path("/list")
    public Response listPersons()    {

        Response response = null;


        try {
            ArrayList<Person> persons = personDAO.listPersons();
            response = Response.status(Response.Status.OK).entity(persons).build();
        }
        catch (Exception e) {
            com.ba.captwo.eda.demo.model.Error err = new Error();
            err.setMessage(e.getMessage());
            response = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(err).build();
        }

        return response;
    }
}
