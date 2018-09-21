package com.buscame.oncor.buscame.Device.Rest.Request;

import java.io.Serializable;

public class DeviceAsociateRequest implements Serializable {

    private String code;


    public DeviceAsociateRequest() {
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

  }
