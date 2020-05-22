package com.xiaohei.scheduler.dto;

import lombok.Data;

/**
 * @author : WiuLuS
 * @version : v1.0 05.22.2020
 * @discription :
 * @date : 2020-05-22 11:21:54
 * @email : m13886933623@163.com
 */
@Data
public class AppQuartz {
    /**
     * 主键
     */
    private Integer quartzId;
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
    private String startTime;
    /**
     * corn表达式
     */
    private String cronExpression;
    /**
     * 需要传递的参数
     */
    private String invokeParam;

}
