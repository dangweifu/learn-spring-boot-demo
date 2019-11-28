package com.xiaohei.entity.param;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * @author : WiuLuS
 * @version : v1.11.26.2019
 * @discription :
 * @date : 2019-11-26 11:16:49
 * @email : m13886933623@163.com
 */
public class SelectUserConditionEntity implements Serializable {

    public SelectUserConditionEntity() {
    }

    public SelectUserConditionEntity(Map<String, Object> eqContidion) {
        this.eqContidion = eqContidion;
    }

    private Map<String,Object> eqContidion ;

    public Map<String, Object> getEqContidion() {
        return eqContidion;
    }

    public void setEqContidion(Map<String, Object> eqContidion) {
        this.eqContidion = eqContidion;
    }

    /**
     * 添加全量匹配查询条件
     * @param key ：表字段名称（全大写）
     * @param value ： 对应字段值
     */
    public void putEqCondition(String key , Object value){
        this.eqContidion.put(key.toUpperCase(),value);
    }

}
