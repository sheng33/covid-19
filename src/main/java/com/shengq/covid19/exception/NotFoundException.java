package com.shengq.covid19.exception;

import com.shengq.covid19.model.ErrorStatus;

/**
 * 资源NotFound
 */
public class NotFoundException extends GlobalException{
    public NotFoundException(ErrorStatus errorStatus){
        super(errorStatus);
    }
    public NotFoundException(String message,ErrorStatus errorStatus){
        super(message,errorStatus);
    }
    public NotFoundException(String message){
        super(message);
    }
}
