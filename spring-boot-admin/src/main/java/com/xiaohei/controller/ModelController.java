package com.xiaohei.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.xiaohei.constant.GlobalConstant;
import com.xiaohei.entity.LoginForm;
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
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.springframework.http.MediaType;
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
@Api(tags = "接口模板" , value = "VALUE" , produces = MediaType.APPLICATION_JSON_VALUE)
public class ModelController {
    @Resource
    private ApiServiceDemo apiService ;
    @Resource
    private UserTokenService tokenService ;
    @Resource
    private UserService userService ;
    @GetMapping("/test")
    @RequiresPermissions(value = {"test:admin:user"})
    @ApiOperation(value = "test()",notes = "test-notes")
    public String test(){
        return "Hello World!" ;
    }
    @GetMapping("/user/info")
    @ApiOperation(value = "getUserInfo(@RequestParam String id)",notes = "test-notes")
    public R getUserInfo(@RequestParam String id){
        SelectUserConditionEntity condition = new SelectUserConditionEntity(new HashMap<>());
        condition.putEqCondition("ID",id);
        return apiService.selectBy(condition);
    }

    @PostMapping("/user/list")
    @ApiOperation(value = "getUserInfo(@RequestBody SelectUserConditionEntity condition)",notes = "test-notes")
    public R getUserInfo(@RequestBody SelectUserConditionEntity condition){
        return apiService.selectBy(condition);
    }

    @PostMapping("/user/add")
    @ApiOperation(value = "saveUser(@RequestBody UserModelEntity user)",notes = "test-notes")
    public R saveUser(@RequestBody UserModelEntity user){
        return apiService.insertBy(user);
    }
    @PostMapping("/user/update")
    @ApiOperation(value = "updateUser(@RequestBody UserModelEntity user)",notes = "test-notes")
    public R updateUser(@RequestBody UserModelEntity user){
        return apiService.updateBy(user);
    }
    @GetMapping("/user/del")
    @ApiOperation(value = "delUser(@RequestParam String id)",notes = "test-notes")
    public R delUser(@RequestParam String id){
        return apiService.deleteBy(id);
    }

    @PostMapping("/user/login")
    @ApiOperation(value = "adminLogin(LoginForm form)",notes = "test-notes")
    public R adminLogin(@RequestBody LoginForm form){
        Subject subject = SecurityUtils.getSubject();
        if (subject.isAuthenticated()){
            UserEntity user = (UserEntity) subject.getPrincipal();
            UserTokenEntity token = tokenService.selectById(user.getId());
            return R.ok().put("principal",user).put("credentials",token.getToken());
        }else {
            Wrapper<UserEntity> wap = new EntityWrapper<UserEntity>().eq("USER_NAME", form.getUsername());
            UserEntity user = userService.selectOne(wap);
            if (user == null) return R.error(GlobalConstant.USER_NOT_EXIST);
            UserTokenEntity token = new UserTokenEntity(true);
            token.setId(user.getId());
            boolean b = tokenService.insertOrUpdate(token);
            if (b){
                subject.login(new AcceptTicket(token.getToken()));
                return R.ok().put("principal",user).put("credentials",token.getToken());
            }else {
                return R.error(GlobalConstant.TOKEN_ISSUE_FAILD);
            }
        }

    }
}
