package com.shengq.covid19.service.Impl;

import com.shengq.covid19.dto.SystemUserDTO;
import com.shengq.covid19.service.SystemUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author shengQ
 */
@Service
public class SecurityUserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    SystemUserService userService;

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        SystemUserDTO systemUser = userService.getUserInfoByName(name);
        if (systemUser == null) {
            throw new UsernameNotFoundException("管理员账户不存在");
        }
        return new org.springframework.security.core.userdetails.User(systemUser.getUsername(),
                systemUser.getPassword(),
                systemUser.getStatus()==0,
                true,
                true,
                true,
                getGrantedAuthority(systemUser));
    }

    public List<GrantedAuthority> getGrantedAuthority(SystemUserDTO user){
        List<GrantedAuthority> list = new ArrayList<>();
        list.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        return list;
    }
}
