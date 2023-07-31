package com.berka.exception;

import lombok.Getter;

@Getter
public class TahminManagerException extends RuntimeException{

    private final ErrorType errorType;

    public TahminManagerException(ErrorType errorType, String message) {
        super(message);
        this.errorType = errorType;
    }


    public TahminManagerException(ErrorType errorType) {
        super(errorType.getMessage());
        this.errorType = errorType;
    }
}
