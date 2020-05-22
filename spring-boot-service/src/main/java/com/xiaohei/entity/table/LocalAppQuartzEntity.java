package com.xiaohei.entity.table;

import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

/**
 * @author : WiuLuS
 * @version : v1.0 05.22.2020
 * @discription :
 * @date : 2020-05-22 13:48:29
 * @email : m13886933623@163.com
 */
@Data
@TableName("LOCAL_APP_QUARTZ")
public class LocalAppQuartzEntity extends BaseTableEntity {

    /**
     * 任务名称
     */
    private String jobName;
    /**
     * 任务分组
     */
    private String jobGroup;
    /**
     * 任务开始时间
     */
    private String jobStartTime;
    /**
     * corn表达式
     */
    private String jobCronExpression;
    /**
     * 需要传递的参数
     */
    private String jobInvokeParam;
}
