package com.xiaohei.controller;

import com.xiaohei.entity.param.SelectUserConditionEntity;
import com.xiaohei.entity.param.UserModelEntity;
import com.xiaohei.entity.table.UserEntity;
import com.xiaohei.service.api.ApiServiceDemo;
import com.xiaohei.utils.R;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;

/**
 * @author : WiuLuS
 * @version : v1.11.20.2019
 * @discription :
 * @date : 2019-11-20 10:00:27
 * @email : m13886933623@163.com
 */
@RestController
@RequestMapping("/mode")
public class ModelController {

    @GetMapping("/test")
    @RequiresPermissions(value = {"test:admin:user"})
    public String test(){
        return "Hello World!" ;
    }

    @Resource
    private ApiServiceDemo apiService ;
    @GetMapping("/user/info")
    public R getUserInfo(@RequestParam String id){
        SelectUserConditionEntity condition = new SelectUserConditionEntity(new HashMap<>());
        condition.putEqCondition("ID",id);
        return apiService.selectBy(condition);
    }

    @PostMapping("/user/list")
    public R getUserInfo(@RequestBody SelectUserConditionEntity condition){
        return apiService.selectBy(condition);
    }

    @PostMapping("/user/add")
    public R saveUser(@RequestBody UserModelEntity user){
        return apiService.insertBy(user);
    }
    @PostMapping("/user/update")
    public R updateUser(@RequestBody UserModelEntity user){
        return apiService.updateBy(user);
    }
    @GetMapping("/user/del")
    public R delUser(@RequestParam String id){
        return apiService.deleteBy(id);
    }



}
