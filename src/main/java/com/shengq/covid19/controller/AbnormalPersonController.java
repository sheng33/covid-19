package com.shengq.covid19.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.shengq.covid19.config.Result;
import com.shengq.covid19.dao.AbnormalPerson;
import com.shengq.covid19.dao.ClientUser;
import com.shengq.covid19.dto.ClientUserDTO;
import com.shengq.covid19.service.AbnormalPersonService;
import com.shengq.covid19.service.ClientUserService;
import com.shengq.covid19.utils.ResultUtil;
import com.shengq.covid19.vo.AbnormalPersonVo;
import com.shengq.covid19.vo.SystemUserVo;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController("/abnormalList")
public class AbnormalPersonController {
    final AbnormalPersonService abnormalPersonService;
    final ClientUserService clientUserService;
    public AbnormalPersonController(AbnormalPersonService abnormalPersonService, ClientUserService clientUserService) {
        this.abnormalPersonService = abnormalPersonService;
        this.clientUserService = clientUserService;
    }
    @ApiOperation("获取所有异常人员信息")
    @GetMapping("/getAllList")
    public Result<?> getAllList(@RequestParam(value = "page",defaultValue = "1")int page,
                                @RequestParam(value = "size",defaultValue = "10")int size){
        PageHelper.startPage(page,size);
        List<AbnormalPerson> abnormalPeopleList = abnormalPersonService.getAllAbnormalPeople();
        PageInfo<AbnormalPerson> pageInfo = new PageInfo<>(abnormalPeopleList);
        return ResultUtil.success(pageInfo);
    }

    @ApiOperation("获取所有符合status条件的的异常人员信息")
    @ApiImplicitParam(name = "status",value = "状态值",required = true,paramType = "path",
            dataType = "int",defaultValue = "0")
    @PostMapping("/getAllList")
    public Result<?> getAllListByStatus(String status,
                                        @RequestParam(value = "page",defaultValue = "1")int page,
                                        @RequestParam(value = "size",defaultValue = "10")int size){
        PageHelper.startPage(page,size);
        List<AbnormalPerson> abnormalPeopleList = abnormalPersonService.getAllByStatus(status);
        PageInfo<AbnormalPerson> pageInfo = new PageInfo<>(abnormalPeopleList);
        return ResultUtil.success(pageInfo);
    }

    @ApiOperation("处理异常人员记录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id",value = "异常人员记录id",required = true,paramType = "path",dataType = "Interger"),
            @ApiImplicitParam(name = "status",value = "状态值",required = true,paramType = "path",
                    dataType = "int",defaultValue = "0"),
            @ApiImplicitParam(name = "remark",value = "备注",paramType = "path",dataType = "String")
    })
    @PostMapping("/records")
    public Result<?> processingRecords(String id,String status,String remark){
        int code = abnormalPersonService.updateStatus(id,status,remark);
        if (code == 1){
            return ResultUtil.success();
        }else{
            return ResultUtil.error(403,"修改失败");
        }
    }

    @ApiOperation("通过记录id查询异常人员详细信息")
    @GetMapping("/abnormalPersonInfo")
    public Result<?> getAbnormalPerson(String id){
        AbnormalPerson abnormalInfo = abnormalPersonService.searchById(id);
        ClientUserDTO clientUser = clientUserService.findByMobile(abnormalInfo.getMobile());
        AbnormalPersonVo abnormalVo = new AbnormalPersonVo(abnormalInfo.getId(),abnormalInfo.getUserid(),abnormalInfo.getMobile(),
                abnormalInfo.getAddress(),clientUser.getUsername(),abnormalInfo.getNearestaddress(),abnormalInfo.getStatus(),abnormalInfo.getRemark(),
                abnormalInfo.getCreatetime(),clientUser.getIstouch(),clientUser.getIsarea(),clientUser.getIstemperature());
        return ResultUtil.success(abnormalVo);
    }


    @ApiOperation("通过手机号查询异常人员详情信息")
    @GetMapping("/search")
    public Result<?> getAbnormalPersonInfo(String mobile){
        AbnormalPerson abnormalPerson = abnormalPersonService.searchByMobile(mobile);
        return ResultUtil.success(abnormalPerson);
    }


}
