package com.shengq.covid19.config;

import com.shengq.covid19.dao.SysRole;
import com.shengq.covid19.dao.SystemUserLoginDetail;
import com.shengq.covid19.mapper.SysUserRolesMapper;
import com.shengq.covid19.service.SystemUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 自定义权限注解验证
 * @author zwq
 * @date 2020-04-04
 **/
@Component
public class UserPermissionEvaluator implements PermissionEvaluator {
    @Autowired
    private SysUserRolesMapper sysUserService;

    /**
     * hasPermission鉴权方法
     * 这里仅仅判断PreAuthorize注解中的权限表达式
     * 实际中可以根据业务需求设计数据库通过targetUrl和permission做更复杂鉴权
     * 当然targetUrl不一定是URL可以是数据Id还可以是管理员标识等,这里根据需求自行设计
     * @author zwq
     * @date 2020/4/4
     * @param authentication
     * @param targetUrl
     * @param permission
     * @return
     **/
    @Override
    public boolean hasPermission(Authentication authentication, Object targetUrl, Object permission) {
        // 获取用户信息
        SystemUserLoginDetail selfUserEntity =(SystemUserLoginDetail) authentication.getPrincipal();
        System.out.println("鉴定："+selfUserEntity);
        // 查询用户权限(这里可以将权限放入缓存中提升效率)
        Set<String> permissions = new HashSet<>();
        List<SysRole> sysMenuEntityList = sysUserService.findByUserRoles(selfUserEntity.getId());
        System.out.println("鉴定："+sysMenuEntityList);
        System.out.println("被鉴定："+permission.toString());
        for (SysRole sysMenuEntity:sysMenuEntityList) {
            permissions.add(sysMenuEntity.getName());
        }
        // 权限对比
        if (permissions.contains(permission.toString())){
            return true;
        }
        return false;
    }
    @Override
    public boolean hasPermission(Authentication authentication, Serializable targetId, String targetType, Object permission) {
        return false;
    }
}
