package com.xiaohei.controller;

import com.xiaohei.entity.param.SelectUserConditionEntity;
import com.xiaohei.entity.param.UserModelEntity;
import com.xiaohei.service.api.ApiServiceDemo;
import com.xiaohei.utils.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @author : WiuLuS
 * @version : v1.02.08.2020
 * @discription :
 * @date : 2020-02-08 22:22:14
 * @email : m13886933623@163.com
 */
@RestController
@RequestMapping("/user")
@Api(tags = "用户接口模板" , value = "VALUE" , produces = MediaType.APPLICATION_JSON_VALUE)
public class UserModeController {

    @Resource
    private ApiServiceDemo serviceDemo ;

    /** 前台用户添加
     *  shiro 权限控制：只有拥有后边权限标识的用户才能访问此接口 @RequiresPermissions(value = {"api:user:update"})
     **/
    @PostMapping("/add")
    @ApiOperation(value = "添加用户(这里是接口标签(tab)描述)",notes = "这里是接口详情描述")
    @RequiresPermissions(value = {"api:user:update"})
    public R addUser (@RequestBody UserModelEntity user){
        // TODO : 这里进行入参检查（非空、正则表达式匹配等等方式检查参数是否合格，非法参数直接抛出异常提示用户）
        return serviceDemo.insertBy(user);
    }

    /** 前台删除用户
     *  shiro 权限控制：只有拥有后边权限标识的用户才能访问此接口 @RequiresPermissions(value = {"api:user:update"})
     **/
    @GetMapping("/delete/{id}")
    @ApiOperation(value = "删除用户(这里是接口标签(tab)描述)",notes = "这里是接口详情描述")
    @RequiresPermissions(value = {"api:user:update"})
    public R deleteBy (@PathVariable String id){
        // TODO : 这里进行入参检查（非空、正则表达式匹配等等方式检查参数是否合格，非法参数直接抛出异常提示用户）
        return serviceDemo.deleteBy(id);
    }

    /** 前台用户修改
     *  shiro 权限控制：只有拥有后边权限标识的用户才能访问此接口 @RequiresPermissions(value = {"api:user:update"})
     **/
    @PostMapping("/modify")
    @ApiOperation(value = "修改用户(这里是接口标签(tab)描述)",notes = "这里是接口详情描述")
    @RequiresPermissions(value = {"api:user:update"})
    public R updateBy (@RequestBody UserModelEntity user){
        // TODO : 这里进行入参检查（非空、正则表达式匹配等等方式检查参数是否合格，非法参数直接抛出异常提示用户）
        return serviceDemo.updateBy(user);
    }

    /** 用户查询
     *  shiro 权限控制：只有拥有后边权限标识的用户才能访问此接口 @RequiresPermissions(value = {"api:user:update"})
     **/
    @PostMapping("/query")
    @ApiOperation(value = "用户查询(这里是接口标签(tab)描述)",notes = "这里是接口详情描述")
    @RequiresPermissions(value = {"api:user:update"})
    public R queryBusinessBy (@RequestBody Map<String,Object> condition){
        // TODO : 这里进行入参检查（非空、正则表达式匹配等等方式检查参数是否合格，非法参数直接抛出异常提示用户）
        SelectUserConditionEntity s = new SelectUserConditionEntity();
        if (!condition.isEmpty()){
            s.setEqContidion(condition);
        }
        return serviceDemo.selectBy(s);
    }
}
