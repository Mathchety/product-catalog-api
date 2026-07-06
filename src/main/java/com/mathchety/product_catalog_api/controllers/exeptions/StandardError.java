package com.mathchety.product_catalog_api.controllers.exeptions;

import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
public class StandardError {
    private Instant timeStamp;
    private Integer status;
    private String error;
    private String message;
    private String path;

    public StandardError(){}

    public StandardError(Instant timeStamp, Integer status, String error, String message, String path){
        this.timeStamp = timeStamp;
        this.status = status;
        this.error = error;
        this.message = message;
        this.path = path;
    }

}
