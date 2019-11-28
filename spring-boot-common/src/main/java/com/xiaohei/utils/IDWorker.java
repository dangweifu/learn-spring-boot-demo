package com.xiaohei.utils;

import com.baomidou.mybatisplus.toolkit.IdWorker;

import java.io.Serializable;
import java.util.UUID;

/**
 * @author : WiuLuS
 * @version : v1.11.22.2019
 * @discription :
 * @date : 2019-11-22 20:44:58
 * @email : m13886933623@163.com
 */
public class IDWorker extends IdWorker implements Serializable {

    private static final String SUFFIX = "WiuLuS-" ;
    public static String getNextId(){
        String s = UUID.randomUUID().toString().replace("-", "").substring(0, 23);

        return SUFFIX + s ;
    }


}
