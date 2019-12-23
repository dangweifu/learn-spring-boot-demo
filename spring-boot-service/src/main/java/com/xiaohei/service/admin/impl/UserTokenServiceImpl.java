package com.xiaohei.service.admin.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.xiaohei.dao.admin.UserTokenDao;
import com.xiaohei.entity.table.UserTokenEntity;
import com.xiaohei.service.admin.UserTokenService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author : WiuLuS
 * @version : v1.12.09.2019
 * @discription :
 * @date : 2019-12-09 16:27:40
 * @email : m13886933623@163.com
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class UserTokenServiceImpl extends ServiceImpl<UserTokenDao, UserTokenEntity> implements UserTokenService  {
}
