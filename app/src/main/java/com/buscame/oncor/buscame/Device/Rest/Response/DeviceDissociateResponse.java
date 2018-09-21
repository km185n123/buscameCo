package com.buscame.oncor.buscame.Device.Rest.Response;

import java.io.Serializable;

public class DeviceDissociateResponse implements Serializable {

    private int code;
    private String message;

    public DeviceDissociateResponse() {
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
