package com.shengq.covid19.utils;

import com.shengq.covid19.config.Result;

/**
 * @author shengQ
 */
public class ResultUtil {
    public static Result<Object> success(Object object) {
        Result<Object> result = new Result<>();
        result.setCode(200);
        result.setMsg("请求成功");
        result.setData(object);
        return result;
    }
    public static Result<String> successMsg(String msg) {
        Result<String> result = new Result<String>();
        result.setCode(200);
        result.setMsg(msg);
        result.setData(null);
        return result;
    }
    public static Result<Object> success(String msg,Object object) {
        Result<Object> result = new Result<>();
        result.setCode(200);
        result.setMsg(msg);
        result.setData(object);
        return result;
    }
    public static Result<Object> success() {
        return success(null);
    }

    public static Result<String> error(Integer code, String msg) {
        Result<String> result = new Result<String>();
        result.setCode(code);
        result.setMsg(msg);
        return result;
    }

}