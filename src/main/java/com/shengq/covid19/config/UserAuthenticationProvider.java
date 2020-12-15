package com.shengq.covid19.config;

import com.shengq.covid19.dao.SysRole;
import com.shengq.covid19.dao.SystemUserLoginDetail;
import com.shengq.covid19.mapper.SysUserRolesMapper;
import com.shengq.covid19.service.Impl.SecurityUserDetailsServiceImpl;
import com.shengq.covid19.service.SystemUserService;
import com.shengq.covid19.utils.MyPasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 自定义登录验证
 * @author zwq
 * @date 2020-04-04
 **/
@Component
public class UserAuthenticationProvider implements AuthenticationProvider {
    @Autowired
    private SecurityUserDetailsServiceImpl selfUserDetailsService;
    @Autowired
    private SysUserRolesMapper sysUserRolesMapper;


    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        System.out.println("authentication:::"+authentication);
        // 获取表单输入中返回的用户名
        String userName = authentication.getName();
        // 获取表单中输入的密码
        String password = (String) authentication.getCredentials();
        if (authentication.getName()==null){
            throw new UsernameNotFoundException("用户名不存在");
        }
        // 查询用户是否存在
        SystemUserLoginDetail userInfo = selfUserDetailsService.loadUserByUsername(userName);
        if (userInfo == null) {
            throw new UsernameNotFoundException("用户名不存在");
        }
        // 我们还要判断密码是否正确，这里我们的密码使用BCryptPasswordEncoder进行加密的
        if (!new MyPasswordEncoder().matches(password,userInfo.getPassword())) {
            throw new BadCredentialsException("密码不正确");
        }

        // 还可以加一些其他信息的判断，比如用户账号已停用等判断
        if (userInfo.getStatus()!=1){
            throw new LockedException("该用户已被冻结");
        }
        // 角色集合
        Set<GrantedAuthority> authorities = new HashSet<>();
        // 查询用户角色
        List<SysRole> sysRoleEntityList = sysUserRolesMapper.findByUserRoles(userInfo.getId());
        for (SysRole sysRoleEntity: sysRoleEntityList){
            authorities.add(new SimpleGrantedAuthority("ROLE_" + sysRoleEntity.getName()));
        }
        authorities.forEach(System.out::println);
//        userInfo.setAuthorities(authorities);
        // 进行登录
        return new UsernamePasswordAuthenticationToken(userInfo, password, authorities);
    }
    @Override
    public boolean supports(Class<?> authentication) {
        return true;
    }
}
