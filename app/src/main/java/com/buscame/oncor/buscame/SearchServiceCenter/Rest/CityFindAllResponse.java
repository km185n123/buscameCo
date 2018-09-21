package com.buscame.oncor.buscame.SearchServiceCenter.Rest;

import com.buscame.oncor.buscame.SearchServiceCenter.Model.City;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CityFindAllResponse implements Serializable {

    private int code;
    private String message;
    private List<City> cities;

    public CityFindAllResponse() {
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

    public List<City> getCities() {
        if(cities==null){
            cities=new ArrayList<>();
        }
        return cities;
    }

    public void setCities(List<City> cities) {
        this.cities = cities;
    }
}
