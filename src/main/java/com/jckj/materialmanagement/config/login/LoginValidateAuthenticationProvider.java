package com.jckj.materialmanagement.config.login;


import com.jckj.materialmanagement.model.User;
import com.jckj.materialmanagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.util.DigestUtils;

import javax.annotation.Resource;
import java.util.Objects;

/**
 * 自定义认证核心
 */
@Component
public class LoginValidateAuthenticationProvider implements AuthenticationProvider {

    @Resource
    private UserService userService;

    /**
     * 解密用的
     */

    @Resource
    private PasswordEncoder passwordEncoder;


    /**
     * 进行身份验证
     *
     * @param authentication
     * @return
     * @throws AuthenticationException
     */
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        //获取输入的手机号
        String username = authentication.getName();
        //获取输入的明文
        String rawPassword = (String) authentication.getCredentials();
        //查询用户是否存在
        User user = userService.queryLoginUser(username);
        if (Objects.equals(user, null)) {
            throw new DisabledException("用户不存在,请先注册!");
        }
        //先不设置token,后期增加

        //后期增加手机验证码功能

        //对比密码
        if (!passwordEncoder.matches(rawPassword, user.getUserPassword())) {
            throw new BadCredentialsException("密码输入错误!");
        }
//        if (!user.isEnabled()) {
//            throw new DisabledException("该账户已被禁用，请联系管理员");
//        } else if (!user.isAccountNonLocked()) {
//            throw new LockedException("该账号已被锁定");
//        } else if (!user.isAccountNonExpired()) {
//            throw new AccountExpiredException("该账号已过期，请联系管理员");
//        } else if (!user.isCredentialsNonExpired()) {
//            throw new CredentialsExpiredException("该账户的登录凭证已过期，请重新登录");
//        }

        return new UsernamePasswordAuthenticationToken(user, rawPassword, user.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        //确保authentication能转成该类
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
