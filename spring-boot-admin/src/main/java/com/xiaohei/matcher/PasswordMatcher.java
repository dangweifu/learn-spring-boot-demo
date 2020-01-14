package com.xiaohei.matcher;

import com.xiaohei.entity.table.UserEntity;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.apache.tomcat.util.security.MD5Encoder;

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
            String inPass = from.getPassword();
            SimpleAuthenticationInfo userInfo = (SimpleAuthenticationInfo)info;
            Object o = userInfo.getCredentials();
            ByteSource credentialsSalt = userInfo.getCredentialsSalt();


            String inPassAfterEncrypt = new Md5Hash(inPass, credentialsSalt).toString();
            String dbPassword = userInfo.getCredentials().toString();

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
