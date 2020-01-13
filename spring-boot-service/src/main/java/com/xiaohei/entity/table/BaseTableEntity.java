package com.xiaohei.entity.table;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author : WiuLuS
 * @version : v1.11.22.2019
 * @discription :
 * @date : 2019-11-22 14:36:19
 * @email : m13886933623@163.com
 */
@Data
public class BaseTableEntity implements Serializable {
    @TableId
    @ApiModelProperty("ID")
    private String id ;
}
