package com.buscame.oncor.buscame.Historical.Rest;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class DeviceHistoricRequest {

    private String code;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy",locale = "en-US", timezone = "America/Bogota")
    private Date startDate;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy",locale = "en-US", timezone = "America/Bogota")
    private Date endDate;

    public DeviceHistoricRequest() {
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}
