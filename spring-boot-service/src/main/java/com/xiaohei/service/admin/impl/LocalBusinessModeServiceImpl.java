package com.xiaohei.service.admin.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.xiaohei.dao.admin.LocalBusinessModeDao;
import com.xiaohei.elasticsearch.BaseElasticService;
import com.xiaohei.entity.param.BusinessModeDto;
import com.xiaohei.entity.table.LocalBusinessModeEntity;
import com.xiaohei.service.admin.LocalBusinessModeService;
import com.xiaohei.utils.R;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @author : WiuLuS
 * @version : v1.01.13.2020
 * @discription :
 * @date : 2020-01-13 15:36:59
 * @email : m13886933623@163.com
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class LocalBusinessModeServiceImpl extends ServiceImpl<LocalBusinessModeDao, LocalBusinessModeEntity>
        implements LocalBusinessModeService {

    @Resource
    private LocalBusinessModeDao dao ;

    @Resource
    private BaseElasticService elasticService ;

    @Override
    public R queryBusinessModeBy(String id) {
        // TODO : 这里进行入参检查（非空、正则表达式匹配等等方式检查参数是否合格，非法参数直接抛出异常提示用户）
        elasticService.createIndex("dangweifu","{}");
        return R.ok(dao.selectById(id)) ;
    }

    @Override
    public R updateBusinessModeBy(BusinessModeDto dto) {
        // TODO : 这里进行入参检查（非空、正则表达式匹配等等方式检查参数是否合格，非法参数直接抛出异常提示用户）
        return R.ok(dao.updateAllColumnById(dto));
    }

    @Override
    public R addBusinessMode(BusinessModeDto dto) {
        // TODO : 这里进行入参检查（非空、正则表达式匹配等等方式检查参数是否合格，非法参数直接抛出异常提示用户）
        return R.ok(dao.insert(dto));
    }

    @Override
    public R deleteById(String id) {
        // TODO : 这里进行入参检查（非空、正则表达式匹配等等方式检查参数是否合格，非法参数直接抛出异常提示用户）
        return R.ok(dao.deleteById(id));
    }
}
