package com.shengq.covid19.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorModel {
    private String msg;
    private int code;
    public ErrorModel(ErrorStatus errorStatus){
        this.msg = errorStatus.getMsg();
        this.code = errorStatus.getCode();
    }
}
