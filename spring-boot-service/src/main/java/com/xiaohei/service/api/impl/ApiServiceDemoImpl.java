package com.xiaohei.service.api.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.xiaohei.dao.api.ApiDemoDao;
import com.xiaohei.entity.param.SelectUserConditionEntity;
import com.xiaohei.entity.param.UserModelEntity;
import com.xiaohei.entity.table.UserEntity;
import com.xiaohei.service.api.ApiServiceDemo;
import com.xiaohei.utils.R;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.io.Serializable;
import java.util.Map;

/**
 * @author : WiuLuS
 * @version : v1.11.22.2019
 * @discription :
 * @date : 2019-11-22 14:07:08
 * @email : m13886933623@163.com
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class ApiServiceDemoImpl extends ServiceImpl<ApiDemoDao, UserEntity> implements ApiServiceDemo {

    @Override
    public R insertBy(UserModelEntity user) {
        Integer size = baseMapper.insert(user);
        return size > 0 ? R.ok() : R.error() ;
    }

    @Override
    public R updateBy(UserModelEntity user) {
        Integer size = baseMapper.updateById(user);
        return size > 0 ? R.ok() : R.error() ;
    }

    @Override
    public R deleteBy(Serializable id) {
        Integer size = baseMapper.deleteById(id);
        return size > 0 ? R.ok() : R.error() ;
    }

    @Override
    public R selectBy(SelectUserConditionEntity condition) {
        if (condition.getEqContidion() == null){
            return R.ok().put("userList",baseMapper.selectList(new EntityWrapper<UserEntity>()));
        }else {
            Map<String, Object> eqContidion = condition.getEqContidion();
            EntityWrapper<UserEntity> wap = new EntityWrapper<>();
            for (String key : eqContidion.keySet()){
                wap.eq(key.toUpperCase() , eqContidion.get(key));
            }
            return R.ok().put("userList",baseMapper.selectList(wap));
        }
    }
}
