package com.buscame.oncor.buscame.SearchServiceCenter.Model;

public enum IdentificationType {

    RUR("Rut"),
    NIT("nit");

    private String status;

    IdentificationType(String status){
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}