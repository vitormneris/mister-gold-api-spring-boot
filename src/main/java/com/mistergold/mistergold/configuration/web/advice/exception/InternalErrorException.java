package com.mistergold.mistergold.configuration.web.advice.exception;

import com.mistergold.mistergold.configuration.web.enums.RunErrorEnum;
import lombok.Getter;

@Getter
public class InternalErrorException extends RuntimeException {
    private RunErrorEnum runErrorEnum;

    public InternalErrorException(String message) {
        super(message);
    }

    public InternalErrorException(RunErrorEnum runErrorEnum) {
        super(runErrorEnum.getMessage());
        this.runErrorEnum = runErrorEnum;
    }
}
