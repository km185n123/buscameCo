package com.buscame.oncor.buscame.SearchServiceCenter.Rest;


import java.io.Serializable;

public class ServiceCenterFindAllByRadiusAndCityRequest implements Serializable {

    private int serviceId;
    private int radius;
    private int cityId;
    private Double lat;
    private Double lng;
    private boolean filterForCity;

    public ServiceCenterFindAllByRadiusAndCityRequest() {
    }


    public int getServiceId() {
        return serviceId;
    }

    public void setServiceId(int serviceId) {
        this.serviceId = serviceId;
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLng() {
        return lng;
    }

    public void setLng(Double lng) {
        this.lng = lng;
    }

    public boolean isFilterForCity() {
        return filterForCity;
    }

    public void setFilterForCity(boolean filterForCity) {
        this.filterForCity = filterForCity;
    }
}