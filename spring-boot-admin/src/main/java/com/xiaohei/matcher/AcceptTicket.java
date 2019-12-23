package com.xiaohei.matcher;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;
import com.xiaohei.entity.LoginForm;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.tomcat.util.security.MD5Encoder;
import org.springframework.beans.BeanUtils;
import sun.security.provider.MD5;

import java.util.Date;
import java.util.UUID;

/**
 * @author : WiuLuS
 * @version : v1.11.28.2019
 * @discription :
 * @date : 2019-11-28 16:43:14
 * @email : m13886933623@163.com
 */
public class AcceptTicket implements AuthenticationToken {
    /**
     * token : 允许用户访问某些资源（该资源需要登录才能访问）的令牌。
     */
    private String token ;
    public AcceptTicket() {}

    public AcceptTicket(String token) {
        this.token = token;
    }

    @Override
    public Object getPrincipal() {
        return token;
    }

    @Override
    public Object getCredentials() {
        return token;
    }
}
