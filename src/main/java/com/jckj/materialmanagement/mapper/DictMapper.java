package com.jckj.materialmanagement.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jckj.materialmanagement.model.Dict;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 字典表 Mapper 接口
 * </p>
 *
 * @author 
 * @since 2020-07-03
 */
public interface DictMapper extends BaseMapper<Dict> {

    List<Dict> selectDictListByType(@Param(value = "type") String type);
}
