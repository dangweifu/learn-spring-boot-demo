package com.xiaohei.matcher;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.util.ByteSource;
import java.util.Objects;

/**
 * @author : WiuLuS
 * @version : v1.11.28.2019
 * @discription :
 * @date : 2019-11-28 10:22:00
 * @email : m13886933623@163.com
 */
public class PasswordMatcher extends HashedCredentialsMatcher {

    /**
     * TODO : 这里进行密码校验，校验失败抛出
     * @param token
     * @param info
     * @return
     */
    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {

        if (token instanceof AcceptTicket){
            AcceptTicket from = (AcceptTicket) token;
            // 从 token 中获取用户登录密码，也就是用户登录时输入的密码
            // 可以是明文，也可能是对称加密后的密文
            String inPass = from.getPassword();
            // 强制类型转换，因为 info 是从 realm 中传递过来的
            // ( realm 中 doGetAuthenticationInfo 方法返回值就是这个info参数)
            SimpleAuthenticationInfo userInfo = (SimpleAuthenticationInfo)info;
            // 从 info 中获取加密随机盐
            ByteSource credentialsSalt = userInfo.getCredentialsSalt();
            // 进行 MD5 加密返回密文 32位字符串
            String inPassAfterEncrypt = new Md5Hash(inPass, credentialsSalt).toString();
            // 从 info 中获取数据库存储的用户密码
            String dbPassword = userInfo.getCredentials().toString();
            // 打印日志 TODO : 进行用户登录操作日志打印
            System.out.println("inPassAfterEncrypt : " + inPassAfterEncrypt);
            System.out.println("inPass + user.getSalt() : " + inPass + credentialsSalt);
            //进行密码的比对
            boolean flag = Objects.equals(inPassAfterEncrypt , dbPassword );
            if ( flag ){
               return true ;
            }
        }
        // （错误的凭证）
        throw new IncorrectCredentialsException("密码错误！");

    }

}
