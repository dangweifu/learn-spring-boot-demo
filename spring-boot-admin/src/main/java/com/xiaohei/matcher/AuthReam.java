package com.xiaohei.matcher;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.xiaohei.entity.table.UserEntity;
import com.xiaohei.entity.table.UserTokenEntity;
import com.xiaohei.service.admin.UserService;
import com.xiaohei.service.admin.UserTokenService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.Set;

/**
 * @author : WiuLuS
 * @version : v1.11.28.2019
 * @discription :
 * @date : 2019-11-28 10:38:27
 * @email : m13886933623@163.com
 */
@Configuration
public class AuthReam  extends AuthorizingRealm {
    @Resource
    private UserTokenService tokenService ;
    @Resource
    private UserService userService ;
    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof AcceptTicket;
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        /*
         * 根据用户主体(用户实体)获取用户权限列表(可以是基于字符串形式返回也可以是基于对象形式返回)
         */
        String permission = "all";
        Set<String> permissions = new HashSet<>();
        permissions.add(permission);
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.setStringPermissions(permissions);
        return info;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        /*
         * 1、从AuthenticationToken中获取token(令牌)
         * 2、根据token从缓存中获取用户登录信息，缓存中拿不到则去数据库中拿 提问：为什么要缓存用户登录信息
         * 3、检查用户登录信息是否有效
         * 4、如果用户登录信息有效，则返回包含用户登录信息的对象，否则抛出账号验证失败异常
         */
        Object s = authenticationToken.getPrincipal();
        UserTokenEntity token = tokenService.selectOne(new EntityWrapper<UserTokenEntity>().eq("TOKEN", s.toString()));
        //token失效
        if(token == null || token.getExpireTime().getTime() < System.currentTimeMillis()){
            throw new IncorrectCredentialsException("token失效，请重新登录");
        }
        UserEntity user = userService.selectOne(new EntityWrapper<UserEntity>().eq("ID", token.getId()));
        return new SimpleAuthenticationInfo(user, s, getName());
    }
}
