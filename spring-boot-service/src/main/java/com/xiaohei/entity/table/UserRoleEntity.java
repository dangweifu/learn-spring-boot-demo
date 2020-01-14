package com.xiaohei.entity.table;

import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author : WiuLuS
 * @version : v1.01.14.2020
 * @discription :
 * @date : 2020-01-14 11:42:19
 * @email : m13886933623@163.com
 */
@Data
@TableName("LOCAL_USER_ROLE")
public class UserRoleEntity extends BaseTableEntity implements Serializable {
    /** 角色名称 */
    private String roleName ;
    /** 父角色ID */
    private String parentId ;
    /** 创建人ID */
    private String creator  ;
    /** 创建时间 */
    private Date   createTime ;
    /** 修改时间 */
    private Date   updateTime ;
    /** 是(1)否(0)删除 */
    private String isDel ;
    /** 备注说明 */
    private String remark ;
}
