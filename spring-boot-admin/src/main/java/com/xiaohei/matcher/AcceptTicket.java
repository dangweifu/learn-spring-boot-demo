package com.xiaohei.matcher;

import org.apache.shiro.authc.AuthenticationToken;

/**
 * @author : WiuLuS
 * @version : v1.11.28.2019
 * @discription :
 * @date : 2019-11-28 16:43:14
 * @email : m13886933623@163.com
 */
public class AcceptTicket implements AuthenticationToken {

    private String token ;

    public AcceptTicket() {
    }

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
