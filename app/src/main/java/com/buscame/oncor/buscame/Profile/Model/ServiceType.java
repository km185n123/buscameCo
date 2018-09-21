package com.buscame.oncor.buscame.Profile.Model;

import java.util.ArrayList;
import java.util.List;

public class ServiceType extends Generic  {

    private String image;
    private String description;
    private List<Service>  services;

    public List<Service> getServices() {
        if(services==null){
            services= new ArrayList<>();
        }
        return services;
    }

    public void setServices(List<Service> services) {
        this.services = services;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
