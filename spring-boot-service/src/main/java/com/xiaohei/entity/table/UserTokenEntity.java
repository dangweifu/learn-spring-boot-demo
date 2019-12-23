package com.xiaohei.entity.table;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import com.xiaohei.constant.GlobalConstant;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

/**
 * @author : WiuLuS
 * @version : v1.12.09.2019
 * @discription :
 * @date : 2019-12-09 16:20:19
 * @email : m13886933623@163.com
 */
@Data
@TableName("LOCAL_USER_TOKEN")
public class UserTokenEntity implements Serializable {
    /**
     * 用户ID
     */
    @TableId(type= IdType.INPUT)
    private String id;
    /**
     * token : 允许用户访问某些资源（该资源需要登录才能访问）的令牌。
     */
    private String token ;
    /**
     * 过期时间
     */
    private Date expireTime;
    /**
     * 更新时间
     */
    private Date updateTime;

    public UserTokenEntity() {
    }
    public UserTokenEntity(Boolean isDefault) {
        if (isDefault){
            //当前时间
            Date now = new Date();
            this.token = UUID.randomUUID().toString().replaceAll("-","");
            this.expireTime = new Date(now.getTime() + GlobalConstant.DEFAULT_USER_TOKEN_EXPIRE_TIME  * 1000);
            this.updateTime = now ;
        }
    }
}
