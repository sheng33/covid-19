package com.shengq.covid19.service.Impl;

import com.shengq.covid19.dao.SysRole;
import com.shengq.covid19.dao.SystemUser;
import com.shengq.covid19.dao.SystemUserLoginDetail;
import com.shengq.covid19.dto.SystemUserDTO;
import com.shengq.covid19.mapper.SysUserRolesMapper;
import com.shengq.covid19.mapper.SystemUserMapper;
import com.shengq.covid19.service.SystemUserService;
import lombok.SneakyThrows;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author shengQ
 */
@Service
public class SecurityUserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    SystemUserMapper systemUserMapper;
    @Autowired
    SysUserRolesMapper sysUserRolesMapper;


    @SneakyThrows
    @Override
    public SystemUserLoginDetail loadUserByUsername(String name) throws UsernameNotFoundException {
        SystemUser systemUser = systemUserMapper.findByName(name);
        System.out.println(name);
        if (systemUser == null) {
            throw new UsernameNotFoundException("管理员账户不存在");
        }
        List<GrantedAuthority> authorities = new ArrayList<>();
        // 查询用户角色
        List<SysRole> sysRoleEntityList = sysUserRolesMapper.findByUserRoles(systemUser.getId());
        for (SysRole sysRoleEntity: sysRoleEntityList){
            authorities.add(new SimpleGrantedAuthority("ROLE_" + sysRoleEntity.getName()));
        }
        SystemUserLoginDetail systemUserLoginDetail = new SystemUserLoginDetail(systemUser,authorities);


        return systemUserLoginDetail;
    }

}
