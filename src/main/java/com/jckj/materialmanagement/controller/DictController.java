package com.jckj.materialmanagement.controller;


import com.jckj.materialmanagement.config.response.GlobalResponse;
import com.jckj.materialmanagement.model.Dict;
import com.jckj.materialmanagement.model.User;
import com.jckj.materialmanagement.service.IDictService;
import com.jckj.materialmanagement.service.UserService;
import com.jckj.materialmanagement.utils.RedisUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author
 * @since 2020-06-20
 */
@Slf4j
@RestController
@Api(tags = "字典接口")
@RequestMapping("/dict")
public class DictController {

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private IDictService iDictService;


    @ApiOperation(value = "获取字典所有类型", httpMethod = "POST")
    @RequestMapping("/type/list")
    public List<Dict> queryDictTypeList() {
        return iDictService.queryDictTypeList();
    }

    @ApiOperation(value = "获取指定type字典列表", httpMethod = "POST")
    @RequestMapping("/list")
    public List<Dict> queryDictList(@RequestBody Dict dict) {
        return iDictService.queryDictListByType(dict.getType());
    }

    @ApiOperation(value = "保存字典数据", httpMethod = "POST")
    @RequestMapping("/save")
    public GlobalResponse saveDict(@RequestBody Dict dict) {
        return iDictService.saveDict(dict);
    }


}
