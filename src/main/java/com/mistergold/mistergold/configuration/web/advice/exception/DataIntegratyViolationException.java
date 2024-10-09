package com.mistergold.mistergold.configuration.web.advice.exception;

import com.mistergold.mistergold.configuration.web.enums.RunErrorEnum;

import lombok.Getter;

@Getter
public class DataIntegratyViolationException extends RuntimeException {
    private RunErrorEnum runErrorEnum;

    public DataIntegratyViolationException(String message) {
        super(message);
    }

    public DataIntegratyViolationException(RunErrorEnum runErrorEnum) {
        super(runErrorEnum.getMessage());
        this.runErrorEnum = runErrorEnum;
    }
}
