package com.xiaohei.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * @author : WiuLuS
 * @version : v1.11.22.2019
 * @discription : 当前类主要用来响应数据或提示语给前端
 * @date : 2019-11-22 15:01:03
 * @email : m13886933623@163.com
 */
public class R extends HashMap<String , Object> {
    private static final long serialVersionUID = 1L;

    public R() {
        put("code", 0);
        put("msg","success");
    }

    public static R error() {
        return error(500, "未知异常，请联系管理员");
    }

    public static R error(String msg) {
        return error(500, msg);
    }

    public static R error(int code, String msg) {
        R r = new R();
        r.put("code", code);
        r.put("msg", msg);
        return r;
    }

    public static R ok(String msg) {
        R r = new R();
        r.put("msg", msg);
        return r;
    }

    public static R ok(Map<String, Object> map) {
        R r = new R();
        r.putAll(map);
        return r;
    }

    public static R ok() {
        return new R();
    }
    @Override
    public R put(String key, Object value) {
        super.put(key, value);
        return this;
    }
}
