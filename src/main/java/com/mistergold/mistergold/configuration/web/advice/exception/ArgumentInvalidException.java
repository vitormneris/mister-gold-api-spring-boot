package com.mistergold.mistergold.configuration.web.advice.exception;

import com.mistergold.mistergold.configuration.web.enums.RunErrorEnum;
import lombok.Getter;

@Getter
public class ArgumentInvalidException extends RuntimeException {
    private RunErrorEnum runErrorEnum;

    public ArgumentInvalidException(String message) {
        super(message);
    }

    public ArgumentInvalidException(RunErrorEnum runErrorEnum) {
        super(runErrorEnum.getMessage());
        this.runErrorEnum = runErrorEnum;
    }
}
