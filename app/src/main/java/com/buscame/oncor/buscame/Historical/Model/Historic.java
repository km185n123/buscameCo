package com.buscame.oncor.buscame.Historical.Model;

import java.io.Serializable;
import java.util.Date;

public class Historic implements Serializable {

    private int id;
    private String codeDevice;
    private String latitude;
    private String longitude;
    private Date createDate;
    private Date createHour;
    private String stringCreateHour;
    private String address;

    public Historic() {
    }


    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

    public int getId() {
        return id;
    }

    public String getCodeDevice() {
        return codeDevice;
    }

    public void setCodeDevice(String codeDevice) {
        this.codeDevice = codeDevice;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getCreateHour() {
        return createHour;
    }

    public void setCreateHour(Date createHour) {
        this.createHour = createHour;
    }

    public String getStringCreateHour() {
        return stringCreateHour;
    }

    public void setStringCreateHour(String stringCreateHour) {
        this.stringCreateHour = stringCreateHour;
    }
}
