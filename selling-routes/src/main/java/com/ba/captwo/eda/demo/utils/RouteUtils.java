package com.ba.captwo.eda.demo.utils;

import org.apache.camel.Message;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Map;

/**
 * Created by u760245 on 06/07/2014.
 */
public class RouteUtils {

    private static final Logger log = LoggerFactory.getLogger(RouteUtils.class);

    public static void messageLog(String processor, Message m)  {
        log.info("*** ["+processor+"] : id : "+m.getMessageId());
        Object body = m.getBody();
        log.info("*** ["+processor+"] : body class : "+body.getClass().getName());
        log.info("*** ["+processor+"] : body : "+body);
        Map<String, Object> headers =  m.getHeaders();
        for (Map.Entry<String, Object> e : headers.entrySet())      {
            log.info("*** ["+processor+"] : Header : " + e.getKey() + " : " + e.getValue());
        }
    }

    public static String toJson(Object o) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(o);
    }
}
