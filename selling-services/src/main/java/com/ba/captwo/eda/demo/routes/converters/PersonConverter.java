package com.ba.captwo.eda.demo.routes.converters;

import com.ba.captwo.eda.demo.model.Person;
import org.apache.camel.Converter;
import org.apache.camel.Exchange;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by u760245 on 20/07/2014.
 */
@Converter
public class PersonConverter {

    private final static Logger log = LoggerFactory.getLogger(PersonConverter.class);

    private PersonConverter() {
    }


    @Converter
    public static Person toPerson(Person p, Exchange exchange) {
        log.info("Converting");
        log.info("Input : "+p.toString());
        return p;
    }
}
