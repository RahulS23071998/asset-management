package com.assetcircle.assetmanagement.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class AssetManagementException extends RuntimeException{

    public AssetManagementException(HttpStatus httpStatus, String message){
        super(message);
    }
}
