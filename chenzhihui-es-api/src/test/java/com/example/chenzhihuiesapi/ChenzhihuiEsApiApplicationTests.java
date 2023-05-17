package com.example.chenzhihuiesapi;

import com.alibaba.fastjson2.JSON;
import com.example.chenzhihuiesapi.pojo.User;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.IndicesClient;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.core.TimeValue;
import org.elasticsearch.search.fetch.subphase.FetchSourceContext;
import org.elasticsearch.xcontent.XContentType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.ArrayList;

@SpringBootTest
class ChenzhihuiEsApiApplicationTests {

    @Autowired
    private RestHighLevelClient restHighLevelClient;

    // 测试索引的创建 Request
    @Test
    void testCreateIndex() throws IOException {
        // 1、创建索引请求
        CreateIndexRequest request = new CreateIndexRequest("chen_index");
        // 2、客户端执行请求 IndicesClient，请求后获得响应
        CreateIndexResponse createIndexResponse
                = restHighLevelClient.indices().create(request, RequestOptions.DEFAULT);
        // 3、输出测试
        System.out.println(createIndexResponse);
    }

    // 测试获取索引
    @Test
    void testExistIndex() throws IOException {
        GetIndexRequest request = new GetIndexRequest("chen_index");
        boolean exists = restHighLevelClient.indices().exists(request, RequestOptions.DEFAULT);
        System.out.println(exists);
    }

    // 删除索引
    @Test
    void testDeleteIndex() throws IOException {
        DeleteIndexRequest request = new DeleteIndexRequest("chen_index");
        AcknowledgedResponse delete = restHighLevelClient.indices().delete(request, RequestOptions.DEFAULT);
        System.out.println(delete.isAcknowledged());
    }

    // 测试添加文档
    @Test
    void testAddDocument() throws IOException {
        // 创建对象
        User user = new User("chenzhihui",3);
        // 创建请求
        IndexRequest request = new IndexRequest("chen_index2");
        // 规则 put /chen_index2/_doc/1
        request.id("1");
        request.timeout(TimeValue.timeValueSeconds(1));
        request.timeout("1s");

        // 将我们的数据放入请求（要以json格式）
        request.source(JSON.toJSONString(user), XContentType.JSON);

        // 客户端发送请求,获取响应的结果
        IndexResponse index = restHighLevelClient.index(request, RequestOptions.DEFAULT);

        System.out.println(index.toString());
        System.out.println(index.status());


    }

    // 获取文档，判断是否存在
    @Test
    void testIsExist() throws IOException {
        GetRequest getRequest = new GetRequest("chen_index2", "1");
        getRequest.fetchSourceContext(new FetchSourceContext(false));
        getRequest.storedFields("_none_");

        boolean exists = restHighLevelClient.exists(getRequest, RequestOptions.DEFAULT);
        System.out.println(exists);

    }

    // 获得文档信息
    @Test
    void testGetDocument() throws IOException {
        GetRequest getRequest = new GetRequest("chen_index2", "1");
        GetResponse getResponse = restHighLevelClient.get(getRequest, RequestOptions.DEFAULT);
        System.out.println(getResponse.getSourceAsString());
        System.out.println(getResponse);
    }

    // 更新文档
    @Test
    void testUpdateDocument() throws IOException {
        UpdateRequest updateRequest = new UpdateRequest("chen_index2", "1");
        updateRequest.timeout("1s");

        User user = new User("chenzhihuiaha", 111);
        updateRequest.doc(JSON.toJSONString(user),XContentType.JSON);

        UpdateResponse updateResponse = restHighLevelClient.update(updateRequest, RequestOptions.DEFAULT);
        System.out.println(updateResponse.status());
        System.out.println(updateResponse.toString());
    }

    // 删除文档记录
    @Test
    void testDeleteDocument() throws IOException {
        DeleteRequest deleteRequest = new DeleteRequest("chen_index2", "1");
        deleteRequest.timeout("1s");

        DeleteResponse deleteResponse = restHighLevelClient.delete(deleteRequest, RequestOptions.DEFAULT);
        System.out.println(deleteResponse.status());
    }

    // 批量插入数据
    @Test
    void testBulkRequest() throws IOException {
        BulkRequest bulkRequest = new BulkRequest();
        bulkRequest.timeout("10s");

        ArrayList<User> userList = new ArrayList<>();

        userList.add(new User("111",1));
        userList.add(new User("222",2));
        userList.add(new User("333",3));
        userList.add(new User("444",4));
        userList.add(new User("555",5));
        userList.add(new User("666",6));
        userList.add(new User("777",7));
        userList.add(new User("888",8));

        for (int i = 0; i < userList.size(); i++) {
            bulkRequest.add(new IndexRequest("chen_index")
                    .id(""+i+1)
                    .source(JSON.toJSONString(userList.get(i)), XContentType.JSON)
            );
        }

        BulkResponse bulkResponse = restHighLevelClient.bulk(bulkRequest, RequestOptions.DEFAULT);
        System.out.println(bulkResponse.hasFailures());


    }


}
