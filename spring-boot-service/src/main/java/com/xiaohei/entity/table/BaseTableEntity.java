package com.xiaohei.entity.table;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;

import java.io.Serializable;

/**
 * @author : WiuLuS
 * @version : v1.11.22.2019
 * @discription :
 * @date : 2019-11-22 14:36:19
 * @email : m13886933623@163.com
 */
public class BaseTableEntity implements Serializable {
    @TableId
    private String id ;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
