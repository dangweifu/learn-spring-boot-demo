package com.xiaohei.service.api;

import com.baomidou.mybatisplus.service.IService;
import com.xiaohei.entity.param.SelectUserConditionEntity;
import com.xiaohei.entity.param.UserModelEntity;
import com.xiaohei.entity.table.UserEntity;
import com.xiaohei.utils.R;

import java.io.Serializable;

/**
 * @author : WiuLuS
 * @version : v1.11.22.2019
 * @discription :
 * @date : 2019-11-22 14:05:34
 * @email : m13886933623@163.com
 */

public interface ApiServiceDemo extends IService<UserEntity> {

    /**
     * 添加用户示例方法
     * @param user ：用户实体
     * @return ：响应消息实体（发生异常情况时可包含异常信息）
     */
    R insertBy(UserModelEntity user);

    /**
     * 修改用户示例方法
     * @param user ：用户实体
     * @return ： 响应消息实体（发生异常情况时可包含异常信息）
     */
    R updateBy(UserModelEntity user);

    /**
     * 删除用户示例方法
     * @param id : 用户ID
     * @return ： 响应消息实体（发生异常情况时可包含异常信息）
     */
    R deleteBy(Serializable id);

    /**
     * 查询用户示例方法
     * @param condition ：查询条件
     * @return ：响应消息实体（发生异常情况时可包含异常信息）
     */
    R selectBy(SelectUserConditionEntity condition);

}
