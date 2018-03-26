package com.transaction.statistics.controller;

import com.transaction.statistics.exception.InvalidTimestampException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.http.HttpStatus.NO_CONTENT;

@ControllerAdvice
public class DefaultControllerAdvice {
    @ResponseStatus(NO_CONTENT)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public void handleMethodArgumentNotValidException() {
    }

    @ResponseStatus(NO_CONTENT)
    @ExceptionHandler(InvalidTimestampException.class)
    public void handleInvalidTimestampException() {
    }
}
