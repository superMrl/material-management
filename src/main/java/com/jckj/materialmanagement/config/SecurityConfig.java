package com.jckj.materialmanagement.config;

import com.jckj.materialmanagement.config.login.LoginFailureHandler;
import com.jckj.materialmanagement.config.login.LoginSuccessHandler;
import com.jckj.materialmanagement.config.login.LoginValidateAuthenticationProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.DigestUtils;

import javax.annotation.Resource;

/**
 * spring security
 * 核心配置类配置
 */

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    //自定义认证
    @Resource
    private LoginValidateAuthenticationProvider loginValidateAuthenticationProvider;

    //登录成功handler
    @Resource
    private LoginSuccessHandler loginSuccessHandler;

    //登录失败handler
    @Resource
    private LoginFailureHandler loginFailureHandler;

    /**
     * 权限核心配置
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //基础设置
//        http.httpBasic()//配置HTTP基本身份验证
//                .and()
//                .authorizeRequests()
//                .anyRequest().authenticated()//所有请求都需要认证
//                .and()
//                .formLogin()
////                .loginPage("/login")//登录页面url
////                .usernameParameter("username")
////                .passwordParameter("password")
//                .loginProcessingUrl("/login")//登录验证url
////                .defaultSuccessUrl("/index")//成功登录跳转
//                .successHandler(loginSuccessHandler)//成功登录处理器
//                .failureHandler(loginFailureHandler)//失败登录处理器
//                .permitAll();//登录成功后有权限访问所有页面
//        //关闭csrf跨域攻击防御
//        http.csrf().disable()
//                //基于token,不需要session
//                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);


        //关闭验证
        http.csrf().disable()
                .authorizeRequests()
                .anyRequest().permitAll()
                .and().logout().permitAll();

    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //这里要设置自定义认证
        auth.authenticationProvider(loginValidateAuthenticationProvider);
    }

    /**
     * BCrypt加密
     * @return
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
