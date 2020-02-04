package com.xiaohei.entity.table;


import com.baomidou.mybatisplus.annotations.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author : WiuLuS
 * @version : v1.01.13.2020
 * @discription :
 * @date : 2020-01-13 15:32:19
 * @email : m13886933623@163.com
 */
@Data
@TableName("LOCAL_BUSINESS_MODE")
public class LocalBusinessModeEntity extends BaseTableEntity implements Serializable {

    @ApiModelProperty(value = "业务名称")
    private String businessName ;
    @ApiModelProperty(value = "业务类型"  )
    private String businessType ;
    @ApiModelProperty(value = "业务编号"  )
    private String businessNo   ;

}
