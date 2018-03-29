package com.hackathonNerds.controller;

import java.sql.SQLException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.hackathonNerds.common.BaseResponse;

public abstract class BaseController {

    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public BaseResponse errorResponse(Exception ex) {
        BaseResponse response = new BaseResponse();
        response.setMessage(ex.getMessage());
        response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.toString());
        return response;
    }

    @ExceptionHandler(SQLException.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public BaseResponse errorResponseSqlException(Exception ex) {
        BaseResponse response = new BaseResponse();
        response.setMessage(ex.getMessage());
        return response;
    }
}
