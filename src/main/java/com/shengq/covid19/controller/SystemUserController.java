package com.shengq.covid19.controller;

import com.shengq.covid19.config.Result;
import com.shengq.covid19.dto.SystemUserDTO;
import com.shengq.covid19.service.Impl.SecurityUserDetailsServiceImpl;
import com.shengq.covid19.service.SystemUserService;
import com.shengq.covid19.utils.ResultUtil;
import com.shengq.covid19.vo.SystemUserVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

/**
 * @author shengQ
 */
@RestController()
@RequestMapping("/admin")
@Api(tags = "管理用户端口")
public class SystemUserController {
    @Autowired
    SystemUserService systemUserService;
    @Autowired
    SecurityUserDetailsServiceImpl securityUserDetailsService;
    @ApiOperation("系统用户注册接口")
    @PostMapping(value = "/register")
    public Result<String> register(@RequestBody SystemUserDTO systemUserDTO){
        int count = systemUserService.registerSystemUser(systemUserDTO);
        if (count == -1){
            return ResultUtil.error(201,"用户已存在");
        }
        return ResultUtil.successMsg("新增系统用户成功");
    }

    @ApiOperation("管理页登录")
    @PostMapping(value = "/login")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username",value = "用户名",required = true,paramType = "path"
                    ,dataType = "String",defaultValue = "sheng"),
            @ApiImplicitParam(name = "password",value = "密码",required = true,paramType = "path"
            ,dataType = "String",defaultValue = "12345")
    })
    public Result<String> login(@RequestParam("username") String username,@RequestParam("password")String password){
        UserDetails systemUserDTO = securityUserDetailsService.loadUserByUsername(username);
        if (systemUserDTO != null){
            systemUserDTO.getPassword().equals(password);
        }
        return ResultUtil.successMsg("登录成功");
    }


    @ApiOperation("查询用户详情")
    @GetMapping(value = "/getUserInfo")
    @ApiImplicitParam(name = "username",value = "用户名",required = true,paramType = "query"
            ,dataType = "String",defaultValue = "sheng")
    public Result<?> getUserInfo(String username){
        SystemUserDTO systemUserVo = systemUserService.getUserInfoByName(username);
        if (systemUserVo == null){
            return ResultUtil.error(201,"无此账户");
        }
        String perssion = systemUserVo.getPermission()==0?"admin":"guest";
        return ResultUtil.success(new SystemUserVo(systemUserVo.getUsername(),systemUserVo.getMobile(),systemUserVo.getStatus(),perssion));

    }
}
