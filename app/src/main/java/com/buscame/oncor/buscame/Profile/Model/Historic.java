package com.buscame.oncor.buscame.Profile.Model;


public class Historic extends Generic {

    private String latitude;
    private String longitude;

    public Historic() {
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }
}
