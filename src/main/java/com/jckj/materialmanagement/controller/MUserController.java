package com.jckj.materialmanagement.controller;


import com.jckj.materialmanagement.config.error.ErrorCode;
import com.jckj.materialmanagement.exception.BusinessException;
import com.jckj.materialmanagement.model.User;
import com.jckj.materialmanagement.service.UserService;
import com.jckj.materialmanagement.utils.RedisUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 
 * @since 2020-06-20
 */
@RestController
@Api(tags = "用户接口")
@RequestMapping("/user")
public class MUserController {

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private UserService userService;



    @ApiOperation(value = "根据id获取用户",httpMethod = "POST")
    @RequestMapping("/get")
    public User getUser(@RequestBody User user) {

//        throw new BusinessException(ErrorCode.FAILED);
       return user;
    }
    

}
