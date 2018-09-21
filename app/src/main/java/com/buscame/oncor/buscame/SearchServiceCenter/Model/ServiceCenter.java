package com.buscame.oncor.buscame.SearchServiceCenter.Model;

import com.buscame.oncor.buscame.Profile.Model.Generic;

import java.util.ArrayList;
import java.util.List;

public class ServiceCenter extends Generic {

    private String nit;
    private String phone;
    private String phoneLocal;
    private String identification;
    private String lastName;
    private String address;
    private String image;
    private List<String[]> locations;
    private boolean isOrganization;
    private City city;
    private IdentificationType identificationType;


    public ServiceCenter() {
    }

    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPhoneLocal() {
        return phoneLocal;
    }

    public void setPhoneLocal(String phoneLocal) {
        this.phoneLocal = phoneLocal;
    }

    public String getIdentification() {
        return identification;
    }

    public void setIdentification(String identification) {
        this.identification = identification;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public List<String[]> getLocations() {
        if(locations==null){
            locations=new ArrayList<>();
        }
        return locations;
    }

    public void setLocations(List<String[]> locations) {
        this.locations = locations;
    }

    public boolean isOrganization() {
        return isOrganization;
    }

    public void setOrganization(boolean organization) {
        isOrganization = organization;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public IdentificationType getIdentificationType() {
        return identificationType;
    }

    public void setIdentificationType(IdentificationType identificationType) {
        this.identificationType = identificationType;
    }
}

