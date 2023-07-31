package com.berka.exception;

import lombok.Getter;

@Getter
public class SkorManagerException extends RuntimeException{

    private final ErrorType errorType;

    public SkorManagerException(ErrorType errorType, String message) {
        super(message);
        this.errorType = errorType;
    }


    public SkorManagerException(ErrorType errorType) {
        super(errorType.getMessage());
        this.errorType = errorType;
    }
}
