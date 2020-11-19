package com.shengq.covid19.exception;


import com.shengq.covid19.model.ErrorStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GlobalException extends Exception{
    private static final long serialVersionUID = -3338358803193014357L;
    private int code;
    public GlobalException(ErrorStatus errorStatus){
        super(errorStatus.getMsg());
        this.code = errorStatus.getCode();
    }
    public GlobalException(String msg,ErrorStatus errorStatus){
        super(msg);
        this.code = errorStatus.getCode();
    }

    public GlobalException(String message) {
        super(message);
    }
}
