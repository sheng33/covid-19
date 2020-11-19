package com.shengq.covid19.handler;

import com.shengq.covid19.exception.NotFoundException;
import com.shengq.covid19.model.ErrorModel;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class RestExceptionHandler {
    @ExceptionHandler(value = NotFoundException.class)
    @ResponseBody
    @ResponseStatus
    public ErrorModel handleNotFoundException(NotFoundException e){
        return new ErrorModel(e.getMessage(),e.getCode());
    }
}
