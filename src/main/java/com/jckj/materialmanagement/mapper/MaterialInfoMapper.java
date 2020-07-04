package com.jckj.materialmanagement.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jckj.materialmanagement.model.MaterialInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.security.access.method.P;

import java.util.List;

/**
 * <p>
 * 物资表 Mapper 接口
 * </p>
 *
 * @author 
 * @since 2020-07-04
 */
public interface MaterialInfoMapper extends BaseMapper<MaterialInfo> {

    List<MaterialInfo> queryMaterialInfoListByCompanyId(@Param(value = "companyId") Long companyId);
}
