package com.berka.exception;

import lombok.Getter;

@Getter
public class SehirManagerException extends RuntimeException{

    private final ErrorType errorType;

    public SehirManagerException(ErrorType errorType, String message) {
        super(message);
        this.errorType = errorType;
    }


    public SehirManagerException(ErrorType errorType) {
        super(errorType.getMessage());
        this.errorType = errorType;
    }
}
