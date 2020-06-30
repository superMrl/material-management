package com.jckj.materialmanagement.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.jckj.materialmanagement.mapper.UserMapper;
import com.jckj.materialmanagement.model.User;
import com.jckj.materialmanagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author 
 * @since 2020-06-30
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {


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
}
