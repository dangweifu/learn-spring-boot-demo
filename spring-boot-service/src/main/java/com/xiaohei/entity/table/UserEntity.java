package com.xiaohei.entity.table;

import com.baomidou.mybatisplus.annotations.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author : WiuLuS
 * @version : v1.11.22.2019
 * @discription :
 * @date : 2019-11-22 14:35:34
 * @email : m13886933623@163.com
 */
@Data
@TableName("LOCAL_USER")
@ApiModel
public class UserEntity extends BaseTableEntity implements Serializable {
    /** 用户名称 */
    @ApiModelProperty("用户名称")
    private String userName ;
    /** 登录密码 */
    @ApiModelProperty("登录密码")
    private String password ;
    /** 用户邮箱 */
    @ApiModelProperty("用户邮箱")
    private String email ;
    /** 随机盐，用来给密码加密使用 */
    private String salt ;
    /** 用户年龄 */
    @ApiModelProperty("用户年龄")
    private Integer age ;
}
