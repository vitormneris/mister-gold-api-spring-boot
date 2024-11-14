package com.mistergold.mistergold.configuration.web.advice;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.mistergold.mistergold.configuration.web.advice.exception.ArgumentInvalidException;
import com.mistergold.mistergold.configuration.web.advice.exception.NotAuthorizationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.mistergold.mistergold.configuration.web.advice.dto.ErrorFieldDTO;
import com.mistergold.mistergold.configuration.web.advice.dto.ErrorMessageResponseDTO;
import com.mistergold.mistergold.configuration.web.advice.exception.DataIntegratyViolationException;
import com.mistergold.mistergold.configuration.web.advice.exception.ResourceNotFoundException;
import com.mistergold.mistergold.configuration.web.enums.RunErrorEnum;

import jakarta.servlet.http.HttpServletRequest;


@ControllerAdvice
public class ExceptionHandlerController {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorMessageResponseDTO> methodArgumentNotValid(MethodArgumentNotValidException exception, HttpServletRequest request) {
        RunErrorEnum errorEnum = RunErrorEnum.ERR0003;
        List<ErrorFieldDTO> fields = new ArrayList<>();

        for (FieldError error : exception.getBindingResult().getFieldErrors()) {
            ErrorFieldDTO errorFieldDTO = ErrorFieldDTO.builder()
                    .name(error.getField())
                    .description(error.getDefaultMessage())
                    .value(String.valueOf(error.getRejectedValue()))
                    .build();
            
            fields.add(errorFieldDTO);
        }

        ErrorMessageResponseDTO messageResponseDTO = ErrorMessageResponseDTO.builder()
                    .code(errorEnum.getCode())
                    .message(errorEnum.getMessage())
                    .timestamp(Instant.now())
                    .path(request.getRequestURI())
                    .fields(fields)
                    .build();
        
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(messageResponseDTO);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorMessageResponseDTO> resourceNotFound(ResourceNotFoundException exception, HttpServletRequest request) {
        RunErrorEnum runErrorEnum = exception.getRunErrorEnum();

        ErrorMessageResponseDTO errorMessageResponseDTO = ErrorMessageResponseDTO.builder()
                    .code(runErrorEnum.getCode())
                    .message(runErrorEnum.getMessage())
                    .timestamp(Instant.now())
                    .path(request.getRequestURI())
                    .fields(Collections.emptyList())
                    .build();

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessageResponseDTO);
    }

    @ExceptionHandler(DataIntegratyViolationException.class)
    public ResponseEntity<ErrorMessageResponseDTO> dataIntegratyViolation(DataIntegratyViolationException exception, HttpServletRequest request) {
        RunErrorEnum runErrorEnum = exception.getRunErrorEnum();

        ErrorMessageResponseDTO errorMessageResponseDTO = ErrorMessageResponseDTO.builder()
                    .code(runErrorEnum.getCode())
                    .message(runErrorEnum.getMessage())
                    .timestamp(Instant.now())
                    .path(request.getRequestURI())
                    .fields(Collections.emptyList())
                    .build();

        return ResponseEntity.status(HttpStatus.CONFLICT).body(errorMessageResponseDTO);
    }

    @ExceptionHandler(ArgumentInvalidException.class)
    public ResponseEntity<ErrorMessageResponseDTO> argumentInvalid(ArgumentInvalidException exception, HttpServletRequest request) {
        RunErrorEnum runErrorEnum = exception.getRunErrorEnum();

        ErrorMessageResponseDTO errorMessageResponseDTO = ErrorMessageResponseDTO.builder()
                .code(runErrorEnum.getCode())
                .message(runErrorEnum.getMessage())
                .timestamp(Instant.now())
                .path(request.getRequestURI())
                .fields(Collections.emptyList())
                .build();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessageResponseDTO);
    }

    @ExceptionHandler(NotAuthorizationException.class)
    public ResponseEntity<ErrorMessageResponseDTO> notAuthorization(NotAuthorizationException exception, HttpServletRequest request) {
        RunErrorEnum runErrorEnum = exception.getRunErrorEnum();

        ErrorMessageResponseDTO errorMessageResponseDTO = ErrorMessageResponseDTO.builder()
                .code(runErrorEnum.getCode())
                .message(runErrorEnum.getMessage())
                .timestamp(Instant.now())
                .path(request.getRequestURI())
                .fields(Collections.emptyList())
                .build();

        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(errorMessageResponseDTO);
    }
}
