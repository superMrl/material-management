package com.jckj.materialmanagement.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.base.Optional;
import com.google.common.base.Strings;
import com.jckj.materialmanagement.config.error.ErrorCode;
import com.jckj.materialmanagement.config.response.GlobalResponse;
import com.jckj.materialmanagement.enumd.RedisKeys;
import com.jckj.materialmanagement.model.User;
import com.jckj.materialmanagement.service.UserService;
import com.jckj.materialmanagement.utils.ComUtil;
import com.jckj.materialmanagement.utils.RedisUtil;
import io.netty.util.internal.MathUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.spring.web.json.Json;
import sun.security.provider.MD5;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Enumeration;
import java.util.UUID;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author
 * @since 2020-06-20
 */
@Slf4j
@RestController
@Api(tags = "登录")
public class LoginController {

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private UserService userService;

    @Resource
    private PasswordEncoder passwordEncoder;


    @ApiOperation(value = "登录", httpMethod = "POST")
    @RequestMapping("/login")
    public GlobalResponse login(HttpServletRequest request, HttpServletResponse response) {
        String token = request.getHeader("token");
        String telPhone = request.getParameter("telPhone");
        String userPassword = request.getParameter("userPassword");

        if (Strings.isNullOrEmpty(token) ) {
            //第一次登录
            User user = userService.queryLoginUser(telPhone);
            if (ComUtil.isNull(user)) {
                return GlobalResponse.fail(ErrorCode.USER_LOGIN_FAIL);
            }

            if (!passwordEncoder.matches(userPassword, user.getUserPassword())) {
                return GlobalResponse.fail(ErrorCode.USER_LOGIN_FAIL);
            }

            String uuid = UUID.randomUUID().toString();
            String key = String.format(RedisKeys.LOGIN_USER_TOKEN, user.getTelPhone());
            redisUtil.set(key, uuid, 60 * 30);//指定30分钟
            JSONObject u = new JSONObject();
            u.put("userName",user.getUserName());
            u.put("telPhone",user.getTelPhone());
            u.put("companyId",user.getCompanyId());

            JSONObject json = new JSONObject();
            json.put("token",uuid);
            json.put("user", JSONObject.toJSONString(u));
            return GlobalResponse.success(ErrorCode.USER_LOGIN_SUCCESS, json);
        }

        //验证token
        Object value = redisUtil.get(String.format(RedisKeys.LOGIN_USER_TOKEN, telPhone));
        if (value == null || Strings.isNullOrEmpty(value.toString())) {
            return GlobalResponse.fail(ErrorCode.USER_TOKEN_FAIL);
        }

        if (!token.equals(value.toString())) {
            return GlobalResponse.fail(ErrorCode.USER_TOKEN_FAIL);
        }



        return GlobalResponse.success();
    }

    @ApiOperation(value = "退出", httpMethod = "POST")
    @RequestMapping("/logout")
    public GlobalResponse logout(@RequestBody User user) {

        //清理token
        redisUtil.del(String.format(RedisKeys.LOGIN_USER_TOKEN, user.getTelPhone()));
        return GlobalResponse.success();
    }


}
