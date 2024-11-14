package com.mistergold.mistergold.configuration.web.advice.exception;

import com.mistergold.mistergold.configuration.web.enums.RunErrorEnum;
import lombok.Getter;

@Getter
public class NotAuthorizationException extends RuntimeException {
    private RunErrorEnum runErrorEnum;

    public NotAuthorizationException(String message) {
        super(message);
    }

    public NotAuthorizationException(RunErrorEnum runErrorEnum) {
        super(runErrorEnum.getMessage());
        this.runErrorEnum = runErrorEnum;
    }
}
