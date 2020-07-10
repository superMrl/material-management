package com.jckj.materialmanagement.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jckj.materialmanagement.config.response.GlobalResponse;
import com.jckj.materialmanagement.model.OutStorageInfo;

import java.util.List;

/**
 * <p>
 * 出库表 服务类
 * </p>
 *
 * @author 
 * @since 2020-07-06
 */
public interface IOutStorageInfoService extends IService<OutStorageInfo> {

    GlobalResponse saveBatch(String param);

    List<OutStorageInfo> selectTodayInStore();

    OutStorageInfo selectOutStorageById(String id);
}
