package com.jckj.materialmanagement.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.jckj.materialmanagement.config.error.ErrorCode;
import com.jckj.materialmanagement.config.response.GlobalResponse;
import com.jckj.materialmanagement.exception.BusinessException;
import com.jckj.materialmanagement.model.InStorageInfo;
import com.jckj.materialmanagement.mapper.InStorageInfoMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jckj.materialmanagement.service.InStorageInfoService;
import com.jckj.materialmanagement.utils.DateUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 入库表 服务实现类
 * </p>
 *
 * @author 
 * @since 2020-07-01
 */
@Service
public class InStorageInfoServiceImpl extends ServiceImpl<InStorageInfoMapper, InStorageInfo> implements InStorageInfoService {

    @Resource
    private InStorageInfoMapper inStorageInfoMapper;

    @Override
    public GlobalResponse saveBatch(String param) {
        List<InStorageInfo> inStorageInfoList = JSONArray.parseArray(param, InStorageInfo.class);
        for (InStorageInfo inStorageInfo : inStorageInfoList) {
            String timeId = IdWorker.getMillisecond();
            inStorageInfo.setInPageNo(timeId);
//            inStorageInfo.setInsertTime(LocalDateTime.now());
        }
        boolean flag = this.saveBatch(inStorageInfoList);
        if(!flag){
            throw new BusinessException(ErrorCode.INSTORE_SAVE_FAIL);
        }
        return  GlobalResponse.success(ErrorCode.INSTORE_SAVE_SUCCESS);
    }

    @Override
    public List<InStorageInfo> selectTodayInStore() {

        String insertTime = DateUtils.formatDateToDB(DateUtils.parseMinTimeForDate(DateUtils.getCurrentDay()).toString(), DateUtils.TIME_REQ_PATTERN, DateUtils.TIME_DB_PATTERN);
        return inStorageInfoMapper.selectTodayInStore(insertTime);
    }
}
