package com.jckj.materialmanagement.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jckj.materialmanagement.config.response.GlobalResponse;
import com.jckj.materialmanagement.model.User;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author 
 * @since 2020-06-30
 */
public interface UserService extends IService<User>{

    User queryLoginUser(String telepbone);

    GlobalResponse register(User user);
}
