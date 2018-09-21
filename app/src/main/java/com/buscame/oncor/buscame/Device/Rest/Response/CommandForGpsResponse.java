package com.buscame.oncor.buscame.Device.Rest.Response;

public class CommandForGpsResponse {
    private int code;
    private String message;

    public CommandForGpsResponse() {
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

