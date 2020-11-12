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

import java.util.ArrayList;
import java.util.List;

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
    @ApiImplicitParam(name = "username",value = "用户名",required = true,paramType = "query"
            ,dataType = "String",defaultValue = "sheng")
    @GetMapping(value = "/getUserInfo")
    public Result<?> getUserInfo(String username){
        SystemUserDTO systemUserVo = systemUserService.getUserInfoByName(username);
        if (systemUserVo == null){
            return ResultUtil.error(201,"无此账户");
        }
        String perssion = systemUserVo.getPermission()==0?"admin":"guest";
        return ResultUtil.success(new SystemUserVo(systemUserVo.getUserid(),systemUserVo.getName(),
                systemUserVo.getUsername(),systemUserVo.getMobile(),
                systemUserVo.getStatus(),perssion));
    }
    @ApiOperation("查询管理员用户列表")
    @GetMapping(value = "/getUserList")
    public Result<?> getUserList(){
        List<SystemUserDTO> dtoList = systemUserService.getAllUser();
        List<SystemUserVo>  voList = new ArrayList<>();
        dtoList.forEach(systemUser -> {
            String perssion = systemUser.getPermission()==0?"admin":"guest";
            voList.add(new SystemUserVo(systemUser.getUserid(),systemUser.getName(),systemUser.getUsername(),
                    systemUser.getMobile(),systemUser.getStatus(),perssion));
        });
        return ResultUtil.success(voList);
    }
    @ApiOperation("删除系统用户")
    @ApiImplicitParam(name = "id",value = "用户id")
    @GetMapping(value = "/delUser")
    public Result<?> delSystemUser(Integer id){
        int count = systemUserService.delSystemUser(id);
        String msg = count==1?"删除成功":"删除失败";
        return ResultUtil.success(msg);
    }

    @ApiOperation("修改系统用户密码")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userid",value = "用户id"),
            @ApiImplicitParam(name = "name",value = "登录名"),
            @ApiImplicitParam(name = "username",value = "用户姓名"),
            @ApiImplicitParam(name = "oldPassword",value = "原密码"),
            @ApiImplicitParam(name = "newPassword",value = "新密码")
    })
    @PostMapping("/changePassword")
    public Result<?> changePassword(Integer userid,String name,String username,String oldPassword,String newPassword){
        int pd = systemUserService.updateSystemUserInfo(userid,name,username,oldPassword,newPassword);
        String msg = pd==1?"修改成功":"密码错误";
        return ResultUtil.success(msg);
    }
}
