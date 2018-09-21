package com.buscame.oncor.buscame.Device.Rest.Request;

import java.io.Serializable;

public class DeviceDissociateRequest implements Serializable {

    private String code;

    public DeviceDissociateRequest() {
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}