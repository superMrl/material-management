package com.jckj.materialmanagement.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jckj.materialmanagement.config.response.GlobalResponse;
import com.jckj.materialmanagement.model.MaterialInfo;

import java.util.List;

/**
 * <p>
 * 物资表 服务类
 * </p>
 *
 * @author 
 * @since 2020-07-04
 */
public interface IMaterialInfoService extends IService<MaterialInfo> {

    List<MaterialInfo> queryMaterialInfoList(Long companyId);

    GlobalResponse saveMaterialInfo(MaterialInfo info);

    GlobalResponse delMaterialInfo(MaterialInfo info);
}
