package com.ba.captwo.eda.demo.processors;

import com.ba.captwo.eda.demo.model.*;
import com.ba.captwo.eda.demo.model.Error;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.cxf.helpers.IOUtils;
import org.apache.cxf.jaxrs.client.WebClient;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.ws.rs.core.Response;
import java.io.InputStream;

/**
 * Created by u760245 on 07/07/2014.
 */

@Component("RESTProducerEndpoint")
public class RESTProducerEndpoint implements Processor {

    private final Logger log = LoggerFactory.getLogger(RESTProducerEndpoint.class);

    public static final String RESPONSE =  RESTProducerEndpoint.class.getName()+".RESPONSE";
    public static final String RESPONSE_TYPE =  RESTProducerEndpoint.class.getName()+".RESPONSE_TYPE";
    public static final String POST_PAYLOAD_HEADER_NAME =  RESTProducerEndpoint.class.getName()+".POST_PAYLOAD_HEADER_NAME";

    public void process(Exchange exchange) throws Exception {

        String uri = (String)exchange.getIn().getHeader(Exchange.HTTP_BASE_URI);
        String accept = (String)exchange.getIn().getHeader(Exchange.ACCEPT_CONTENT_TYPE);
        String query = (String)exchange.getIn().getHeader(Exchange.HTTP_QUERY);
        String method = (String)exchange.getIn().getHeader(Exchange.HTTP_METHOD);
        String responseType = (String)exchange.getIn().getHeader(RESPONSE_TYPE);

        log.info("CALLING : "+method+":"+uri + query);
        log.info("RESPONSE_TYPE : "+responseType);

        WebClient client = WebClient.create(uri + query);

        Response r = null;

        if ("GET".equalsIgnoreCase(method)) {
            r = client.accept(accept).get();
        }
        else if ("POST".equalsIgnoreCase(method))   {
            String payloadHeader = (String)exchange.getIn().getHeader(POST_PAYLOAD_HEADER_NAME);
            String payload = (String)exchange.getIn().getHeader(payloadHeader);
            r = client.accept(accept).post(payload);
        }

        String value = IOUtils.toString((InputStream) r.getEntity());
        log.info("RESPONSE : "+value);

        if (Response.Status.OK.getStatusCode() == r.getStatus())    {
            Class c = Class.forName(responseType);
            ObjectMapper mapper = new ObjectMapper();
            Object outObject = mapper.readValue(value, c);
            exchange.getIn().getHeaders().put(RESTProducerEndpoint.RESPONSE, outObject);
        }
        else    {
            Error err = null;
            try {
                ObjectMapper mapper = new ObjectMapper();
                err = mapper.readValue(value, Error.class);
                exchange.getIn().getHeaders().put(RESTProducerEndpoint.RESPONSE, err);
            }
            catch (Exception e) {
                err = new Error();
                err.setMessage("Error : "+r.getStatus()+" received.");
                exchange.getIn().getHeaders().put(RESTProducerEndpoint.RESPONSE, err);
            }

        }

    }
}
