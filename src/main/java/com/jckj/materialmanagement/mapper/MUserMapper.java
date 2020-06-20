package com.jckj.materialmanagement.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jckj.materialmanagement.model.MUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 
 * @since 2020-06-20
 */
@Mapper
public interface MUserMapper{

    MUser getUserById(@Param(value = "id")Long id);
}
