package com.ba.captwo.eda.demo.processors;

import com.ba.captwo.eda.demo.utils.Constants;
import com.ba.captwo.eda.demo.utils.RouteUtils;
import org.apache.camel.Exchange;
import org.apache.camel.InvalidPayloadException;
import org.apache.camel.Message;
import org.apache.camel.Processor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.activation.DataHandler;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

/**
 * Created by u760245 on 06/07/2014.
 */
@Component("RESTRequestProcessor")
public class RESTRequestProcessor implements Processor {

    private final Logger log = LoggerFactory.getLogger(RESTRequestProcessor.class);

    public void process(Exchange exchange) throws Exception {

        log.debug("***THREAD : "+ Thread.currentThread().toString());

        String uri = (String)exchange.getIn().getHeader(Constants.CAMEL_URI_HEADER);
        log.info("*** Request URI : " + uri);
        processMatrixParams(uri, exchange.getIn());

        RouteUtils.messageLog("RESTRequestProcessor", exchange.getIn());
    }

    private void processMatrixParams(String uri, Message m) {

        Map<String, String> matrixParams = new HashMap<String, String>();
        String[] params = uri.split(";");
        for (String param : params) {
            if (param.contains("=")) {
                String[] keyVal = param.split("=");
                matrixParams.put(keyVal[0].trim(), keyVal[1].trim());
                m.getHeaders().put(keyVal[0].trim(), keyVal[1].trim());
            }
        }
    }
}
