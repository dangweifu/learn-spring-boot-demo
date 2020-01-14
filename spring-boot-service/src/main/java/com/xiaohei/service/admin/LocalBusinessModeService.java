package com.xiaohei.service.admin;

import com.baomidou.mybatisplus.service.IService;
import com.xiaohei.entity.param.BusinessModeDto;
import com.xiaohei.entity.table.LocalBusinessModeEntity;
import com.xiaohei.utils.R;

/**
 * @author : WiuLuS
 * @version : v1.01.13.2020
 * @discription :
 * @date : 2020-01-13 15:36:09
 * @email : m13886933623@163.com
 */
public interface LocalBusinessModeService extends IService<LocalBusinessModeEntity> {
    /**
     * 根据业务ID查询业务详情
     * @param id : id
     * @return : mode
     */
    R queryBusinessModeBy(String id);

    /**
     * 根据对象 ID 修改所有字段
     * @param dto ：业务实体
     * @return ：修改是否成功
     */
    R updateBusinessModeBy(BusinessModeDto dto);

    /**
     * 添加业务实体
     * @param dto ：入参实体
     * @return ：添加结果是否成功
     */
    R addBusinessMode(BusinessModeDto dto);

    /**
     * 根据 ID 删除业务实体
     * @param id ：ID
     * @return ：删除结果是否成功
     */
    R deleteById(String id);
}
