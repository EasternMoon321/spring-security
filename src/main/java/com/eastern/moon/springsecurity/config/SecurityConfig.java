package com.eastern.moon.springsecurity.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        super.configure(auth);
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        super.configure(http);
        http.exceptionHandling().accessDeniedPage("/unauthorized.html");    // 未授权跳转页面
        http
                .formLogin()
                .loginPage("/login.html")   // 登陆页面
                .loginProcessingUrl("/user/login")  // 提交表单的中转地址，无需创建
                .defaultSuccessUrl("/demo/login")   // 登陆成功地址
                .permitAll()
                .and()
                .authorizeRequests()
                .antMatchers("/", "/demo/hello", "/demo/index")     // 无需验证的地址
                .permitAll()
                .antMatchers("/demo/login")
//                .hasAuthority("/demo/login")    // /demo/login需具有/demo/login权限
                .hasRole("admin")       // /demo/login需具有admin角色
                .anyRequest()
                .authenticated()
                .and()
                .csrf()
                .disable(); // 关闭csrf防护
    }
}
