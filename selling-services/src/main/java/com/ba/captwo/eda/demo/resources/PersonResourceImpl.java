package com.ba.captwo.eda.demo.resources;

import com.ba.captwo.eda.demo.coreservices.PersonService;
import com.ba.captwo.eda.demo.model.*;
import com.ba.captwo.eda.demo.model.Error;
import org.apache.cxf.jaxrs.ext.MessageContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import javax.servlet.http.HttpSession;
import javax.servlet.http.*;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import java.util.ArrayList;

/**
 * Created by u760245 on 05/07/2014.
 */
@Component("PersonResource")
public class PersonResourceImpl implements PersonResource{

    private final Logger log = LoggerFactory.getLogger(PersonResourceImpl.class);

    @Context
    HttpServletRequest request;

    @Autowired
    PersonService personService;

    public Response createPerson(
            String fname,
            String lname,
            String address,
            String city)    {

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
            p = personService.createPerson(p);
            response = Response.status(Response.Status.OK).entity(p).build();
        }
        catch (Exception e) {
            com.ba.captwo.eda.demo.model.Error err = new Error();
            err.setMessage(e.getMessage());
            response = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(err).build();
        }

        return response;
    }


    public Response readPerson(int pid)    {

        Person p = null;

        log.debug("readPerson");
        log.debug("readPerson REQUEST : "+request);
        log.debug("pid : " + pid);

        Response response = null;

        try {
            HttpSession session = (HttpSession)request.getSession();
            log.debug("readPerson SESSION : "+session);

            p = (Person)session.getAttribute("PERSON");

            if (p == null) {
                log.debug("Retrieving Person from DB");
                p = personService.readPerson(pid);
                session.setAttribute("PERSON", p);
            }
            else    {
                log.debug("Retrieved Person from Session");
            }

            response = Response.status(Response.Status.OK).entity(p).build();
        }
        catch (Exception e) {
            e.printStackTrace();
            com.ba.captwo.eda.demo.model.Error err = new Error();
            err.setMessage(e.getMessage());
            response = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(err).build();
        }

        return response;
    }


    public Response updatePerson(int pid, String fname, String lname, String address, String city)    {

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
            p = personService.updatePerson(p);
            response = Response.status(Response.Status.OK).entity(p).build();
        }
        catch (Exception e) {
            com.ba.captwo.eda.demo.model.Error err = new Error();
            err.setMessage(e.getMessage());
            response = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(err).build();
        }

        return response;
    }

    public Response deletePerson(int pid)    {

        log.debug("deletePerson");
        log.debug("pid : " + pid);

        Response response = null;

        Person p = null;

        try {
            personService.deletePerson(pid);
            response = Response.status(Response.Status.OK).entity("OK").build();
        }
        catch (Exception e) {
            com.ba.captwo.eda.demo.model.Error err = new Error();
            err.setMessage(e.getMessage());
            response = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(err).build();
        }

        return response;
    }

    public Response listPersons()    {

        Response response = null;


        try {
            ArrayList<Person> persons = personService.listPersons();
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
