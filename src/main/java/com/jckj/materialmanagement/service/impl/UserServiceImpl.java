package com.jckj.materialmanagement.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.jckj.materialmanagement.config.error.ErrorCode;
import com.jckj.materialmanagement.config.response.GlobalResponse;
import com.jckj.materialmanagement.exception.BusinessException;
import com.jckj.materialmanagement.mapper.UserMapper;
import com.jckj.materialmanagement.model.User;
import com.jckj.materialmanagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Objects;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author 
 * @since 2020-06-30
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService{


    @Resource
    private PasswordEncoder passwordEncoder;
    @Resource
    private  UserMapper userMapper;

    /**
     * 查询登录用户
     * @param telepbone
     * @return
     */
    @Override
    public User queryLoginUser(String telepbone) {
        return userMapper.selectByTelephone(telepbone);
    }

    /**
     * 注册用户
     * @param user
     * @return
     */
    @Override
    public GlobalResponse register(User user) {
        User userDB = userMapper.selectByTelephone(user.getTelPhone());
        if(!Objects.equals(null,userDB)){
            throw new BusinessException(ErrorCode.USER_TELEPHONE_HAS_REGISTER);
        }
        user.setUserPassword(passwordEncoder.encode(user.getUserPassword()));
        int insert = userMapper.insert(user);
        if(insert < 0){
            throw new BusinessException(ErrorCode.USER_REGISTER_FAIL);
        }
        return GlobalResponse.success();
    }


}
