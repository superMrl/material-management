package com.jckj.materialmanagement.controller;


import com.jckj.materialmanagement.config.response.GlobalResponse;
import com.jckj.materialmanagement.model.InStorageInfo;
import com.jckj.materialmanagement.service.InStorageInfoService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 入库表 前端控制器
 * </p>
 *
 * @author 
 * @since 2020-07-01
 */
@Slf4j
@RestController
@RequestMapping("/inStorageInfo")
public class InStorageInfoController {

    @Resource
    private InStorageInfoService inStorageInfoService;

    @ApiOperation(value = "批量保存入库详情", httpMethod = "POST")
    @RequestMapping("/saveBatch")
    public GlobalResponse saveBatch(@RequestBody String param) {
        return inStorageInfoService.saveBatch(param);
    }

    @ApiOperation(value = "获取入库详情", httpMethod = "POST")
    @RequestMapping("/selectTodayInStore")
    public List<InStorageInfo> selectTodayInStore() {
        return inStorageInfoService.selectTodayInStore();
    }

}
