package com.shengq.covid19.utils;

import com.shengq.covid19.config.Result;

/**
 * @author shengQ
 */
public class ResultUtil {
    public static <T> Result<T> success(T msg,T object) {
        Result<T> result = new Result<T>();
        result.setCode(200);
        result.setMsg(msg.toString());
        result.setData(object);
        return result;
    }
    public static <T> Result<T> successMsg(T msg) {
        Result<T> result = new Result<T>();
        result.setCode(200);
        result.setMsg(msg.toString());
        result.setData(null);
        return result;
    }
    public static <T> Result<T> success(String msg, T object) {
        Result<T> result = new Result<T>();
        result.setCode(200);
        result.setMsg(msg);
        result.setData(object);
        return result;
    }
    public static <T> Result<T> success() {
        return  success("请求成功",null);
    }

    public static <T> Result<T> error(Integer code, T msg) {
        Result<T> result = new Result<T>();
        result.setCode(code);
        result.setMsg(msg.toString());
        return result;
    }

}