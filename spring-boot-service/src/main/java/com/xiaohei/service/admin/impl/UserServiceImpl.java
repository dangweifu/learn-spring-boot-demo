package com.xiaohei.service.admin.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.xiaohei.dao.admin.UserDao;
import com.xiaohei.entity.table.UserEntity;
import com.xiaohei.service.admin.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author : WiuLuS
 * @version : v1.12.09.2019
 * @discription :
 * @date : 2019-12-09 16:43:40
 * @email : m13886933623@163.com
 */
@Service
@Transactional(rollbackFor = RuntimeException.class)
public class UserServiceImpl extends ServiceImpl<UserDao, UserEntity> implements UserService {
}
