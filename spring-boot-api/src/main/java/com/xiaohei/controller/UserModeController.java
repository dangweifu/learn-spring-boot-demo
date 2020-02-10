package com.xiaohei.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.xiaohei.constant.GlobalConstant;
import com.xiaohei.entity.param.SelectUserConditionEntity;
import com.xiaohei.entity.param.UserModelEntity;
import com.xiaohei.entity.table.UserEntity;
import com.xiaohei.entity.table.UserTokenEntity;
import com.xiaohei.matcher.AcceptTicket;
import com.xiaohei.service.admin.UserService;
import com.xiaohei.service.admin.UserTokenService;
import com.xiaohei.service.api.ApiServiceDemo;
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
    private UserTokenService tokenService ;
    @Resource
    private UserService userService ;
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

    @PostMapping("/login")
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
    @GetMapping("/logout")
    @ApiOperation(value = "用户退出",notes = "用户退出")
    public R logout(){
        Subject subject = SecurityUtils.getSubject();
        if (subject.isAuthenticated()){
            subject.logout();
            // TODO : 如果用户凭证、权限列表等用户信息进行了缓存，则在这里还需要清理缓存信息，保证用户登录状态相关的信息一致性。
        }
//        subject.logout();
        return R.ok();
    }
}
