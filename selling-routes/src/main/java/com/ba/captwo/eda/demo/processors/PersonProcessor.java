package com.ba.captwo.eda.demo.processors;

import com.ba.captwo.eda.demo.model.Booking;
import com.ba.captwo.eda.demo.model.Person;
import com.ba.captwo.eda.demo.utils.RouteUtils;
import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.Processor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * Created by u760245 on 06/07/2014.
 */
@Component("PersonProcessor")
public class PersonProcessor implements Processor {

    private final Logger log = LoggerFactory.getLogger(PersonProcessor.class);

    public void process(Exchange exchange) throws Exception {

        RouteUtils.messageLog("PersonProcessor", exchange.getIn());

        Map<String, Object> params = (Map<String, Object>)exchange.getIn().getHeaders();
        Booking b = new Booking();
        Person p = new Person();
        //p.setPersonID((Integer)params.get("pid"));
        p.setLastName((String)params.get("lname"));
        p.setFirstName((String)params.get("fname"));
        p.setAddress((String)params.get("address"));
        p.setCity((String)params.get("city"));

        Message m = exchange.getIn();
        RouteUtils.messageLog("PersonProcessor", exchange.getIn());

        exchange.getIn().getHeaders().put("person", p);
        exchange.getIn().setBody(RouteUtils.toJson(p));
    }

}
