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
import com.xiaohei.entity.dto.ScheduleJobDTO;
import com.xiaohei.entity.table.ScheduleJobEntity;
import com.xiaohei.page.PageData;
import com.xiaohei.scheduler.utils.ScheduleUtils;
import com.xiaohei.service.common.ScheduleJobService;
import com.xiaohei.utils.BeanCopierUtil;
import org.apache.commons.lang3.StringUtils;
import org.quartz.Scheduler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Service
public class ScheduleJobServiceImpl extends ServiceImpl<ScheduleJobDao, ScheduleJobEntity> implements ScheduleJobService {
	@Autowired
	private Scheduler scheduler;

	private ScheduleJobDao dao ;
	
	@Override
	public PageData<ScheduleJobDTO> page(Map<String, Object> params) {
//		this.selectPage()
//		IPage<ScheduleJobEntity> page = this.selectPage(
//			getPage(params, Constant.CREATE_DATE, false),
//			getWrapper(params)
//		);
//		return getPageData(page, ScheduleJobDTO.class);
		return null ;
	}

	@Override
	public ScheduleJobDTO get(String jobId) {
		ScheduleJobEntity entity = dao.selectById(jobId);
		return BeanCopierUtil.copy(entity, ScheduleJobDTO.class);
	}

//	private QueryWrapper<ScheduleJobEntity> getWrapper(Map<String, Object> params){
//		String beanName = (String)params.get("beanName");
//
//		QueryWrapper<ScheduleJobEntity> wrapper = new QueryWrapper<>();
//		wrapper.like(StringUtils.isNotBlank(beanName), "bean_name", beanName);
//
//		return wrapper;
//	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void save(ScheduleJobDTO dto) {
		ScheduleJobEntity entity = BeanCopierUtil.copy(dto, ScheduleJobEntity.class);

		entity.setStatus(Constant.ScheduleStatus.NORMAL.getValue());
        this.insert(entity);
        
        ScheduleUtils.createScheduleJob(scheduler, entity);
    }
	
	@Override
	@Transactional(rollbackFor = Exception.class)
	public void update(ScheduleJobDTO dto) {
		ScheduleJobEntity entity = BeanCopierUtil.copy(dto, ScheduleJobEntity.class);

        ScheduleUtils.updateScheduleJob(scheduler, entity);
                
        this.updateById(entity);
    }

	@Override
	@Transactional(rollbackFor = Exception.class)
    public void deleteBatch(String[] ids) {
    	for(String jobId : ids){
    		ScheduleUtils.deleteScheduleJob(scheduler, jobId);
    	}
    	
    	//删除数据
    	this.deleteBatchIds(Arrays.asList(ids));
	}

	@Override
    public int updateBatch(String[] ids, int status){
    	Map<String, Object> map = new HashMap<>(2);
    	map.put("ids", ids);
    	map.put("status", status);
    	return dao.updateBatch(map);
    }
    
	@Override
	@Transactional(rollbackFor = Exception.class)
    public void run(String[] ids) {
    	for(String jobId : ids){
    		ScheduleUtils.run(scheduler, this.selectById(jobId));
    	}
    }

	@Override
	@Transactional(rollbackFor = Exception.class)
    public void pause(String[] ids) {
        for(String jobId : ids){
    		ScheduleUtils.pauseJob(scheduler, jobId);
    	}
        
    	updateBatch(ids, Constant.ScheduleStatus.PAUSE.getValue());
    }

	@Override
	@Transactional(rollbackFor = Exception.class)
    public void resume(String[] ids) {
    	for(String jobId : ids){
    		ScheduleUtils.resumeJob(scheduler, jobId);
    	}

    	updateBatch(ids, Constant.ScheduleStatus.NORMAL.getValue());
    }
    
}