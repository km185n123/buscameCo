package com.buscame.oncor.buscame.Device.Rest.Response;


import com.buscame.oncor.buscame.Profile.Model.Historic;

import java.util.ArrayList;
import java.util.List;

public class DeviceHistoricResponse {

    private int code;
    private String message;
    List<Historic> historics;

    public DeviceHistoricResponse() {
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

    public List<Historic> getHistorics() {
        if(historics==null){
            historics= new ArrayList<>();
        }
        return historics;
    }

    public void setHistorics(List<Historic> historics) {
        this.historics = historics;
    }
}
