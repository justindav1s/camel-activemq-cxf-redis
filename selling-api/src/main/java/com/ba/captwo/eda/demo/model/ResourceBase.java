package com.ba.captwo.eda.demo.model;

import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.lang.reflect.Field;

/**
 * Created by u760245 on 13/07/2014.
 */
public abstract class ResourceBase {

    private final Logger log = LoggerFactory.getLogger(ResourceBase.class);

    public String toString() {
        ObjectMapper mapper = new ObjectMapper();
        String out = null;
        try {
            out = mapper.writeValueAsString(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return out;
    }

    @Override
    public int hashCode() {
        int ret = 1;
        log.debug("HashCode : "+ret);
        return ret;
    }

    @Override
    public boolean equals(Object that) {

        boolean equality = true;

        if (that == null) equality =  false;
        if (that.getClass() != this.getClass()) equality =  false;
        Field[] fields = this.getClass().getDeclaredFields();

        try {
            for (Field f : fields) {
                f.setAccessible(true);
                Object thisVal = f.get(this);
                Object thatVal = f.get(that);

                log.debug(this.getClass().getName()+": thisVal :["+thisVal+"] thatVal :["+thatVal+"]");

                if ( (thisVal != null) && (thatVal == null) ||
                        (thisVal == null) && (thatVal != null) ) {
                    equality =  false;
                }
                else if ( (thisVal == null) && (thatVal == null) ) {
                    //do nothing
                }
                else if ( !thisVal.equals(thatVal) ) {
                    equality =  false;
                }
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        if (equality)
            log.debug(this.getClass().getName()+": Objects are Equal.");
        else
            log.debug(this.getClass().getName()+": Objects are NOT Equal.");

        return equality;
    }
}
