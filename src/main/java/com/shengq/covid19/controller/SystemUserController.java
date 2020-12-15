package com.shengq.covid19.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.shengq.covid19.config.Result;
import com.shengq.covid19.dao.SystemUserLoginDetail;
import com.shengq.covid19.dto.SystemUserDTO;
import com.shengq.covid19.exception.GlobalException;
import com.shengq.covid19.service.ClientUserService;
import com.shengq.covid19.service.Impl.SecurityUserDetailsServiceImpl;
import com.shengq.covid19.service.SystemUserService;
import com.shengq.covid19.utils.JwtTokenUtils;
import com.shengq.covid19.utils.ResultUtil;
import com.shengq.covid19.vo.SystemUserVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import org.springframework.security.core.token.Token;
import org.springframework.security.core.token.TokenService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * @author shengQ
 */
@RestController
@RequestMapping("/admin")
@Api(tags = "管理用户端口")
@CrossOrigin
public class SystemUserController {
    final SystemUserService systemUserService;
    final SecurityUserDetailsServiceImpl securityUserDetailsService;
    final ClientUserService clientUserService;
    final JwtTokenUtils jwtTokenUtil;

    public SystemUserController(ClientUserService clientUserService, SystemUserService systemUserService, SecurityUserDetailsServiceImpl securityUserDetailsService, JwtTokenUtils jwtTokenUtil) {
        this.clientUserService = clientUserService;
        this.systemUserService = systemUserService;
        this.securityUserDetailsService = securityUserDetailsService;
        this.jwtTokenUtil = jwtTokenUtil;
    }


    @ApiOperation("系统用户注册接口")
    @PostMapping(value = "/register")
    public Result<Object> register(@RequestBody SystemUserDTO systemUserDTO){
        int count = systemUserService.registerSystemUser(systemUserDTO);
        if (count == -1){
            return ResultUtil.error(201,"用户已存在");
        }
        return ResultUtil.successMsg("新增系统用户成功");
    }

//    @ApiOperation("管理页登录")
//    @PostMapping(value = "/login")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "username",value = "用户名",required = true,paramType = "path"
//                    ,dataType = "String",defaultValue = "sheng"),
//            @ApiImplicitParam(name = "password",value = "密码",required = true,paramType = "path"
//            ,dataType = "String",defaultValue = "12345")
//    })
//    public Result<Object> login(@RequestBody JsonNode json, HttpServletRequest request) throws JsonProcessingException {
//        String username = json.get("username").asText();
//        String password = json.get("password").asText();
//        SystemUserLoginDetail systemUserDTO = securityUserDetailsService.loadUserByUsername(username);
//        if (systemUserDTO == null){
//            return ResultUtil.error(402,"用户不存在");
//        }else {
//            System.out.println(systemUserDTO.getPassword());
//            boolean code = systemUserDTO.getPassword().equals(password);
//            if (!code){
//                return ResultUtil.error(402,"密码错误");
//            }
//        }
//        String token = jwtTokenUtil.generateToken(systemUserDTO);
//        System.out.println("test:::"+token);
//        SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(username, null, new ArrayList<>()));
//        System.out.println("toooo::"+jwtTokenUtil.getUsernameFromToken(token));
//        Authentication authentication1 = SecurityContextHolder.getContext().getAuthentication();
//        System.out.println(authentication1);
//        systemUserDTO.setPassword(null);
//        ObjectMapper mapper = new ObjectMapper();
//        String str = "{\"token\":\""+token+"\"}";
//        JsonNode jsonNode = mapper.readTree(str);
//        return ResultUtil.success("登录成功", jsonNode);
//    }



    @PreAuthorize("hasRole('admin')")
    @ApiOperation("查询用户详情")
    @ApiImplicitParam(name = "username",value = "用户名",required = true,paramType = "query"
            ,dataType = "String",defaultValue = "sheng")
    @GetMapping(value = "/getUserInfo")
    public Result<?> getUserInfo(String username) throws GlobalException {
        if (username==null){
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            username = authentication.getName();
        }
        SystemUserDTO systemUserVo = systemUserService.getUserInfoByName(username);
        if (systemUserVo == null){
            return ResultUtil.error(201,"无此账户");
        }
        String perssion = systemUserVo.getPermission()==0?"admin":"guest";
        return ResultUtil.success("获取成功",new SystemUserVo(systemUserVo.getId(),systemUserVo.getName(),
                systemUserVo.getUsername(),systemUserVo.getMobile(),
                systemUserVo.getStatus(),perssion));
    }
    @ApiOperation("查询管理员用户列表")
    @GetMapping(value = "/getUserList")
    public Result<?> getUserList(@RequestParam(value = "page",defaultValue = "1")int page,
                                 @RequestParam(value = "size",defaultValue = "10")int size){
        PageHelper.startPage(page,size);
        List<SystemUserDTO> dtoList = systemUserService.getAllUser();
        List<SystemUserVo>  voList = new ArrayList<>();
        dtoList.forEach(systemUser -> {
            String perssion = systemUser.getPermission()==0?"admin":"guest";
            voList.add(new SystemUserVo(systemUser.getId(),systemUser.getName(),systemUser.getUsername(),
                    systemUser.getMobile(),systemUser.getStatus(),perssion));
        });
        PageInfo<SystemUserVo> pageInfo = new PageInfo<>(voList);
        return ResultUtil.success("查询成功",pageInfo);
    }
    @ApiOperation("删除系统用户")
    @ApiImplicitParam(name = "id",value = "用户id")
    @GetMapping(value = "/delUser")
    public Result<?> delSystemUser(Integer id){
        int count = systemUserService.delSystemUser(id);
        String msg = count==1?"删除成功":"删除失败";
        return ResultUtil.success("删除成功",msg);
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
        return ResultUtil.success(msg,null);
    }





}
