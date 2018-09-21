package com.buscame.oncor.buscame.Device.Rest.Response;

import com.buscame.oncor.buscame.Device.Model.Device;

import java.io.Serializable;

public class DeviceAsociateResponse implements Serializable {


    private int code;
    private String message;
    private Device device;

    public DeviceAsociateResponse() {
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

    public Device getDevice() {
        return device;
    }

    public void setDevice(Device device) {
        this.device = device;
    }
}
