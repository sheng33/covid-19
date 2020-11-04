package com.shengq.covid19.controller;

import com.shengq.covid19.config.Result;
import com.shengq.covid19.service.SystemUserService;
import com.shengq.covid19.utils.ResultUtil;
import com.shengq.covid19.vo.SystemUserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author shengQ
 */
@RestController()
@RequestMapping("/user")
public class SystemUserController {
    @Autowired
    SystemUserService systemUserService;


    @PostMapping(value = "/register")
    public Result register(@RequestBody SystemUserVo systemUserVo){
        System.out.println(systemUserVo);
        return ResultUtil.success();
    }
}
