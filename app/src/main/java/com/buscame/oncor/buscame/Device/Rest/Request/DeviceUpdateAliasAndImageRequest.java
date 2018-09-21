package com.buscame.oncor.buscame.Device.Rest.Request;

public class DeviceUpdateAliasAndImageRequest {

    private String code;
    private String alias;
    private String image;

    public DeviceUpdateAliasAndImageRequest() {
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
