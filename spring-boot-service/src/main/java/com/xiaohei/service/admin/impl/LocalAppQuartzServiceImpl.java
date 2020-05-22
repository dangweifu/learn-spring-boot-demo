package com.xiaohei.service.admin.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.xiaohei.dao.admin.LocalAppQuartzDao;
import com.xiaohei.entity.table.LocalAppQuartzEntity;
import com.xiaohei.service.admin.LocalAppQuartzService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @author : WiuLuS
 * @version : v1.0 05.22.2020
 * @discription :
 * @date : 2020-05-22 14:04:03
 * @email : m13886933623@163.com
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class LocalAppQuartzServiceImpl extends ServiceImpl<LocalAppQuartzDao, LocalAppQuartzEntity>
        implements LocalAppQuartzService {

    @Resource
    private LocalAppQuartzDao dao ;




}
