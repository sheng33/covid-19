package com.shengq.covid19.controller;

import cn.hutool.json.JSONObject;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.shengq.covid19.config.Result;
import com.shengq.covid19.dao.DailyReport;
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

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
        String userid = json.get("userid").asText();
        if (userid=="")   return ResultUtil.error(500,"userid不能为空");
        ClientUserDTO clientUser = clientService.findById(userid);
        if (clientUser == null) return ResultUtil.error(500,"clientUser为空");
        if (clientUser.getUsername() == null || clientUser.getMobile() == null){
            return  ResultUtil.error(-1,"用户信息不完整");
        }else {
            return ResultUtil.success("授权成功",clientUser);
        }
    }


    @ApiOperation("用户信息绑定")
    @PostMapping("/userBind")
    public Result<?> userBind(@RequestBody JsonNode jsonNode){
        String userid = jsonNode.get("userid").asText();
        String username = jsonNode.get("username").asText();
        String iphone = jsonNode.get("mobile").asText();
        String smsCode = jsonNode.get("smsCode").asText();
        int sql_code = 0;
        if (smsCode.equals("1234")){
            sql_code = clientMapper.updateClientUser(userid,username,iphone);
        }else {
            return ResultUtil.error(-1,"验证码错误");
        }
        if (sql_code == 1){
            return ResultUtil.success("绑定成功",null);
        }
        return ResultUtil.error(-100,"未知错误");

    }
    @ApiOperation("每日信息填报")
    @PostMapping("/fillinformation")
    public Result<?> fillInformation(@RequestBody DailyReportVo dailyInfo){
        System.out.println("每日填报数据："+dailyInfo);
        int code = dailyReportService.addNewRecording(dailyInfo);
        // 存入数据库dailyReport 表
        // 进行温度，地点，异常人员接触分析
        // 存入异常人员信息表
        if (code == 1){
            return ResultUtil.success();
        }else {
            return ResultUtil.error(-1,"每日填报失败");
        }
    }
    @ApiOperation("填报记录列表")
    @GetMapping("/getDailyList")
    public Result<?> getDailyList(@RequestParam(value = "page",defaultValue = "1")int page,
                                  @RequestParam(value = "size",defaultValue = "10")int size,String userid) throws JsonProcessingException {
        PageHelper.startPage(page,size);
        List<DailyReport> list = dailyReportService.findInfoById(userid);
        List<JsonNode> listData = new ArrayList<>();
        ObjectMapper mapper = new ObjectMapper();
        list.forEach(obj ->{
            String str = "{\"id\":\""+obj.getId()+"\",\"address\":\""+obj.getAddress()+"\",\"temperature\":\""+obj.getTemperature()+"\",\"reamrk\":\""+obj.getRemark()+"\",\"status\":\""+obj.getStatus()+"\",\"createtime\":\""+obj.getCreateTime()+"\"}";
            JsonNode result = null;
            try {
                result = mapper.readTree(str.getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }
            listData.add(result);
        });


        PageInfo<JsonNode> pageInfo = new PageInfo<>(listData);
        return ResultUtil.success("查询成功",pageInfo);
    }


}
