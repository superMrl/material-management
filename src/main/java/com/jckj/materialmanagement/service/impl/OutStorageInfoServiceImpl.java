package com.jckj.materialmanagement.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jckj.materialmanagement.config.error.ErrorCode;
import com.jckj.materialmanagement.config.response.GlobalResponse;
import com.jckj.materialmanagement.exception.BusinessException;
import com.jckj.materialmanagement.mapper.OutStorageInfoMapper;
import com.jckj.materialmanagement.model.OutStorageInfo;
import com.jckj.materialmanagement.service.IOutStorageInfoService;
import com.jckj.materialmanagement.utils.DateUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 出库表 服务实现类
 * </p>
 *
 * @author 
 * @since 2020-07-06
 */
@Service
public class OutStorageInfoServiceImpl extends ServiceImpl<OutStorageInfoMapper, OutStorageInfo> implements IOutStorageInfoService {

    @Resource
    private OutStorageInfoMapper outStorageInfoMapper;

    @Override
    public GlobalResponse saveBatch(String param) {
        List<OutStorageInfo> outStorageInfoList = JSONArray.parseArray(param, OutStorageInfo.class);
        for (OutStorageInfo outStorageInfo : outStorageInfoList) {
            String timeId = IdWorker.getMillisecond();
            outStorageInfo.setOutPageNo(timeId);
//            inStorageInfo.setInsertTime(LocalDateTime.now());
        }
        boolean flag = this.saveBatch(outStorageInfoList);
        if(!flag){
            throw new BusinessException(ErrorCode.INSTORE_SAVE_FAIL);
        }
        return  GlobalResponse.success(ErrorCode.INSTORE_SAVE_SUCCESS);
    }

    @Override
    public List<OutStorageInfo> selectTodayInStore() {

        String outTime = DateUtils.formatDateToDB(DateUtils.parseMinTimeForDate(DateUtils.getCurrentDay()).toString(), DateUtils.TIME_REQ_PATTERN, DateUtils.TIME_DB_PATTERN);
        return outStorageInfoMapper.selectTodayOutStore(outTime);
    }

    @Override
    public OutStorageInfo selectOutStorageById(String id) {
        return this.getById(Long.parseLong(id));
    }

}
