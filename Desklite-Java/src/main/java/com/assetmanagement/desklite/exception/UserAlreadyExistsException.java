package com.assetmanagement.desklite.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class UserAlreadyExistsException  extends RuntimeException {

    public UserAlreadyExistsException(HttpStatus badRequest, String message){
        super(message);
    }

}
