package com.xiaohei.matcher;

import com.xiaohei.entity.table.UserEntity;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.context.annotation.Configuration;

/**
 * @author : WiuLuS
 * @version : v1.11.28.2019
 * @discription :
 * @date : 2019-11-28 10:38:27
 * @email : m13886933623@163.com
 */
@Configuration
public class AuthReam  extends AuthorizingRealm {

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof AcceptTicket;
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        /*
         * 根据凭证获取用户权限列表(可以是基于字符串形式返回也可以是基于对象形式返回)
         */
        return new SimpleAuthorizationInfo();
//        throw new UnauthorizedException("Subject does not have permission [test:admin:user]");
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        /*
         * 1、从AuthenticationToken中获取token(令牌)
         * 2、根据token从缓存中获取用户登录信息，缓存中拿不到则去数据库中拿 提问：为什么要缓存用户登录信息
         * 3、检查用户登录信息是否有效
         * 4、如果用户登录信息有效，则返回包含用户登录信息的对象，否则抛出账号验证失败异常
         */
        return new SimpleAuthenticationInfo(new UserEntity(), token.getPrincipal(), getName());
    }
}
