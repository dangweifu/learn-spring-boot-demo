package com.xiaohei.service.admin;


import com.baomidou.mybatisplus.service.IService;
import com.xiaohei.entity.table.UserPermissionEntity;
import com.xiaohei.utils.R;

/**
 * @author : WiuLuS
 * @version : v1.01.14.2020
 * @discription :
 * @date : 2020-01-14 14:25:16
 * @email : m13886933623@163.com
 */
public interface UserPermissionService extends IService<UserPermissionEntity> {
    /**
     * 根据用户ID获取用户权限集合，以 Set 集合返回，且以统一返回对象 R 包装一层。
     * @param userId ： 用户 ID
     * @return ：结果对象
     */
    R getUserPermissionSetBy(String userId);
}
