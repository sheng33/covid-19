package com.shengq.covid19.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.shengq.covid19.service.Impl.SecurityUserDetailsServiceImpl;
import com.shengq.covid19.utils.MyPasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * @author shengQ
 */
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    MyPasswordEncoder myPasswordEncoder;
    @Autowired
    SecurityUserDetailsServiceImpl myCustomUserService;
    @Autowired
    ObjectMapper objectMapper;



    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authenticationProvider(authenticationProvider())
                .authorizeRequests()
                    .antMatchers("/swagger-ui.html").permitAll()
                    .antMatchers("/webjars/**").permitAll()
                    .antMatchers("/v2/**").permitAll()
                    .antMatchers("/swagger-resources/**").permitAll()
                    .antMatchers("/admin/**").hasRole("ADMIN")
                    .antMatchers("/admin/login").permitAll()
                    .antMatchers("/api/decode").permitAll()
                    .antMatchers("/client/**").permitAll()
                    .antMatchers("/clientsysConfig/getBanner").permitAll()
                    .anyRequest().authenticated()//必须授权才能访问
                    .and()
                .httpBasic()
                    //未登录时，进行json格式的提示，很喜欢这种写法，不用单独写一个又一个的类
                    .authenticationEntryPoint((request,response,authException) -> {
                        response.setContentType("application/json;charset=utf-8");
                        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                        PrintWriter out = response.getWriter();
                        Map<String,Object> map = new HashMap<>();
                        map.put("code",403);
                        map.put("message","未登录");
                        out.write(objectMapper.writeValueAsString(map));
                        out.flush();
                        out.close();
                    })
                    .and()
                .formLogin() //使用自带的登录
                    .loginProcessingUrl("/admin/login")
                //登录失败，返回json
                    .failureHandler((request,response,ex) -> {
                        response.setContentType("application/json;charset=utf-8");
                        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                        PrintWriter out = response.getWriter();
                        Map<String,Object> map = new HashMap<>();
                        map.put("code",401);
                        if (ex instanceof UsernameNotFoundException || ex instanceof BadCredentialsException) {
                            map.put("message","用户名或密码错误");
                        } else if (ex instanceof DisabledException) {
                            System.out.println(ex);
                            map.put("message","账户被禁用");
                        } else {
                            map.put("message","登录失败!");
                        }
                        out.write(objectMapper.writeValueAsString(map));
                        out.flush();
                        out.close();
                    })
                    //登录成功，返回json
                    .successHandler((request,response,authentication) -> {
                        Map<String,Object> map = new HashMap<>();
                        map.put("code",200);
                        map.put("message","登录成功");
                        map.put("data",authentication);
                        response.setContentType("application/json;charset=utf-8");
                        PrintWriter out = response.getWriter();
                        out.write(objectMapper.writeValueAsString(map));
                        out.flush();
                        out.close();
                    })
                    .and()
                .exceptionHandling()
                //没有权限，返回json
                    .accessDeniedHandler((request,response,ex) -> {
                        response.setContentType("application/json;charset=utf-8");
                        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                        PrintWriter out = response.getWriter();
                        Map<String,Object> map = new HashMap<>();
                        map.put("code",403);
                        map.put("message", "权限不足");
                        out.write(objectMapper.writeValueAsString(map));
                        out.flush();
                        out.close();
                    })
                .and()
                .logout()
                //退出成功，返回json
                    .logoutSuccessHandler((request,response,authentication) -> {
                        Map<String,Object> map = new HashMap<>();
                        map.put("code",200);
                        map.put("message","退出成功");
                        map.put("data",authentication);
                        response.setContentType("application/json;charset=utf-8");
                        PrintWriter out = response.getWriter();
                        out.write(objectMapper.writeValueAsString(map));
                        out.flush();
                        out.close();
                    })
                 .permitAll();
        //开启跨域访问
        http.cors().disable();
        //开启模拟请求，比如API POST测试工具的测试，不开启时，API POST为报403错误
        http.csrf().disable();
    }

    @Override
    public void configure(WebSecurity web) {
        //对于在header里面增加token等类似情况，放行所有OPTIONS请求。
        web.ignoring().antMatchers(HttpMethod.OPTIONS, "/**");
    }
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //此方法在5.X过时需要提供一个PasswordEncorder的实例，否则后台汇报错误：java.lang.IllegalArgumentException: There is no PasswordEncoder mapped for the id "null"
        //auth.inMemoryAuthentication().withUser("admin").password("123456").roles("ADMIN");
        auth.userDetailsService(myCustomUserService).passwordEncoder(myPasswordEncoder);
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        //对默认的UserDetailsService进行覆盖
        authenticationProvider.setUserDetailsService(myCustomUserService);
        authenticationProvider.setPasswordEncoder(myPasswordEncoder);
        return authenticationProvider;
    }

}