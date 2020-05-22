package com.xiaohei.scheduler;

import com.xiaohei.scheduler.dto.AppQuartz;
import com.xiaohei.scheduler.utils.JobUtil;
import com.xiaohei.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : WiuLuS
 * @version : v1.0 05.22.2020
 * @discription :
 * @date : 2020-05-22 11:32:49
 * @email : m13886933623@163.com
 */
@RestController
@RequestMapping("/quartz/job")
public class JobController {

    @Autowired
    private JobUtil jobUtil;
    @Autowired
//    private AppQuartzService appQuartzService;

    public JobController() {
    }


    /**
     * 添加一个job
     */
    @RequestMapping(value="/add",method= RequestMethod.POST)
    public R addJob(@RequestBody AppQuartz appQuartz) throws Exception {

//        appQuartzService.insertAppQuartzSer(appQuartz);
        String job = jobUtil.addJob(appQuartz);
        return R.ok() ;
    }

    /**
     * 暂停job
     */
    @RequestMapping(value="/stop",method=RequestMethod.POST)
    public R stopJob(@RequestBody Integer[]quartzIds) throws Exception {
        AppQuartz appQuartz=null;
        if(quartzIds.length>0){
            for(Integer quartzId:quartzIds) {

//                appQuartz=appQuartzService.selectAppQuartzByIdSer(quartzId).get(0);
                jobUtil.pauseJob(appQuartz.getJobName(), appQuartz.getJobGroup());
            }
            return R.ok("success stopJob");
        }else {
            return R.error(404,"fail stopJob");
        }
    }

    /**
     * 恢复job
     */
    @RequestMapping(value="/recover",method=RequestMethod.POST)
    public R recoverJob(@RequestBody Integer[]quartzIds) throws Exception {
        AppQuartz appQuartz=null;
        if(quartzIds.length>0) {
            for(Integer quartzId:quartzIds) {
//                appQuartz=appQuartzService.selectAppQuartzByIdSer(quartzId).get(0);
                jobUtil.resumeJob(appQuartz.getJobName(), appQuartz.getJobGroup());
            }
            return R.ok("success recoverJob");
        }else {
            return R.error(404,"fail recoverJob");
        }
    }
    /**
     * 删除job
     */
    @RequestMapping(value="/deleteJob",method=RequestMethod.POST)
    public R deletejob(@RequestBody Integer[]quartzIds) throws Exception {
        AppQuartz appQuartz=null;
        for(Integer quartzId:quartzIds) {
//            appQuartz=appQuartzService.selectAppQuartzByIdSer(quartzId).get(0);
            String ret=jobUtil.deleteJob(appQuartz);
            if("success".equals(ret)) {
//                appQuartzService.deleteAppQuartzByIdSer(quartzId);
            }
        }
        return R.ok("success deletejob");
    }

    /**
     * 修改
     */
    @RequestMapping(value="/update",method=RequestMethod.POST)
    public R  updateJob(@RequestBody AppQuartz appQuartz) throws Exception {
        String ret= jobUtil.modifyJob(appQuartz);
        if("success".equals(ret)) {
//            appQuartzService.updateAppQuartzSer(appQuartz);
            return R.ok("success updateJob");
        }else {
            return R.error(400,"fail updateJob");
        }
    }

    /**
     * 暂停所有
     */
    @RequestMapping(value="/stop/all",method=RequestMethod.GET)
    public R stopAll() throws Exception {
        jobUtil.pauseAllJob();
        return R.ok("success stopAll");
    }

    /**
     * 恢复所有
     */
    @RequestMapping(value="/recover/all",method=RequestMethod.GET)
    public R recoverAll() throws Exception {
        jobUtil.resumeAllJob();
        return R.ok("success recoverAll");
    }

}
