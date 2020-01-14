package com.xiaohei.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @author : WiuLuS
 * @version : v1.12.09.2019
 * @discription :
 * @date : 2019-12-09 15:29:33
 * @email : m13886933623@163.com
 */
@Data
public class LoginForm implements Serializable {

    public LoginForm() {
    }

    public LoginForm(String username, String password) {
        this.username = username;
        this.password = password;
    }

    private String username ;
    private String password ;
    private String captcha ;

}
