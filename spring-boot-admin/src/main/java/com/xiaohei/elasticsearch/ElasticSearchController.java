package com.xiaohei.elasticsearch;

import com.xiaohei.utils.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author : WiuLuS
 * @version : v1.0 05.21.2020
 * @discription :
 * @date : 2020-05-21 11:41:03
 * @email : m13886933623@163.com
 */
@RestController
@RequestMapping("/elasticsearch")
@Api(tags = "ElasticSearch相关操作" , value = "VALUE" , produces = MediaType.APPLICATION_JSON_VALUE)
public class ElasticSearchController {

    @Resource
    private BaseElasticService elasticService ;

    /** 创建索引 index
     *  shiro 权限控制：只有拥有后边权限标识的用户才能访问此接口 @RequiresPermissions(value = {"admin:user:create"})
     **/
    @GetMapping("/create/{index}")
    @ApiOperation(value = "根据业务ID查询业务详情(这里是接口标签(tab)描述)",notes = "这里是接口详情描述")
//    @RequiresPermissions(value = {"admin:user:create"})
    public R creatIndex(@PathVariable String index , String jsonParam){
        elasticService.createIndex( index.toLowerCase() , jsonParam);
        return R.ok();
    }

    /** 添加文档 index
     *  shiro 权限控制：只有拥有后边权限标识的用户才能访问此接口 @RequiresPermissions(value = {"admin:user:create"})
     **/
    @GetMapping("/add")
    @ApiOperation(value = "添加文档(这里是接口标签(tab)描述)",notes = "这里是接口详情描述")
    @RequiresPermissions(value = {"admin:user:create"})
    public R addDoc(@RequestBody Object o){
        // todo : something
        return R.ok();
    }


    /** 删除文档 index
     *  shiro 权限控制：只有拥有后边权限标识的用户才能访问此接口 @RequiresPermissions(value = {"admin:user:delete"})
     **/
    @GetMapping("/delete")
    @ApiOperation(value = "删除文档(这里是接口标签(tab)描述)",notes = "这里是接口详情描述")
    @RequiresPermissions(value = {"admin:user:delete"})
    public R deleteDoc(@RequestBody Object o){
        // todo : something
        return R.ok();
    }


    /** 修改文档 index
     *  shiro 权限控制：只有拥有后边权限标识的用户才能访问此接口 @RequiresPermissions(value = {"admin:user:update"})
     **/
    @GetMapping("/update")
    @ApiOperation(value = "修改文档(这里是接口标签(tab)描述)",notes = "这里是接口详情描述")
    @RequiresPermissions(value = {"admin:user:update"})
    public R updateDoc(@RequestBody Object o){
        // todo : something
        return R.ok();
    }


    /** 查询文档 index
     *  shiro 权限控制：只有拥有后边权限标识的用户才能访问此接口 @RequiresPermissions(value = {"admin:user:query"})
     **/
    @GetMapping("/query")
    @ApiOperation(value = "查询文档(这里是接口标签(tab)描述)",notes = "这里是接口详情描述")
    @RequiresPermissions(value = {"admin:user:query"})
    public R queryDoc(@RequestBody Object o){
        // todo : something
        return R.ok();
    }

}
