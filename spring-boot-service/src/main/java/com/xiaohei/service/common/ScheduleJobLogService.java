/**
 * Copyright (c) 2018 WiuLuS All rights reserved.
 *
 * https://www.baidu.com
 *
 * 版权所有，侵权必究！
 */

package com.xiaohei.service.common;


import com.baomidou.mybatisplus.service.IService;
import com.xiaohei.entity.dto.ScheduleJobLogDTO;
import com.xiaohei.entity.table.ScheduleJobLogEntity;
import com.xiaohei.page.PageData;

import java.util.Date;
import java.util.Map;

/**
 * 定时任务日志
 *
 * @author Mark sunlightcs@gmail.com
 */
public interface ScheduleJobLogService extends IService<ScheduleJobLogEntity> {

	PageData<ScheduleJobLogDTO> page(Map<String, Object> params);

	ScheduleJobLogDTO get(String jobId);


}
