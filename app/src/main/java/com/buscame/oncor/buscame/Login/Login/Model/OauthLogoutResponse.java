package com.buscame.oncor.buscame.Login.Login.Model;

public class OauthLogoutResponse {

    private int code;
    private String message;

    public OauthLogoutResponse(int code, String message) {
        this.code = code;
        this.message = message;
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
}
