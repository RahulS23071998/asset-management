package com.assetmanagement.desklite.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class EmployeeNotFoundException extends RuntimeException{

    public EmployeeNotFoundException(HttpStatus httpStatus, String message){
        super(message);
    }

    public EmployeeNotFoundException(String message){
        super(message);
    }
}
