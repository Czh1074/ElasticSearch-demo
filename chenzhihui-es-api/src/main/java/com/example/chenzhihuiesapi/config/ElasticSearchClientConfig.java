package com.example.chenzhihuiesapi.config;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * ES配置类
 * 1、找对象
 * 2、放到spring中代用即可
 * @Author: ChenZhiHui
 * @DateTime: 2023/5/15 23:55
 **/
@Configuration
public class ElasticSearchClientConfig {

    // spring beans -> id = restHighLevelClient  class = RestHighLevelClient
    @Bean
    public RestHighLevelClient restHighLevelClient(){
        RestHighLevelClient client = new RestHighLevelClient(
                RestClient.builder(
                        new HttpHost("localhost", 9200,"http")
                )
        );
        return client;
    }
}
