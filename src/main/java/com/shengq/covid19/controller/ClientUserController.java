package com.shengq.covid19.controller;

import com.shengq.covid19.config.Result;
import com.shengq.covid19.service.ClientUserService;
import com.shengq.covid19.utils.ResultUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/client")
@Api(tags = "客户用户端口")
public class ClientUserController {
    @Autowired
    ClientUserService clientService;

    @ApiOperation("信息授权接口")
    @PostMapping("/authorization")
    public Result<?> authorization(){
        return ResultUtil.success();
    }






}
