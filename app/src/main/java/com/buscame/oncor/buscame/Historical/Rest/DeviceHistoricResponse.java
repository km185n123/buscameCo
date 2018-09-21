package com.buscame.oncor.buscame.Historical.Rest;

import com.buscame.oncor.buscame.Historical.Model.Historic;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DeviceHistoricResponse {

    private int code;
    private String message;
    private Map<String,List<Historic>> historicForDay;

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

    public Map<String, List<Historic>> getHistoricForDay() {
        if(historicForDay==null){
            historicForDay= new HashMap<>();
        }
        return historicForDay;
    }

    public void setHistoricForDay(Map<String, List<Historic>> historicForDay) {
        this.historicForDay = historicForDay;
    }

}
