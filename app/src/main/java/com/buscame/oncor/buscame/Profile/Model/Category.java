package com.buscame.oncor.buscame.Profile.Model;

import java.util.*;

public class Category extends Generic {

    private String color;
    private String description;
    private String image;
    private Set<ServiceType> serviceTypes;

    public Category() {
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }


    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Set<ServiceType> getServiceTypes() {
        if(serviceTypes==null){
            serviceTypes= new HashSet<>();
        }
        return serviceTypes;
    }

    public void setServiceTypes(Set<ServiceType> serviceTypes) {
        this.serviceTypes = serviceTypes;
    }


}