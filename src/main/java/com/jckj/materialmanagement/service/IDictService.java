package com.jckj.materialmanagement.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.jckj.materialmanagement.config.response.GlobalResponse;
import com.jckj.materialmanagement.model.Dict;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 字典表 服务类
 * </p>
 *
 * @author 
 * @since 2020-07-03
 */
public interface IDictService extends IService<Dict> {

    List<Dict> queryDictTypeList();

    List<Dict> queryDictListByType(String type);

    GlobalResponse saveDict(Dict dict);

    GlobalResponse delDict(Dict dict);

    Map<String, Map<Long, Dict>> queryDict4Material();
}
