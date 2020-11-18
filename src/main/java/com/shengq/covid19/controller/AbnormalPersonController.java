package com.shengq.covid19.controller;

import com.shengq.covid19.config.Result;
import com.shengq.covid19.service.AbnormalPersonService;
import com.shengq.covid19.utils.ResultUtil;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/abnormalList")
public class AbnormalPersonController {
    final AbnormalPersonService abnormalPersonService;

    public AbnormalPersonController(AbnormalPersonService abnormalPersonService) {
        this.abnormalPersonService = abnormalPersonService;
    }
    @ApiOperation("获取所有异常人员信息")
    @GetMapping("/getAllList")
    public Result<?> getAllList(){
        return ResultUtil.success(abnormalPersonService.getAllAbnormalPeople());
    }

    @ApiOperation("获取所有符合status条件的的异常人员信息")
    @ApiImplicitParam(name = "status",value = "状态值",required = true,paramType = "path",
            dataType = "int",defaultValue = "0")
    @PostMapping("/getAllList")
    public Result<?> getAllListByStatus(String status){
        return ResultUtil.success(abnormalPersonService.getAllByStatus(status));
    }

    @PostMapping("/Records")
    public Result<?> processingRecords(Integer id,String status,String remark){
        int code = abnormalPersonService.updateStatus(id,status,remark);
        if (code == 1){
            return ResultUtil.success();
        }else{
            return ResultUtil.error(403,"修改失败");
        }

    }


}
