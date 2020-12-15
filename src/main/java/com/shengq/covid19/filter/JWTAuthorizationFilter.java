package com.shengq.covid19.filter;

import com.alibaba.fastjson.JSONObject;
import com.shengq.covid19.dao.SystemUserLoginDetail;
import com.shengq.covid19.utils.JwtTokenUtils;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
@Slf4j
public class JWTAuthorizationFilter extends BasicAuthenticationFilter {
    @Autowired
    JwtTokenUtils jwtTokenUtils;

    public JWTAuthorizationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain chain) throws IOException, ServletException {
        String tokenHeader = request.getHeader(jwtTokenUtils.getTokenHeader());
        System.out.println("得到的token"+tokenHeader);
        if (null != tokenHeader) {
            try {
                // 截取JWT前缀
                String token = tokenHeader;
                // 解析JWT
                Claims claims = Jwts.parser()
                        .setSigningKey(jwtTokenUtils.getSecret())
                        .parseClaimsJws(token)
                        .getBody();
                // 获取用户名
                String username = claims.getSubject();
                String userId = claims.getId();
                System.out.println(claims);
                System.out.println(username);
                if (!StringUtils.isEmpty(username) && !StringUtils.isEmpty(userId)) {
                    // 获取角色
                    List<GrantedAuthority> authorities = new ArrayList<>();
                    String authority = claims.get("authorities").toString();
                    if (!StringUtils.isEmpty(authority)) {
                        List<Map<String, String>> authorityMap = JSONObject.parseObject(authority, List.class);
                        for (Map<String, String> role : authorityMap) {
                            if (!StringUtils.isEmpty(role.toString())) {
                                authorities.add(new SimpleGrantedAuthority(role.get("authority")));
                            }
                        }
                    }
                    //组装参数
                    SystemUserLoginDetail selfUserEntity = new SystemUserLoginDetail();
                    selfUserEntity.setUsername(claims.getSubject());
                    selfUserEntity.setId(Integer.parseInt(claims.getId()));
                    selfUserEntity.setAuthorities(authorities);
                    System.out.println("当前权限"+authorities);
                    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(selfUserEntity, userId, authorities);
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            } catch (ExpiredJwtException e) {
                log.info("Token过期");
            } catch (Exception e) {
                log.info("Token无效");
            }
        }
        chain.doFilter(request, response);
        return;
    }
}