package com.xiaohei.entity.table;

import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;

/**
 * @author : WiuLuS
 * @version : v1.11.22.2019
 * @discription :
 * @date : 2019-11-22 14:35:34
 * @email : m13886933623@163.com
 */
@TableName("LOCAL_USER")
public class UserEntity extends BaseTableEntity implements Serializable {

    private String userName ;
    private String password ;
    private String email ;
    private String salt ;
    private Integer age ;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
