package com.xiaohei.entity.table;

import com.baomidou.mybatisplus.annotations.TableName;
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
public class UserEntity extends BaseTableEntity implements Serializable {

    private String userName ;
    private String password ;
    private String email ;
    private String salt ;
    private Integer age ;
}
