package com.assetmanagement.desklite.dashboard.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class AssetNotFoundException extends RuntimeException {

    public AssetNotFoundException(String message){
        super(message);
    }
}
