package com.xiaohei.scheduler;

import com.xiaohei.constant.Constant;
import com.xiaohei.entity.dto.ScheduleJobDTO;
import com.xiaohei.service.common.ScheduleJobService;
import com.xiaohei.utils.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.Map;

/**
 * @author : WiuLuS
 * @version : v1.0 05.22.2020
 * @discription :
 * @date : 2020-05-22 11:32:49
 * @email : m13886933623@163.com
 */
@RestController
@RequestMapping("/quartz/job")
@Api(tags = "定时任务管理相关操作" , value = "VALUE" , produces = MediaType.APPLICATION_JSON_VALUE)
public class JobController {
    @Autowired
    private ScheduleJobService scheduleJobService;

    @GetMapping("page")
    @ApiOperation("分页")
    @ApiImplicitParams({
            @ApiImplicitParam(name = Constant.PAGE, value = "当前页码，从1开始", paramType = "query", required = true, dataType="int") ,
            @ApiImplicitParam(name = Constant.LIMIT, value = "每页显示记录数", paramType = "query",required = true, dataType="int") ,
            @ApiImplicitParam(name = Constant.ORDER_FIELD, value = "排序字段", paramType = "query", dataType="String") ,
            @ApiImplicitParam(name = Constant.ORDER, value = "排序方式，可选值(asc、desc)", paramType = "query", dataType="String") ,
            @ApiImplicitParam(name = "beanName", value = "beanName", paramType = "query", dataType="String")
    })
//    @RequiresPermissions("sys:schedule:page")
    public R page(@ApiIgnore @RequestParam Map<String, Object> params){
//        PageData<ScheduleJobDTO> page = scheduleJobService.page(params);
//
//        return new R<PageData<ScheduleJobDTO>>().ok(page);
        return R.ok();
    }

    @GetMapping("{id}")
    @ApiOperation("信息")
//    @RequiresPermissions("sys:schedule:info")
    public R info(@PathVariable("id") String id){
        ScheduleJobDTO schedule = scheduleJobService.get(id);

        return R.ok(schedule);
    }

    @PostMapping("save")
    @ApiOperation("保存")
//    @RequiresPermissions("sys:schedule:save")
    public R save(@RequestBody ScheduleJobDTO dto){
        scheduleJobService.save(dto);
        return new R();
    }

    @PutMapping("update")
    @ApiOperation("修改")
//    @RequiresPermissions("sys:schedule:update")
    public R update(@RequestBody ScheduleJobDTO dto){
        scheduleJobService.update(dto);
        return new R();
    }

    @DeleteMapping("delete")
    @ApiOperation("删除")
//    @RequiresPermissions("sys:schedule:delete")
    public R delete(@RequestBody String[] ids){
        scheduleJobService.deleteBatch(ids);

        return new R();
    }

    @PutMapping("/run")
    @ApiOperation("立即执行")
//    @RequiresPermissions("sys:schedule:run")
    public R run(@RequestBody String[] ids){
        scheduleJobService.run(ids);
        return new R();
    }

    @PutMapping("/pause")
    @ApiOperation("暂停")
//    @RequiresPermissions("sys:schedule:pause")
    public R pause(@RequestBody String[] ids){
        scheduleJobService.pause(ids);
        return new R();
    }

    @PutMapping("/recover")
    @ApiOperation("恢复")
//    @RequiresPermissions("sys:schedule:resume")
    public R resume(@RequestBody String[] ids){
        scheduleJobService.resume(ids);
        return new R();
    }

}
