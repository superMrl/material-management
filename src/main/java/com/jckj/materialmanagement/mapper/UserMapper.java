package com.jckj.materialmanagement.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jckj.materialmanagement.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 用户表 Mapper 接口
 * </p>
 *
 * @author 
 * @since 2020-06-30
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

    User selectByTelephone(@Param(value = "telPhone") String telPhone);

    User selectLoginUserByTp(@Param(value = "telPhone")String telPhone, @Param(value = "userPassword")String userPassword);
}
