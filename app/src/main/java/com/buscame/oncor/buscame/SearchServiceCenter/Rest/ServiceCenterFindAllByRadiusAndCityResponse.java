package com.buscame.oncor.buscame.SearchServiceCenter.Rest;



import com.buscame.oncor.buscame.SearchServiceCenter.Model.ServiceCenter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ServiceCenterFindAllByRadiusAndCityResponse implements Serializable {

    private int code;
    private String message;
    private List<ServiceCenter> serviceCenters;

    public ServiceCenterFindAllByRadiusAndCityResponse() {
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

    public List<ServiceCenter> getServiceCenters() {
        if(serviceCenters==null){
            serviceCenters=new ArrayList<>();
        }
        return serviceCenters;
    }

    public void setServiceCenters(List<ServiceCenter> serviceCenters) {
        this.serviceCenters = serviceCenters;
    }
}


