package com.jckj.materialmanagement.controller;


import com.alibaba.fastjson.JSONObject;
import com.google.common.base.Objects;
import com.jckj.materialmanagement.model.MUser;
import com.jckj.materialmanagement.service.impl.MUserServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 
 * @since 2020-06-20
 */
@RestController
@Api(tags = "用户接口")
@RequestMapping("/user")
public class MUserController {

    @Autowired
    private MUserServiceImpl mUserService;

    @ApiOperation(value = "根据id获取用户",httpMethod = "POST")
    @RequestMapping("/get")
    public String getUser(@RequestBody MUser user) {
        MUser db = mUserService.getUser(user.getId());
        if(Objects.equal(db,null)){
            return null;
        }
        return JSONObject.toJSONString(db);
    }
    

}
