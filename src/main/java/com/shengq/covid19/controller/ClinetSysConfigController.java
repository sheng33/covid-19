package com.shengq.covid19.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.shengq.covid19.config.Result;
import com.shengq.covid19.dao.ClientConfigImg;
import com.shengq.covid19.dao.SystemUserLoginDetail;
import com.shengq.covid19.service.ClientConfigImgService;
import com.shengq.covid19.service.Impl.ClientConfigImgServiceImpl;
import com.shengq.covid19.utils.ResultUtil;
import com.shengq.covid19.vo.SystemUserVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.sql.ResultSet;
import java.util.List;

@Api(tags = "用户端系统配置")
@RestController
@RequestMapping("/clientsysConfig")
public class ClinetSysConfigController {
    @Autowired
    ClientConfigImgService sysConfigServer;
    @ApiOperation("获取全部banner图片")
    @RequestMapping(value = "/getAllBannerImg",method = RequestMethod.GET)
    public Result<?> getBannerImg(@RequestParam(value = "page",defaultValue = "1")int page,
                                  @RequestParam(value = "size",defaultValue = "10")int size){
        SystemUserLoginDetail st = (SystemUserLoginDetail) SecurityContextHolder.
                getContext().getAuthentication().getPrincipal();
        PageHelper.startPage(page,size);
        List<ClientConfigImg> configImgList = sysConfigServer.getAllImages();
        PageInfo<ClientConfigImg> pageInfo = new PageInfo<>(configImgList);
        return ResultUtil.success(pageInfo);
    }
    @ApiOperation("新增banner图")
    @PostMapping(value = "/addBannerImg")
    public Result<?> addBannerImg(@RequestBody JsonNode json){
        String imgName = json.get("imgName").asText();
        String imgUrl = json.get("imgUrl").asText();
        int code = sysConfigServer.addImage(imgName,imgUrl);
        if (code == 1){
            return ResultUtil.success("新增bannerImg成功",null);
        }else {
            return ResultUtil.error(-1,"新增失败");
        }
    }
    @ApiOperation("获取banner图")
    @GetMapping(value = "/getBanner")
    public Result<?> getBanner(int bannerId){
        ClientConfigImg clientConfigImg = sysConfigServer.getImage(bannerId);
        return ResultUtil.success(clientConfigImg);
    }
    @ApiOperation("删除banner图")
    @GetMapping(value = "/delBanner")
    public Result<?> delBanner(int bannerId){
        int code = sysConfigServer.delImage(bannerId);
        if (code == 1){
            return ResultUtil.success("删除bannerImg成功",null);
        }else {
            return ResultUtil.error(-1,"删除失败");
        }
    }



}
