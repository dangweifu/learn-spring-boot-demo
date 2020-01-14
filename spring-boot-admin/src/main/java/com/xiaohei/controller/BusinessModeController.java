package com.xiaohei.controller;

import com.xiaohei.entity.param.BusinessModeDto;
import com.xiaohei.entity.table.LocalBusinessModeEntity;
import com.xiaohei.service.admin.LocalBusinessModeService;
import com.xiaohei.service.admin.UserService;
import com.xiaohei.service.admin.UserTokenService;
import com.xiaohei.utils.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author : WiuLuS
 * @version : v1.01.13.2020
 * @discription :
 * @date : 2020-01-13 15:39:16
 * @email : m13886933623@163.com
 */
@RestController
@RequestMapping("/business")
@Api(tags = "业务接口模板" , value = "VALUE" , produces = MediaType.APPLICATION_JSON_VALUE)
public class BusinessModeController {

    @Resource
    private UserTokenService tokenService ;
    @Resource
    private UserService userService ;
    @Resource
    private LocalBusinessModeService modeService ;

    /** 后台登录用户且拥有浏览权限
     *  shiro 权限控制：只有拥有后边权限标识的用户才能访问此接口 @RequiresPermissions(value = {"admin:user:view"})
     **/
    @GetMapping("/select/{businessId}")
    @ApiOperation(value = "根据业务ID查询业务详情(这里是接口标签(tab)描述)",notes = "这里是接口详情描述")
    public R queryBusinessBy (
            @ApiParam("业务ID") @PathVariable String businessId){
        // TODO : 这里进行入参检查（非空、正则表达式匹配等等方式检查参数是否合格，非法参数直接抛出异常提示用户）
        return modeService.queryBusinessModeBy(businessId);
    }
    /** 后台登录用户且拥有修改权限(增、删、改)
     *  shiro 权限控制：只有拥有后边权限标识的用户才能访问此接口 @RequiresPermissions(value = {"admin:user:update"})
     **/
    @PostMapping("/update")
    @ApiOperation(value = "根据业务ID进行业务详情的修改(这里是接口标签(tab)描述)",notes = "这里是接口详情描述")
    public R updateBusinessBy (@RequestBody BusinessModeDto dto){
        // TODO : 这里进行入参检查（非空、正则表达式匹配等等方式检查参数是否合格，非法参数直接抛出异常提示用户）
        return modeService.updateBusinessModeBy(dto);
    }
    /** 后台登录用户且拥有修改权限(增、删、改)
     * shiro 权限控制：只有拥有后边权限标识的用户才能访问此接口 @RequiresPermissions(value = {"admin:user:update"})
     **/
    @PostMapping("/add")
    @ApiOperation(value = "添加业务(这里是接口标签(tab)描述)",notes = "这里是接口详情描述")
    public R addBusinessBy (@RequestBody BusinessModeDto dto){
        // TODO : 这里进行入参检查（非空、正则表达式匹配等等方式检查参数是否合格，非法参数直接抛出异常提示用户）
        return modeService.addBusinessMode(dto);
    }
    /** 后台登录用户且拥有修改权限(增、删、改)
     * shiro 权限控制：只有拥有后边权限标识的用户才能访问此接口 @RequiresPermissions(value = {"admin:user:update"})
     **/
    @PostMapping("/del/{id}")
    @ApiOperation(value = "删除业务(这里是接口标签(tab)描述)",notes = "这里是接口详情描述")
    public R addBusinessBy (@PathVariable String id){
        // TODO : 这里进行入参检查（非空、正则表达式匹配等等方式检查参数是否合格，非法参数直接抛出异常提示用户）
        return modeService.deleteById(id);
    }

}
