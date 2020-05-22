/**
 * Copyright (c) 2018 WiuLuS All rights reserved.
 *
 * https://www.baidu.com
 *
 * 版权所有，侵权必究！
 */

package com.xiaohei.scheduler.utils;

import com.xiaohei.constant.Constant;
import com.xiaohei.entity.table.ScheduleJobEntity;
import com.xiaohei.entity.table.ScheduleJobLogEntity;
import com.xiaohei.exception.ExceptionUtils;
import com.xiaohei.service.common.ScheduleJobLogService;
import com.xiaohei.service.common.impl.ScheduleJobLogServiceImpl;
import com.xiaohei.utils.SpringContextUtils;
import org.quartz.JobExecutionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.lang.reflect.Method;
import java.util.Date;


/**
 * 定时任务
 *
 * @author Mark sunlightcs@gmail.com
 */
public class ScheduleJob extends QuartzJobBean {
	private Logger logger = LoggerFactory.getLogger(getClass());


	public static ScheduleJobLogService logService = null ;

	static {
		if (logService == null){
			logService = SpringContextUtils.getBean(ScheduleJobLogServiceImpl.class);
		}
	}

    @Override
    protected void executeInternal(JobExecutionContext context) {
        ScheduleJobEntity scheduleJob = (ScheduleJobEntity) context.getMergedJobDataMap().
				get(ScheduleUtils.JOB_PARAM_KEY);

        //数据库保存执行记录
        ScheduleJobLogEntity log = new ScheduleJobLogEntity();
        log.setJobId(scheduleJob.getId());
        log.setBeanName(scheduleJob.getBeanName());
        log.setParams(scheduleJob.getParams());
		log.setCreateDate(new Date());

        //任务开始时间
        long startTime = System.currentTimeMillis();
        
        try {
			//执行任务
			logger.info("任务准备执行，任务ID：{}", scheduleJob.getId());
			Object target = SpringContextUtils.getBean(scheduleJob.getBeanName());
			Method method = target.getClass().getDeclaredMethod("run", String.class);
			method.invoke(target, scheduleJob.getParams());

			//任务执行总时长
			long times = System.currentTimeMillis() - startTime;
			log.setTimes((int)times);
			//任务状态
			log.setStatus(Constant.SUCCESS);
			
			logger.info("任务执行完毕，任务ID：{}  总共耗时：{} 毫秒", scheduleJob.getId(), times);
		} catch (Exception e) {
			logger.error("任务执行失败，任务ID：{}", scheduleJob.getId(), e);
			
			//任务执行总时长
			long times = System.currentTimeMillis() - startTime;
			log.setTimes((int)times);
			
			//任务状态
			log.setStatus(Constant.FAIL);
			log.setError(ExceptionUtils.getErrorStackTrace(e));
		}finally {
			//获取spring bean
			ScheduleJobLogService scheduleJobLogService = SpringContextUtils.getBean(ScheduleJobLogService.class);
			scheduleJobLogService.insert(log);
		}
    }
}