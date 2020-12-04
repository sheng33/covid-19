package com.shengq.covid19.controller;

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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "用户端系统配置")
@RestController
@RequestMapping("/sysConfig")
public class ClinetSysConfigController {
    @Autowired
    ClientConfigImgService sysConfigServer;
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

}
