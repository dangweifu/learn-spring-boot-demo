package com.xiaohei.scheduler.job;

import org.quartz.*;
import org.springframework.stereotype.Component;

/**
 * @author : WiuLuS
 * @version : v1.0 05.22.2020
 * @discription :
 * @date : 2020-05-22 11:27:50
 * @email : m13886933623@163.com
 */
@PersistJobDataAfterExecution
@DisallowConcurrentExecution
@Component
public class JobTwo implements Job {

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        JobDataMap data=context.getTrigger().getJobDataMap();
        String invokeParam =(String) data.get("invokeParam");
        //在这里实现业务逻辑
    }

}
