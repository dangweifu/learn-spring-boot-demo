package com.xiaohei.service.admin.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.xiaohei.dao.admin.UserRoleDao;
import com.xiaohei.entity.table.UserRoleEntity;
import com.xiaohei.service.admin.UserPermissionService;
import com.xiaohei.service.admin.UserRoleService;
import com.xiaohei.utils.R;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @author : WiuLuS
 * @version : v1.01.14.2020
 * @discription :
 * @date : 2020-01-14 14:22:43
 * @email : m13886933623@163.com
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class UserRoleServiceImpl extends ServiceImpl<UserRoleDao , UserRoleEntity> implements UserRoleService {

}
