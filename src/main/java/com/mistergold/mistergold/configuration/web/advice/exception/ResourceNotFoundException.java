package com.mistergold.mistergold.configuration.web.advice.exception;

import com.mistergold.mistergold.configuration.web.enums.RunErrorEnum;

import lombok.Getter;

@Getter
public class ResourceNotFoundException extends RuntimeException {
    private RunErrorEnum runErrorEnum;

    public ResourceNotFoundException(String message) {
        super(message);
    }

    public ResourceNotFoundException(RunErrorEnum runErrorEnum) {
        super(runErrorEnum.getMessage());
        this.runErrorEnum = runErrorEnum;
    }
}
