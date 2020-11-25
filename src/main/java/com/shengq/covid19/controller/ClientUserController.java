package com.shengq.covid19.controller;

import cn.hutool.core.codec.Base64;
import com.fasterxml.jackson.databind.JsonNode;
import com.shengq.covid19.config.Result;
import com.shengq.covid19.dao.ClientUser;
import com.shengq.covid19.dto.ClientUserDTO;
import com.shengq.covid19.mapper.ClientUserMapper;
import com.shengq.covid19.service.ClientUserService;
import com.shengq.covid19.service.DailyReportService;
import com.shengq.covid19.utils.ResultUtil;
import com.shengq.covid19.utils.wx.WXCore;
import com.shengq.covid19.vo.DailyReportVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

@RestController
@RequestMapping("/client")
@Api(tags = "客户用户端口")
public class ClientUserController {
    @Autowired
    ClientUserMapper clientMapper;
    @Autowired
    ClientUserService clientService;
    @Autowired
    DailyReportService dailyReportService;
    @Autowired
    WXCore wxCore;
    @ApiOperation("信息授权接口")
    @PostMapping("/authorization")
    public Result<?> authorization(@RequestBody JsonNode json){
        String iv = json.get("iv").asText();
        String encryptedData = json.get("encryptedData").asText();
        String rawData = json.get("rawData").asText();
        System.out.println(encryptedData);
        ClientUser clientUser = clientMapper.findById(json.get("userid").asText());
        JsonNode jsonNode = wxCore.getUserInfo(encryptedData,clientUser.getSessionKey(),iv);
        clientUser.setUsername(jsonNode.get("nickName").asText());


        System.out.println(jsonNode);
        return ResultUtil.success();
    }

    @ApiOperation("每日信息填报")
    @PostMapping("/fillinformation")
    public Result<?> fillInformation(@RequestBody DailyReportVo dailyInfo){
        dailyReportService.addNewRecording(dailyInfo);
        // 存入数据库dailyReport 表
        // 进行温度，地点，异常人员接触分析
        // 存入异常人员信息表


        return ResultUtil.success();
    }


}
