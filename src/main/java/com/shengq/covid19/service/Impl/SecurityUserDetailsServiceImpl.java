package com.shengq.covid19.service.Impl;

import com.shengq.covid19.dao.SystemUserLoginDetail;
import com.shengq.covid19.dto.SystemUserDTO;
import com.shengq.covid19.service.SystemUserService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author shengQ
 */
@Component
public class SecurityUserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    SystemUserService userService;

    @SneakyThrows
    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        SystemUserDTO systemUser = userService.getUserInfoByName(name);
        System.out.println(systemUser);
        if (systemUser == null) {
            throw new UsernameNotFoundException("管理员账户不存在");
        }
        System.out.println(systemUser.getStatus()==1);
        return new SystemUserLoginDetail(
                systemUser.getId(),systemUser.getUsername(),systemUser.getPassword(),
                systemUser.getStatus()==1,
                getGrantedAuthority(systemUser));
    }

    public List<GrantedAuthority> getGrantedAuthority(SystemUserDTO user){
        List<GrantedAuthority> list = new ArrayList<>();
        list.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        return list;
    }
}
