/**
 * Copyright (c) 2018 WiuLuS All rights reserved.
 *
 * https://www.baidu.com
 *
 * 版权所有，侵权必究！
 */

package com.xiaohei.entity.table;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.FieldFill;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * 定时任务
 *
 * @author Mark sunlightcs@gmail.com
 */
@Data
@EqualsAndHashCode(callSuper=false)
@TableName("local_schedule_job")
public class ScheduleJobEntity extends BaseTableEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * spring bean名称
	 */
	private String beanName;
	/**
	 * 参数
	 */
	private String params;
	/**
	 * cron表达式
	 */
	private String cronExpression;
	/**
	 * 任务状态  0：暂停  1：正常
	 */
	private Integer status;
	/**
	 * 备注
	 */
	private String remark;

	/**
	 * 更新者
	 */
	@TableField(fill = FieldFill.INSERT_UPDATE)
	private String updater;
	/**
	 * 更新时间
	 */
	@TableField(fill = FieldFill.INSERT_UPDATE)
	private Date updateDate;
}
