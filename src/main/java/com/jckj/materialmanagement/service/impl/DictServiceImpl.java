package com.jckj.materialmanagement.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.jckj.materialmanagement.config.error.ErrorCode;
import com.jckj.materialmanagement.config.response.GlobalResponse;
import com.jckj.materialmanagement.enumd.DictType;
import com.jckj.materialmanagement.exception.BusinessException;
import com.jckj.materialmanagement.mapper.DictMapper;
import com.jckj.materialmanagement.model.Dict;
import com.jckj.materialmanagement.service.IDictService;
import com.jckj.materialmanagement.utils.ComUtil;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

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
        return dictMapper.selectDictListByType(Lists.newArrayList(type), 0l);
    }

    @Override
    public GlobalResponse saveDict(Dict dict) {
        if (ComUtil.isNull(DictType.getByType(dict.getType()))) {
            throw new BusinessException(ErrorCode.DICT_TYPE_ERROR);
        }
        if (Strings.isNullOrEmpty(dict.getName())) {
            throw new BusinessException(ErrorCode.DICT_NAME_EMPTY);
        }

        Dict db = null;
        List<Dict> dicts = dictMapper.selectDictListByType(Lists.newArrayList(dict.getType()), 0l);
        if (!CollectionUtils.isEmpty(dicts)) {
            for (Dict temp : dicts) {
                if (!ComUtil.isNull(temp.getId()) && temp.getId().equals(dict.getId())) {
                    db = temp;
                    continue;
                }
                if (dict.getName().equals(temp.getName())) {
                    throw new BusinessException(ErrorCode.DATA_NAME_EXIST, new Object[]{temp.getName()});
                }
            }
        }
        if (!ComUtil.isNull(dict.getId()) && ComUtil.isNull(db)) {
            throw new BusinessException(ErrorCode.DATA_NOT_FIND);
        }

        if (ComUtil.isNull(dict.getId())) {
            //保存
            int insert = dictMapper.insert(dict);
            if (insert < 0) {
                throw new BusinessException(ErrorCode.DATA_SAVE_FAIL);
            }
            return GlobalResponse.success();
        }

        int update = dictMapper.updateById(dict);
        if (update < 0) {
            throw new BusinessException(ErrorCode.DATA_UPDATE_FAIL);

        }
        return GlobalResponse.success();
    }

    @Override
    public GlobalResponse delDict(Dict dict) {
        Dict db = dictMapper.selectById(dict.getId());
        if (ComUtil.isNull(db)) {
            throw new BusinessException(ErrorCode.DATA_NOT_FIND);
        }
        int del = dictMapper.deleteById(dict.getId());
        if (del < 0) {
            throw new BusinessException(ErrorCode.DATA_DEL_FAIL);
        }
        return GlobalResponse.success();
    }

    @Override
    public Map<String, Map<Long, Dict>> queryDict4Material(Long companyId) {
        List<String> allTypes = DictType.getAllTypes();
        List<Dict> dicts = dictMapper.selectDictListByType(allTypes, companyId);
        if (CollectionUtils.isEmpty(dicts)) {
            return null;
        }
        Map<String, Map<Long, Dict>> dicMap = Maps.newHashMap();
        if (!CollectionUtils.isEmpty(dicts)) {
            for (Dict dict : dicts) {
                Map<Long, Dict> map = dicMap.get(dict.getType());
                if (CollectionUtils.isEmpty(map)) {
                    map = Maps.newHashMap();
                    dicMap.put(dict.getType(), map);
                }
                if (ComUtil.isNull(map.get(dict.getId()))) {
                    map.put(dict.getId(), dict);
                }
            }
        }
        return dicMap;
    }
}
