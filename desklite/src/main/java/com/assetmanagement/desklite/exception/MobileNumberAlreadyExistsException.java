package com.assetmanagement.desklite.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class MobileNumberAlreadyExistsException extends RuntimeException{

    public MobileNumberAlreadyExistsException(String message){
        super(message);
    }
}
