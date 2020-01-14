package com.xiaohei.entity.table;

import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author : WiuLuS
 * @version : v1.01.14.2020
 * @discription :
 * @date : 2020-01-14 11:46:22
 * @email : m13886933623@163.com
 */
@Data
@TableName("LOCAL_USER_PERMISSION")
public class UserPermissionEntity extends BaseTableEntity implements Serializable {
    /** 权限名称 */
    private String permissionName ;
    /** 权限值（权限标识） */
    private String permissionValue ;
    /** 创建人ID */
    private String creator ;
    /** 创建时间 */
    private Date   createTime ;
    /** 更新时间，每次修改都必须更新该字段 */
    private Date   updateTime ;
    /** 备注说明 */
    private String remark ;
    /** 是(1)否(0)删除 */
    private String isDel ;

}
