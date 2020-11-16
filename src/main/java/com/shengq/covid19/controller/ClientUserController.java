package com.shengq.covid19.controller;

import com.shengq.covid19.config.Result;
import com.shengq.covid19.dao.DailyReport;
import com.shengq.covid19.service.ClientUserService;
import com.shengq.covid19.utils.ResultUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

    @ApiOperation("每日信息填报")
    @PostMapping("/fillinformation")
    public Result<?> fillInformation(@RequestBody DailyReport dailyInfo){

        // 存入数据库dailyReport 表
        // 进行温度，地点，异常人员接触分析
        // 存入异常人员信息表


        return ResultUtil.success();
    }


}
