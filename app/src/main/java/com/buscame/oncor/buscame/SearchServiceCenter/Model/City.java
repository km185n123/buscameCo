package com.buscame.oncor.buscame.SearchServiceCenter.Model;

import com.buscame.oncor.buscame.Profile.Model.Generic;

public class City extends Generic {


    private Country country;

    public City() {
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }
}