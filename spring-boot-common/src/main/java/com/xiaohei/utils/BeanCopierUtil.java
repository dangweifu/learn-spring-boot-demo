package com.xiaohei.utils;

import org.springframework.cglib.beans.BeanCopier;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @author : WiuLuS
 * @version : v1.0 05.19.2020
 * @discription :
 * @date : 2020-05-19 11:16:48
 * @email : m13886933623@163.com
 */
public class BeanCopierUtil {

    /**
     * BeanCopier的缓存
     */
    static final ConcurrentHashMap<String, BeanCopier> BEAN_COPIER_CACHE = new ConcurrentHashMap<>();

    /**
     * 生成key
     * @param srcClazz 源文件的class
     * @param tgtClazz 目标文件的class
     * @return string
     */
    private static String genKey(Class<?> srcClazz, Class<?> tgtClazz) {
        return srcClazz.getName() + tgtClazz.getName();
    }

    /**
     * BeanCopier的copy
     * @param source 源文件的
     * @param target 目标文件
     */
    public static void copy(Object source, Object target) {
        String key = genKey(source.getClass(), target.getClass());
        BeanCopier beanCopier;
        if (BEAN_COPIER_CACHE.containsKey(key)) {
            beanCopier = BEAN_COPIER_CACHE.get(key);
        } else {
            beanCopier = BeanCopier.create(source.getClass(), target.getClass(), false);
            BEAN_COPIER_CACHE.put(key, beanCopier);
        }
        beanCopier.copy(source, target, null);
    }
    /**
     * BeanCopier的copy
     * 将 source 实体 copy 至 target 实体
     * @param source 源文件的
     * @param target 目标文件
     */
    public static void copyNoCache(Object source , Object target){
        BeanCopier.create(source.getClass(),target.getClass(),false).copy(source,target,null);
    }

    /**
     * 对象 copy
     * @param source
     * @param target
     * @param <T>
     * @return
     */
    public static <T> T copy(Object source , Class<T> target){
        if(source == null){
            return null;
        }
        T targetObject = null;
        try {
            targetObject = target.newInstance();
            copyNoCache(source, targetObject);
        } catch (Exception e) {
            // todo : 输出日志
        }

        return targetObject;
    }

}
