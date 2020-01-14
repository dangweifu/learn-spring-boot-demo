package com.xiaohei.entity.result;

import lombok.Data;

import java.io.Serializable;

/**
 * @author : WiuLuS
 * @version : v1.01.14.2020
 * @discription :
 * @date : 2020-01-14 14:45:09
 * @email : m13886933623@163.com
 */
@Data
public class UserPermissionVo implements Serializable {

    /** 用户 ID */
    private String id ;
    /** 用户权限名称 */
    private String name ;
    /** 用户权限标识 */
    private String value ;

}
