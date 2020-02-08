package com.xiaohei.matcher;


import com.xiaohei.entity.LoginForm;
import org.apache.shiro.authc.AuthenticationToken;

/**
 * @author : WiuLuS
 * @version : v1.11.28.2019
 * @discription :
 * @date : 2019-11-28 16:43:14
 * @email : m13886933623@163.com
 */

public class AcceptTicket extends LoginForm implements AuthenticationToken {
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

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "AcceptTicket{" +
                "token='" + token + '\'' +
                '}';
    }
}
