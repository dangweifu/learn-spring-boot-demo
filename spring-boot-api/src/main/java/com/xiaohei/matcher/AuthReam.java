package com.xiaohei.matcher;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.xiaohei.constant.GlobalConstant;
import com.xiaohei.entity.table.UserEntity;
import com.xiaohei.entity.table.UserTokenEntity;
import com.xiaohei.service.admin.UserPermissionService;
import com.xiaohei.service.admin.UserService;
import com.xiaohei.service.admin.UserTokenService;
import com.xiaohei.utils.R;
import org.apache.shiro.authc.*;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

import javax.annotation.Resource;
import java.util.Date;
import java.util.Set;

/**
 * @author : WiuLuS
 * @version : v1.11.28.2019
 * @discription :
 * @date : 2019-11-28 10:38:27
 * @email : m13886933623@163.com
 */
public class AuthReam  extends AuthorizingRealm {
    @Resource
    private UserTokenService tokenService ;
    @Resource
    private UserService userService ;
    @Resource
    private UserPermissionService permissionService ;

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof AcceptTicket;
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        /*
         * 从主体中获取用户实体
         * 根据用户 ID 获取用户权限列表(可以是基于字符串形式返回也可以是基于对象形式返回)
         * TODO ：这里可以加缓存，将用户权限列表进行缓存，缓存 key 要以用户 ID 进行区分。
         *  最好有缓存 key 统一生成的地方进行统一管理。
         */
        UserEntity user = (UserEntity) principals.getPrimaryPrincipal();
        R r = permissionService.getUserPermissionSetBy(user.getId());
        Set<String> permission = (Set<String>) r.get("data");
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.setStringPermissions(permission);
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
        }else {
            Date now = new Date();
            token.setExpireTime(new Date(now.getTime() + GlobalConstant.DEFAULT_USER_TOKEN_EXPIRE_TIME  * 1000));
            token.setUpdateTime(now);
            tokenService.updateById(token);
        }
        UserEntity user = userService.selectOne(new EntityWrapper<UserEntity>().eq("ID", token.getId()));
        ByteSource salt = ByteSource.Util.bytes(user.getSalt());
        System.out.println(salt);
        // 这个对象的参数详解可以参考 https://blog.csdn.net/weixin_42195162/article/details/89376076
        // 我这里说明下我的参数 user对象 、user.password 、user.salt 、当前Realm源对象名称
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user, user.getPassword() , salt , getName() );
        CredentialsMatcher matcher = getCredentialsMatcher();
        // 进行密码校验
        if (matcher.doCredentialsMatch(authenticationToken,info)){
            System.out.println("认证成功！");
        }else {
            System.out.println("认证失败！");

        }
        return info ;
    }
}
