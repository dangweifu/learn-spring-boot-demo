package com.xiaohei.config.bean;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;

/**
 * @author : WiuLuS
 * @version : v1.01.17.2020
 * @discription :
 * @date : 2020-01-17 14:51:53
 * @email : m13886933623@163.com
 */
@Configuration
public class BeanConfig {

    /**
     * 配置 fastJson 消息序列化组件
     * @return
     */
    @Bean
    public HttpMessageConverters fastJsonHttpMessageConverters(){
        FastJsonHttpMessageConverter fastJsonConverter = new FastJsonHttpMessageConverter();
        FastJsonConfig fastJsonConfig = new FastJsonConfig();
        fastJsonConfig.setSerializerFeatures(SerializerFeature.PrettyFormat);
        fastJsonConverter.setFastJsonConfig(fastJsonConfig);
        return new HttpMessageConverters(fastJsonConverter) ;
    }

}
