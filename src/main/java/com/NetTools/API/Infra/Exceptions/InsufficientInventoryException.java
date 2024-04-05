package com.NetTools.API.Infra.Exceptions;

import java.io.IOException;

public class InsufficientInventoryException extends IOException {
    public InsufficientInventoryException(String message){
        super(message);

    }
}
