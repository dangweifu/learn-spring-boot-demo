package com.xiaohei.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.xiaohei.constant.GlobalConstant;
import com.xiaohei.entity.param.BusinessModeDto;
import com.xiaohei.entity.table.UserEntity;
import com.xiaohei.entity.table.UserTokenEntity;
import com.xiaohei.matcher.AcceptTicket;
import com.xiaohei.service.admin.LocalBusinessModeService;
import com.xiaohei.service.admin.UserService;
import com.xiaohei.service.admin.UserTokenService;
import com.xiaohei.utils.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author : WiuLuS
 * @version : v1.01.13.2020
 * @discription : 业务接口控制器模板 ， 方法内部仅做三件事： 收参 、 调用service 、 返回结果（可能是视图，某个页面）
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
    @RequiresPermissions(value = {"admin:user:view"})
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

    @PostMapping("/user/login")
    @ApiOperation(value = "adminLogin(LoginForm form)",notes = "test-notes")
    public R login(@RequestBody AcceptTicket form){
        Subject subject = SecurityUtils.getSubject();
        if (subject.isAuthenticated()){
            UserEntity user = (UserEntity) subject.getPrincipal();
            UserTokenEntity token = tokenService.selectById(user.getId());
            return R.ok().put("principal",user).put("credentials",token.getToken());
        }else {
            // TODO :
            //  1、根据用户名查询用户
            //  2、对用户输入密码进行加密处理以便于数据库存储密码进行比对  暂未进行加密处理
            //  3、根据用户签发登录token
            //  4、执行登录操作
            Wrapper<UserEntity> wap = new EntityWrapper<UserEntity>().eq("USER_NAME", form.getUsername());
            UserEntity user = userService.selectOne(wap);
            if (user == null) return R.error(GlobalConstant.USER_NOT_EXIST);
            UserTokenEntity token = new UserTokenEntity(true);
            token.setId(user.getId());
            boolean b = tokenService.insertOrUpdate(token);
            if (b){
                form.setToken(token.getToken());
                subject.login(form);
                return R.ok().put("principal",user).put("credentials",token.getToken());
            }else {
                return R.error(GlobalConstant.TOKEN_ISSUE_FAILD);
            }
        }
    }
    @GetMapping("/user/logout")
    @ApiOperation(value = "用户退出",notes = "用户退出")
    public R logout(){
        Subject subject = SecurityUtils.getSubject();
        if (subject.isAuthenticated()){
            subject.logout();
            // TODO : 如果用户凭证、权限列表等用户信息进行了缓存，则在这里还需要清理缓存信息，保证用户登录状态相关的信息一致性。
        }
        return R.ok();
    }

}
