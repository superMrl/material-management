package com.jckj.materialmanagement.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jckj.materialmanagement.model.OutStorageInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 出库表 Mapper 接口
 * </p>
 *
 * @author 
 * @since 2020-07-06
 */
public interface OutStorageInfoMapper extends BaseMapper<OutStorageInfo> {

    List<OutStorageInfo> selectTodayOutStore(@Param(value = "outTime") String outTime);

}
