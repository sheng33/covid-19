package com.shengq.covid19.controller;

import com.shengq.covid19.config.Result;
import com.shengq.covid19.dto.SystemUserDTO;
import com.shengq.covid19.service.Impl.SecurityUserDetailsServiceImpl;
import com.shengq.covid19.service.SystemUserService;
import com.shengq.covid19.utils.ResultUtil;
import com.shengq.covid19.vo.SystemUserVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

/**
 * @author shengQ
 */
@RestController()
@RequestMapping("/user")
@Api(tags = "系统用户相关接口")
public class SystemUserController {
    @Autowired
    SystemUserService systemUserService;
    @Autowired
    SecurityUserDetailsServiceImpl securityUserDetailsService;
    @ApiOperation("系统用户注册接口")
    @PostMapping(value = "/register")
    public Result register(@RequestBody SystemUserVo systemUserVo){
        int count = systemUserService.registerSystemUser(systemUserVo);
        System.out.println("返回值"+count);
        if (count == -1){
            return ResultUtil.error(201,"用户已存在");
        }
        return ResultUtil.successMsg("新增系统用户成功");
    }

    @ApiOperation("管理页登录")
    @PostMapping(value = "/login")
    public Result login(@RequestParam("username") String username,@RequestParam("password")String password){
        UserDetails systemUserDTO = securityUserDetailsService.loadUserByUsername(username);
        if (systemUserDTO != null){
            systemUserDTO.getPassword().equals(password);
        }
        return ResultUtil.success();
    }
}
