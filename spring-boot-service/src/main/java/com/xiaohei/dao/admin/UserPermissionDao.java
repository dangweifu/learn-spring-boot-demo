package com.xiaohei.dao.admin;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.xiaohei.entity.table.UserPermissionEntity;

import java.util.Set;

/**
 * @author : WiuLuS
 * @version : v1.01.14.2020
 * @discription :
 * @date : 2020-01-14 14:14:02
 * @email : m13886933623@163.com
 */
public interface UserPermissionDao extends BaseMapper<UserPermissionEntity> {
    /**
     * 根据用户ID获取用户权限集合，以 Set 集合返回，且以统一返回对象 R 包装一层。
     * @param userId ： 用户 ID
     * @return ：结果对象
     */
    Set<String> getUserPermissionSetBy(String userId);
}
