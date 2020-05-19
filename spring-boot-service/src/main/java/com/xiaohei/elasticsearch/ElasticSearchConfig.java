package com.xiaohei.elasticsearch;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author : WiuLuS
 * @version : v1.0 05.19.2020
 * @discription :
 * @date : 2020-05-19 17:19:22
 * @email : m13886933623@163.com
 */
@Configuration
public class ElasticSearchConfig {


    @Bean
    @Qualifier("restHighLevelClient")
    public RestHighLevelClient getHighLevelClient(){
        return new RestHighLevelClient(RestClient.builder(
                new HttpHost("127.0.0.1", 9200, "http")));
    }

}
