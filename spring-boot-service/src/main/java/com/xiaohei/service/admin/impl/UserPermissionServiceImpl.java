package com.xiaohei.service.admin.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.xiaohei.dao.admin.UserPermissionDao;
import com.xiaohei.entity.table.UserPermissionEntity;
import com.xiaohei.service.admin.UserPermissionService;
import com.xiaohei.utils.R;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @author : WiuLuS
 * @version : v1.01.14.2020
 * @discription :
 * @date : 2020-01-14 14:28:45
 * @email : m13886933623@163.com
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class UserPermissionServiceImpl extends ServiceImpl<UserPermissionDao , UserPermissionEntity>
        implements UserPermissionService {

    @Resource
    private UserPermissionDao dao ;
    @Override
    public R getUserPermissionSetBy(String userId) {
        // TODO : 先检验参数是否合格 ，不合格直接返回异常
        return R.ok(dao.getUserPermissionSetBy(userId));
    }
}
