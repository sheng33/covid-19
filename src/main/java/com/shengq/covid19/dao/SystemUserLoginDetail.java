package com.shengq.covid19.dao;

import com.shengq.covid19.dto.SystemUserDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.security.core.CredentialsContainer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class SystemUserLoginDetail extends SystemUser implements UserDetails {

    private static final long serialVersionUID = -47103761267627684L;
    private List<GrantedAuthority> authorities;

    public SystemUserLoginDetail(SystemUser systemUser, List<GrantedAuthority> authorities) {
        this.id = systemUser.getId();
        this.name = systemUser.getName();
        this.username = systemUser.getUsername();
        this.password = systemUser.getPassword();
        this.mobile = systemUser.getMobile();
        this.status = systemUser.getStatus();
        this.permission = systemUser.getPermission();
        this.updateTime = systemUser.getUpdateTime();
        this.createTime = systemUser.getCreateTime();
        this.authorities = authorities;
    }

    public SystemUserLoginDetail() {

    }

    @Override
    public String toString() {
        return "SystemUserLoginDetail{" +
                "authorities=" + authorities +
                ", id=" + id +
                ", name='" + name + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", mobile='" + mobile + '\'' +
                ", status=" + status +
                ", permission=" + permission +
                ", updateTime='" + updateTime + '\'' +
                ", createTime='" + createTime + '\'' +
                '}';
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }


}
