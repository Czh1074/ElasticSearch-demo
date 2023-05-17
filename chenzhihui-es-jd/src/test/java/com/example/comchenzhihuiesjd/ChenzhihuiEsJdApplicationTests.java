package com.example.comchenzhihuiesjd;

import org.elasticsearch.action.admin.indices.create.CreateIndexRequest;
import org.elasticsearch.action.admin.indices.create.CreateIndexResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

@SpringBootTest
class ChenzhihuiEsJdApplicationTests {

    @Test
    void contextLoads() {
    }

    @Autowired
    private RestHighLevelClient restHighLevelClient;

    // 测试索引的创建 Request
    @Test
    void testCreateIndex() throws IOException {
        // 1、创建索引请求
        CreateIndexRequest request = new CreateIndexRequest("jd_goods");
        // 2、客户端执行请求 IndicesClient，请求后获得响应
        CreateIndexResponse createIndexResponse
                = restHighLevelClient.indices().create(request, RequestOptions.DEFAULT);
        // 3、输出测试
        System.out.println(createIndexResponse);
    }
}
