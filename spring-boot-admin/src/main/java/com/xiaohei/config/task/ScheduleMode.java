package com.xiaohei.config.task;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

/**
 * @author : WiuLuS
 * @version : v1.01.16.2020
 * @discription : 定时任务统一管理类
 * @date : 2020-01-16 14:09:14
 * @email : m13886933623@163.com
 */
//@Service
public class ScheduleMode {
    /**
     * 时间表达式的书写格式详解请参考 ： https://www.cnblogs.com/ark-blog/p/9000079.html
     */
//    @Scheduled(cron = "0/3 * * * * *")
    public void test(){
        long start = System.currentTimeMillis();
        System.out.println("定时任务开始：---");
        System.out.println("定时任务结束：---");
        System.out.println("共耗时：" + (System.currentTimeMillis()-start));
    }

}
