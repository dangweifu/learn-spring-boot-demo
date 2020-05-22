/**
 * Copyright (c) 2018 WiuLuS All rights reserved.
 *
 * https://www.baidu.com
 *
 * 版权所有，侵权必究！
 */

package com.xiaohei.service.common.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.xiaohei.constant.Constant;
import com.xiaohei.dao.common.ScheduleJobDao;
import com.xiaohei.dao.common.ScheduleJobLogDao;
import com.xiaohei.entity.dto.ScheduleJobLogDTO;
import com.xiaohei.entity.table.ScheduleJobLogEntity;
import com.xiaohei.page.PageData;
import com.xiaohei.service.common.ScheduleJobLogService;
import com.xiaohei.utils.BeanCopierUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class ScheduleJobLogServiceImpl extends ServiceImpl<ScheduleJobLogDao, ScheduleJobLogEntity> implements ScheduleJobLogService {

	@Autowired
	private ScheduleJobLogDao dao ;
	
//	@Override
//	public PageData<ScheduleJobLogDTO> page(Map<String, Object> params) {
//		IPage<ScheduleJobLogEntity> page = dao.selectPage(
//			getPage(params, Constant.CREATE_DATE, false),
//			getWrapper(params)
//		);
//		return getPageData(page, ScheduleJobLogDTO.class);
//	}

//	private QueryWrapper<ScheduleJobLogEntity> getWrapper(Map<String, Object> params){
//		String jobId = (String)params.get("jobId");
//
//		QueryWrapper<ScheduleJobLogEntity> wrapper = new QueryWrapper<>();
//		wrapper.eq(StringUtils.isNotBlank(jobId), "job_id", jobId);
//
//		return wrapper;
//	}

	@Override
	public PageData<ScheduleJobLogDTO> page(Map<String, Object> params) {
		return null;
	}

	@Override
	public ScheduleJobLogDTO get(String jobId) {
		ScheduleJobLogEntity entity = dao.selectById(jobId);
		return BeanCopierUtil.copy(entity, ScheduleJobLogDTO.class);
	}

}