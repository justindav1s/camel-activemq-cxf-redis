package com.ba.captwo.eda.demo.routes.converters;

import com.ba.captwo.eda.demo.model.Person;
import com.ba.captwo.eda.demo.model.ResourceBase;
import org.apache.camel.Converter;
import org.apache.camel.Exchange;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by u760245 on 20/07/2014.
 */
@Converter
public class ResourceBaseConverter {

    private final static Logger log = LoggerFactory.getLogger(ResourceBaseConverter.class);

    public ResourceBaseConverter() {
        log.info("init");
    }

    @Converter
    public static ResourceBase toPerson(ResourceBase p, Exchange exchange) {
        log.info("Converting");
        log.info("Input : "+p.toString());
        return p;
    }

    @Converter
    public static Object toPerson(Object p, Exchange exchange) {
        log.info("Converting Object");
        log.info("Input : "+p.toString());
        return p;
    }
}
