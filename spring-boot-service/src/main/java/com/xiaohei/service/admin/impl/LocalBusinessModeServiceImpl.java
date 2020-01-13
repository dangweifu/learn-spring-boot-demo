package com.xiaohei.service.admin.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.xiaohei.dao.admin.LocalBusinessModeDao;
import com.xiaohei.entity.table.LocalBusinessModeEntity;
import com.xiaohei.service.admin.LocalBusinessModeService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

}
