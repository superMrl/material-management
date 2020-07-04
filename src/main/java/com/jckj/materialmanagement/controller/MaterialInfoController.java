package com.jckj.materialmanagement.controller;


import com.jckj.materialmanagement.config.response.GlobalResponse;
import com.jckj.materialmanagement.model.Dict;
import com.jckj.materialmanagement.model.MaterialInfo;
import com.jckj.materialmanagement.service.IDictService;
import com.jckj.materialmanagement.service.IMaterialInfoService;
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
 * 物资表 前端控制器
 * </p>
 *
 * @author 
 * @since 2020-07-04
 */
@Slf4j
@RestController
@Api(tags = "物资接口")
@RequestMapping("/product")
public class MaterialInfoController {


    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private IMaterialInfoService iMaterialInfoService;


    @ApiOperation(value = "获取所有的物资", httpMethod = "POST")
    @RequestMapping("/list")
    public List<MaterialInfo> queryMaterialInfoList(@RequestBody MaterialInfo info) {
        return iMaterialInfoService.queryMaterialInfoList(info.getCompanyId());
    }

    @ApiOperation(value = "保存物资信息", httpMethod = "POST")
    @RequestMapping("/save")
    public GlobalResponse saveMaterialInfo(@RequestBody MaterialInfo info) {
        return iMaterialInfoService.saveMaterialInfo(info);
    }

    @ApiOperation(value = "保存物资信息", httpMethod = "POST")
    @RequestMapping("/detail")
    public MaterialInfo queryMaterialInfo(@RequestBody MaterialInfo info) {
        return iMaterialInfoService.queryMaterialInfo(info);
    }


    @ApiOperation(value = "删除物资信息", httpMethod = "POST")
    @RequestMapping("/del")
    public GlobalResponse delMaterialInfo(@RequestBody MaterialInfo info) {
        return iMaterialInfoService.delMaterialInfo(info);
    }
}
