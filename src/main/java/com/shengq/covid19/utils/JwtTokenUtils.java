package com.shengq.covid19.utils;

import com.alibaba.fastjson.JSON;
import com.shengq.covid19.dao.SystemUserLoginDetail;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Clock;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.DefaultClock;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
@Data
@Component
public class JwtTokenUtils implements Serializable {
    private static final long serialVersionUID = -3301605591108950415L;

    @Value("${jwt.secret}")
    public   String secret;

    @Value("${jwt.expiration}")
    public Long expiration;

    @Value("${jwt.token}")
    public String tokenHeader;

    private Clock clock = DefaultClock.INSTANCE;

    public  String createAccessToken(SystemUserLoginDetail selfUserEntity){
        // 登陆成功生成JWT
        String token = Jwts.builder()
                // 放入用户名和用户ID
                .setId(selfUserEntity.getId()+"")
                // 主题
                .setSubject(selfUserEntity.getUsername())
                // 签发时间
                .setIssuedAt(new Date())
                // 签发者
                .setIssuer("sans")
                // 自定义属性 放入用户拥有权限
                .claim("authorities", JSON.toJSONString(selfUserEntity.getAuthorities()))
                // 失效时间
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                // 签名算法和密钥
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
        System.out.println("token失效时间"+new Date(System.currentTimeMillis() + expiration*1000));
        System.out.println("token失效时间"+expiration);
        return token;
    }

    private Date calculateExpirationDate(Date createdDate) {
        return new Date(createdDate.getTime() + expiration);
    }

    public Boolean validateToken(String token, SystemUserLoginDetail user) {
        final String username = getUsernameFromToken(token);
        return (username.equals(user.getUsername())
                && !isTokenExpired(token)
        );
    }

    public String getUsernameFromToken(String token) {
        return getClaimFromToken(token, Claims::getSubject);
    }

    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody();
    }


    private Boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(clock.now());
    }

    public Date getExpirationDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }

}
