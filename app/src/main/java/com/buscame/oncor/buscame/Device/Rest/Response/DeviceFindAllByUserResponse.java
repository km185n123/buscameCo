package com.buscame.oncor.buscame.Device.Rest.Response;




import com.buscame.oncor.buscame.Device.Model.Device;
import com.buscame.oncor.buscame.Profile.Model.Account;

import java.io.Serializable;
import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;

import kotlinx.android.parcel.Parcelize;

public class DeviceFindAllByUserResponse implements Parcelize {

    private int code;
    private String message;
    private List<Device> devices;

    public DeviceFindAllByUserResponse() {
    }



    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Device> getDevices() {
        if(devices==null){
            devices= new ArrayList<>();
        }
        return devices;
    }

    public void setDevices(List<Device> devices) {
        this.devices = devices;
    }

    @Override
    public Class<? extends Annotation> annotationType() {
        return null;
    }
}
