package com.ba.captwo.eda.demo.model;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by u760245 on 05/07/2014.
 */
@XmlRootElement
public class Error extends ResourceBase{

    private String message = null;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
