package com.mistergold.mistergold.configuration.web.advice.exception;

import com.mistergold.mistergold.configuration.web.enums.RunErrorEnum;
import lombok.Getter;

@Getter
public class InternalError extends RuntimeException {
    private RunErrorEnum runErrorEnum;

    public InternalError(String message) {
        super(message);
    }

    public InternalError(RunErrorEnum runErrorEnum) {
        super(runErrorEnum.getMessage());
        this.runErrorEnum = runErrorEnum;
    }
}
