package com.hackathonNerds.common;

import java.io.Serializable;

public class BaseResponse implements Serializable {
    private static final long serialVersionUID = 1L;
    private String status;
    private Integer code;
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

}