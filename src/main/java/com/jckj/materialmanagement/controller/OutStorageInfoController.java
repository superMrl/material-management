package com.jckj.materialmanagement.controller;


import com.jckj.materialmanagement.config.response.GlobalResponse;
import com.jckj.materialmanagement.model.OutStorageInfo;
import com.jckj.materialmanagement.service.IOutStorageInfoService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 出库表 前端控制器
 * </p>
 *
 * @author 
 * @since 2020-07-06
 */
@RestController
@RequestMapping("/outStorageInfo")
public class OutStorageInfoController {

    @Resource
    private IOutStorageInfoService outStorageInfoService;

    @ApiOperation(value = "批量保存出库详情", httpMethod = "POST")
    @RequestMapping("/saveBatch")
    public GlobalResponse saveBatch(@RequestBody String param) {
        return outStorageInfoService.saveBatch(param);
    }

    @ApiOperation(value = "获取今日出库详情", httpMethod = "POST")
    @RequestMapping("/selectTodayOutStore")
    public List<OutStorageInfo> selectTodayOutStore() {
        return outStorageInfoService.selectTodayInStore();
    }

    @ApiOperation(value = "根据出库单主键获取出库详情", httpMethod = "POST")
    @RequestMapping("/selectOutStorageById")
    public OutStorageInfo selectOutStorageById(@RequestBody String id) {
        return outStorageInfoService.selectOutStorageById(id);
    }


}
