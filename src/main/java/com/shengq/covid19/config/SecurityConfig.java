package com.shengq.covid19.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.shengq.covid19.filter.JWTAuthorizationFilter;
import com.shengq.covid19.filter.UserAuthenticationFilter;
import com.shengq.covid19.handler.*;
import com.shengq.covid19.service.Impl.SecurityUserDetailsServiceImpl;
import com.shengq.covid19.utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.expression.DefaultWebSecurityExpressionHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.CorsUtils;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author shengQ
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    MyPasswordEncoder myPasswordEncoder;
    @Autowired
    SecurityUserDetailsServiceImpl userDetailsService;
    /**
     * 自定义登录成功处理器
     */
    @Autowired
    private UserLoginSuccessHandler userLoginSuccessHandler;
    /**
     * 自定义登录失败处理器
     */
    @Autowired
    private UserLoginFailureHandler userLoginFailureHandler;
    /**
     * 自定义注销成功处理器
     */
    @Autowired
    private UserLogoutSuccessHandler userLogoutSuccessHandler;
    /**
     * 自定义暂无权限处理器
     */
    @Autowired
    private UserAuthAccessDeniedHandler userAuthAccessDeniedHandler;
    /**
     * 自定义未登录的处理器
     */
    @Autowired
    private UserAuthenticationEntryPointHandler userAuthenticationEntryPointHandler;
    /**
     * 自定义登录逻辑验证器
     */
    @Autowired
    private UserAuthenticationProvider userAuthenticationProvider;
    /**
     * 注入自定义PermissionEvaluator
     */
    @Bean
    public DefaultWebSecurityExpressionHandler userSecurityExpressionHandler(){
        DefaultWebSecurityExpressionHandler handler = new DefaultWebSecurityExpressionHandler();
        handler.setPermissionEvaluator(new UserPermissionEvaluator());
        return handler;
    }


    /**
     * anyRequest          |   匹配所有请求路径
     * access              |   SpringEl表达式结果为true时可以访问
     * anonymous           |   匿名可以访问
     * denyAll             |   用户不能访问
     * fullyAuthenticated  |   用户完全认证可以访问（非remember-me下自动登录）
     * hasAnyAuthority     |   如果有参数，参数表示权限，则其中任何一个权限可以访问
     * hasAnyRole          |   如果有参数，参数表示角色，则其中任何一个角色可以访问
     * hasAuthority        |   如果有参数，参数表示权限，则其权限可以访问
     * hasIpAddress        |   如果有参数，参数表示IP地址，如果用户IP和参数匹配，则可以访问
     * hasRole             |   如果有参数，参数表示角色，则其角色可以访问
     * permitAll           |   用户可以任意访问
     * rememberMe          |   允许通过remember-me登录的用户访问
     * authenticated       |   用户登录后可访问
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .addFilterAt(customAuthenticationFilter(),UsernamePasswordAuthenticationFilter.class)
                .addFilter(jwtAuthorizationFilter())
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .httpBasic().authenticationEntryPoint(userAuthenticationEntryPointHandler)
                .and()
                .authorizeRequests()
                    .antMatchers("/swagger-ui.html").permitAll()
                    .antMatchers("/webjars/**").permitAll()
                    .antMatchers("/v2/**").permitAll()
                    .antMatchers("/swagger-resources/**").permitAll()
                    .antMatchers("/admin/login").permitAll()
                    .antMatchers("/api/decode").permitAll()
                    .antMatchers("/client/**").permitAll()
                    .antMatchers("/clientsysConfig/getBanner").permitAll()
                    .antMatchers("/clientsysConfig/getMenuList").permitAll()
                    .antMatchers("/static/**").permitAll()
                    .antMatchers(HttpMethod.OPTIONS, "/**").anonymous()
                    .anyRequest().authenticated()//必须授权才能访问
                .and()
                .formLogin()
                .successHandler(userLoginSuccessHandler)
                // 配置登录失败自定义处理类
                .failureHandler(userLoginFailureHandler)
                .and()
                // 配置登出地址
                .logout()
                .logoutUrl("/login")
                // 配置用户登出自定义处理类
                .logoutSuccessHandler(userLogoutSuccessHandler)
                .and()
                // 配置没有权限自定义处理类
                .exceptionHandling().accessDeniedHandler(userAuthAccessDeniedHandler)
                .and();
        //开启跨域访问
        http.cors().disable();
        //开启模拟请求，比如API POST测试工具的测试，不开启时，API POST为报403错误
        http.csrf().disable();
    }
    /**
     * 认证 AuthenticationProvider
     */
    @Bean
    public AuthenticationProvider authenticationProvider() {
        return userAuthenticationProvider;
    }

    @Bean
    public JWTAuthorizationFilter jwtAuthorizationFilter() throws Exception {
        return new JWTAuthorizationFilter(authenticationManager());
    }
    @Bean
    UserAuthenticationFilter customAuthenticationFilter() throws Exception {
        UserAuthenticationFilter filter = new UserAuthenticationFilter();
        filter.setAuthenticationManager(super.authenticationManager());
        filter.setAuthenticationSuccessHandler(userLoginSuccessHandler);
        filter.setFilterProcessesUrl("/admin/login");
        return filter;
    }
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                // 注入身份的 Bean
                .authenticationProvider(authenticationProvider())
                .userDetailsService(userDetailsService)
                // 默认登陆的加密，自定义登陆的时候无效
                .passwordEncoder(myPasswordEncoder);
    }

}