package com.jckj.materialmanagement.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.google.common.base.Strings;
import com.google.common.collect.Maps;
import com.jckj.materialmanagement.config.error.ErrorCode;
import com.jckj.materialmanagement.config.response.GlobalResponse;
import com.jckj.materialmanagement.enumd.DictType;
import com.jckj.materialmanagement.exception.BusinessException;
import com.jckj.materialmanagement.mapper.DictMapper;
import com.jckj.materialmanagement.mapper.MaterialInfoMapper;
import com.jckj.materialmanagement.model.Dict;
import com.jckj.materialmanagement.model.MaterialInfo;
import com.jckj.materialmanagement.service.IMaterialInfoService;
import com.jckj.materialmanagement.utils.ComUtil;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.prefs.BackingStoreException;
import java.util.stream.Collectors;

/**
 * <p>
 * 物资表 服务实现类
 * </p>
 *
 * @author
 * @since 2020-07-04
 */
@Service
public class MaterialInfoServiceImpl extends ServiceImpl<MaterialInfoMapper, MaterialInfo> implements IMaterialInfoService {

    @Resource
    private MaterialInfoMapper materialInfoMapper;
    @Resource
    private DictMapper dictMapper;


    @Override
    public List<MaterialInfo> queryMaterialInfoList(Long companyId) {
        return materialInfoMapper.queryMaterialInfoListByCompanyId(companyId);
    }

    /**
     * 保存/编辑物资
     *
     * @param info
     * @return
     */
    @Override
    public GlobalResponse saveMaterialInfo(MaterialInfo info) {

        //查询商户所有物资
        MaterialInfo db = null;
        List<MaterialInfo> materialInfos = materialInfoMapper.queryMaterialInfoListByCompanyId(info.getCompanyId());
        if (!CollectionUtils.isEmpty(materialInfos)) {
            for (MaterialInfo temp : materialInfos) {
                if (!Strings.isNullOrEmpty(info.getMaterialSerialNo()) && temp.getMaterialSerialNo().equals(info.getMaterialSerialNo())) {
                    db = temp;
                    continue;
                }
                if (info.getMaterialName().equals(temp.getMaterialName())) {
                    throw new BusinessException(ErrorCode.DATA_NAME_EXIST, new Object[]{temp.getMaterialName()});
                }
            }
        }

        //查询字典信息
        Map<String, Map<Long, Dict>> dicMap = Maps.newHashMap();
        List<String> allTypes = DictType.getAllTypes();
        List<Dict> dicts = dictMapper.selectDictListByType(allTypes, 0l);
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

        //校验字典数据是否存在
        checkDictParam(DictType.BRAND.text, info.getBrand(), dicMap.get(DictType.BRAND.type));
        checkDictParam(DictType.UNIT.text, info.getUnit(), dicMap.get(DictType.UNIT.type));
        checkDictParam(DictType.CATEGORY.text, info.getCategory(), dicMap.get(DictType.CATEGORY.type));
        checkDictParam(DictType.LOCATION.text, info.getLocation(), dicMap.get(DictType.LOCATION.type));

        if (!ComUtil.isNum(String.valueOf(info.getUnitNum()))) {
            throw new BusinessException(ErrorCode.NUM_OF_DATA_ERROR, new Object[]{"单位"});
        }
        if (!ComUtil.isNum(String.valueOf(info.getPurchasePrice()))) {
            throw new BusinessException(ErrorCode.NUM_OF_DATA_ERROR, new Object[]{"采购价"});
        }
        if (!ComUtil.isNum(String.valueOf(info.getRetailPrice()))) {
            throw new BusinessException(ErrorCode.NUM_OF_DATA_ERROR, new Object[]{"零售价"});
        }

        if (!Strings.isNullOrEmpty(info.getMaterialSerialNo()) && ComUtil.isNull(db)) {
            throw new BusinessException(ErrorCode.DATA_NOT_FIND);
        }

        if (Strings.isNullOrEmpty(info.getMaterialSerialNo())) {
            //新增
            info.setMaterialSerialNo(ComUtil.getRandomUUID());
            int insert = materialInfoMapper.insert(info);
            if (insert < 0) {
                throw new BusinessException(ErrorCode.DATA_SAVE_FAIL);
            }
            return GlobalResponse.success();
        }

        int update = materialInfoMapper.updateById(info);
        if(update < 0){
            throw new BusinessException(ErrorCode.DATA_UPDATE_FAIL);
        }
        return GlobalResponse.success();
    }

    /**
     * 校验字典数据是否存在
     *
     * @param type
     * @param id
     * @param text
     */
    private void checkDictParam(String text, Long dicId, Map<Long, Dict> dicMap) {
        if (!ComUtil.isNull(dicId) && !CollectionUtils.isEmpty(dicMap) && ComUtil.isNull(dicMap.get(dicId))) {
            throw new BusinessException(ErrorCode.DICT_ID_NOT_FIND, new Object[]{text});
        }

    }

    /**
     * 删除物资
     *
     * @param info
     * @return
     */
    @Override
    public GlobalResponse delMaterialInfo(MaterialInfo info) {
        int del = materialInfoMapper.deleteById(info.getMaterialSerialNo());
        if(del < 0){
            throw new BusinessException(ErrorCode.DATA_DEL_FAIL);
        }
        return GlobalResponse.success();
    }
}
