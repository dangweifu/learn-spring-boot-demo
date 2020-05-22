package com.xiaohei.mongodb.config;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.convert.*;
import org.springframework.data.mongodb.core.mapping.MongoMappingContext;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class MongoDBConfig  {

    @Bean
    public MappingMongoConverter mappingMongoConverter(MongoDbFactory factory, MongoMappingContext context, BeanFactory beanFactory, CustomConversions conversions) {
        DbRefResolver dbRefResolver = new DefaultDbRefResolver(factory);
        MappingMongoConverter mappingConverter = new MappingMongoConverter(dbRefResolver, context);
        mappingConverter.setCustomConversions(beanFactory.getBean(CustomConversions.class));
        //去掉默认mapper添加的_class
        mappingConverter.setTypeMapper(new DefaultMongoTypeMapper(null));
        //添加自定义的转换器
        mappingConverter.setCustomConversions(conversions);
        return mappingConverter;
    }

    @Bean
    public MappingMongoConverter mappingMongoConverter(MongoDbFactory dbFactory) throws Exception {
        DefaultDbRefResolver dbRefResolver = new DefaultDbRefResolver(dbFactory);
        MappingMongoConverter converter = new MappingMongoConverter(dbRefResolver, this.mongoMappingContext());
        List<Object> list = new ArrayList<>();
        //添加自定义的类型转换器
        list.add(new BigDecimalToDecimal128Converter());
        list.add(new Decimal128ToBigDecimalConverter());
        list.add(new TimestampConverter());
        list.add(new LongTimesConverter());
        converter.setCustomConversions(new MongoCustomConversions(list));
        return converter;
    }
    @Bean
    public MongoMappingContext mongoMappingContext() {
        MongoMappingContext mappingContext = new MongoMappingContext();
        return mappingContext;
    }


}
