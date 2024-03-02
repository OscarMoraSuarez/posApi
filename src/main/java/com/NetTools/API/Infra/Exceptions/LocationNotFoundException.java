package com.NetTools.API.Infra.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.IOException;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class LocationNotFoundException extends IOException {
    public LocationNotFoundException(String message){
        super(message);

    }
}
