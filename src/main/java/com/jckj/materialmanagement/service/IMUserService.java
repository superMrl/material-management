package com.jckj.materialmanagement.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jckj.materialmanagement.model.MUser;


/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 
 * @since 2020-06-20
 */
public interface IMUserService {

    MUser getUser(Long id);
}
