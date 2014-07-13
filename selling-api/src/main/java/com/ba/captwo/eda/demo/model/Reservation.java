package com.ba.captwo.eda.demo.model;

import org.codehaus.jackson.map.ObjectMapper;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.IOException;

/**
 * Created by u760245 on 06/07/2014.
 */
@XmlRootElement
public class Reservation extends ResourceBase{

    @XmlElement
    private Person person;

    @XmlElement
    private Booking booking;

    @XmlElement
    private Error error;


    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Booking getBooking() {
        return booking;
    }

    public void setBooking(Booking booking) {
        this.booking = booking;
    }

    public String toString()    {
        ObjectMapper mapper = new ObjectMapper();
        String out = null;
        try {
            out = mapper.writeValueAsString(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return out;
    }

    public Error getError() {
        return error;
    }

    public void setError(Error error) {
        this.error = error;
    }
}
