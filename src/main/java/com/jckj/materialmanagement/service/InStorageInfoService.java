package com.jckj.materialmanagement.service;

import com.jckj.materialmanagement.config.response.GlobalResponse;
import com.jckj.materialmanagement.model.InStorageInfo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 入库表 服务类
 * </p>
 *
 * @author 
 * @since 2020-07-01
 */
public interface InStorageInfoService extends IService<InStorageInfo> {

    GlobalResponse saveBatch(String param);

    List<InStorageInfo> selectTodayInStore();

    List<InStorageInfo> selectInStoreInfoByParams(Map<String, Object> paramMap);
}
