package com.jckj.materialmanagement.mapper;

import com.jckj.materialmanagement.model.InStorageInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.sql.Date;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 入库表 Mapper 接口
 * </p>
 *
 * @author 
 * @since 2020-07-01
 */
public interface InStorageInfoMapper extends BaseMapper<InStorageInfo> {

    List<InStorageInfo> selectTodayInStore(@Param(value = "insertTime") String insertTime);

    List<InStorageInfo> selectInStoreInfoByParams(@Param(value = "paramMap") Map<String,Object> paramMap);
}
