package com.buscame.oncor.buscame.SearchServiceCenter.Model;

import com.buscame.oncor.buscame.Profile.Model.Generic;

import java.util.ArrayList;
import java.util.List;

public class Country extends Generic {


    private List<City> cities;

    public Country() {
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
