/**
 * Copyright (c) 2018 WiuLuS All rights reserved.
 *
 * https://www.baidu.com
 *
 * 版权所有，侵权必究！
 */

package com.xiaohei.service.common;


import com.baomidou.mybatisplus.service.IService;
import com.xiaohei.entity.dto.ScheduleJobDTO;
import com.xiaohei.entity.table.ScheduleJobEntity;
import com.xiaohei.page.PageData;

import java.util.Map;

/**
 * 定时任务
 *
 * @author Mark sunlightcs@gmail.com
 */
public interface ScheduleJobService extends IService<ScheduleJobEntity> {

	PageData<ScheduleJobDTO> page(Map<String, Object> params);

	ScheduleJobDTO get(String jobId);

	/**
	 * 保存定时任务
	 */
	void save(ScheduleJobDTO dto);
	
	/**
	 * 更新定时任务
	 */
	void update(ScheduleJobDTO dto);
	
	/**
	 * 批量删除定时任务
	 */
	void deleteBatch(String [] ids);
	
	/**
	 * 批量更新定时任务状态
	 */
	int updateBatch(String[] ids, int status);
	
	/**
	 * 立即执行
	 */
	void run(String[] ids);
	
	/**
	 * 暂停运行
	 */
	void pause(String[] ids);
	
	/**
	 * 恢复运行
	 */
	void resume(String[] ids);
}
