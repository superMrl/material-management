package com.jckj.materialmanagement.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.common.base.Strings;
import com.google.common.collect.Collections2;
import com.google.common.collect.Lists;
import com.jckj.materialmanagement.config.error.ErrorCode;
import com.jckj.materialmanagement.config.response.GlobalResponse;
import com.jckj.materialmanagement.enumd.DictType;
import com.jckj.materialmanagement.exception.BusinessException;
import com.jckj.materialmanagement.mapper.DictMapper;
import com.jckj.materialmanagement.model.Dict;
import com.jckj.materialmanagement.service.IDictService;
import com.jckj.materialmanagement.utils.ComUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 字典表 服务实现类
 * </p>
 *
 * @author 
 * @since 2020-07-03
 */
@Service
public class DictServiceImpl extends ServiceImpl<DictMapper, Dict> implements IDictService {

    @Resource
    private DictMapper dictMapper;

    @Override
    public List<Dict> queryDictTypeList() {
        List<Dict> dicts = Lists.newArrayList();
        for (DictType temp : DictType.values()) {
            Dict d = new Dict();
            d.setType(temp.type);
            d.setName(temp.text);
            dicts.add(d);
        }
        return dicts;
    }

    @Override
    public List<Dict> queryDictListByType(String type) {
        return dictMapper.selectDictListByType(type);
    }

    @Override
    public GlobalResponse saveDict(Dict dict) {
        if(ComUtil.isNull(DictType.getByType(dict.getType()))){
            throw new BusinessException(ErrorCode.DICT_TYPE_ERROR);
        }
        if(Strings.isNullOrEmpty(dict.getName())){
            throw new BusinessException(ErrorCode.DICT_NAME_EMPTY);
        }

        Dict db = null;
        List<Dict> dicts = dictMapper.selectDictListByType(dict.getType());
        if(!CollectionUtils.isEmpty(dicts)){
            for (Dict temp : dicts) {
                if(!ComUtil.isNull(temp.getId()) && temp.getId().equals(dict.getId())){
                    db = temp;
                    continue;
                }
                if(dict.getName().equals(temp.getName())){
                    throw new BusinessException(ErrorCode.DICT_NAME_EXIST);
                }
            }
        }
        if(!ComUtil.isNull(dict.getId()) && ComUtil.isNull(db)){
            throw new BusinessException(ErrorCode.DICT_NOT_FIND);
        }

        if(ComUtil.isNull(dict.getId())){
            //保存
            int insert = dictMapper.insert(dict);
            if(insert < 0){
                throw new BusinessException(ErrorCode.DATA_SAVE_FAIL);
            }
            return GlobalResponse.success();
        }

        int update = dictMapper.updateById(dict);
        if(update < 0){
            throw new BusinessException(ErrorCode.DATA_UPDATE_FAIL);

        }
        return GlobalResponse.success();
    }

    @Override
    public GlobalResponse delDict(Dict dict) {
        Dict db = dictMapper.selectById(dict.getId());
        if(ComUtil.isNull(db)){
            throw new BusinessException(ErrorCode.DATA_NOT_FIND);
        }
        int del = dictMapper.deleteById(dict.getId());
        if(del < 0){
            throw new BusinessException(ErrorCode.DATA_DEL_FAIL);
        }
        return GlobalResponse.success();
    }
}
