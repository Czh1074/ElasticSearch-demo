# ElasticSearch-demo
描述：主要实现使用如何使用elasticsearch实现信息构建、信息检索、检索信息高亮的实践操作。
# 前期准备
## 1、ElasticSearch版本7.17.0
1. 下载、解压 -> 安装完成
2. 熟悉目录
```
1、bin 目录文件
2、config 配置文件
	log4j2 日志配置文件
	jvm.options Java虚拟机配置文件
  elasticsearch.yaml elasticsearch配置文件 -> 默认9200端口，跨域也是这边解决！
3、lib 相关jar包
4、modules 功能模块
5、plugins 插件！ik分词器
```
3. 启动
​	http://localhost:9200/

> 4. 安装可视化界面es head插件

 
- 下载地址： https://github.com/mobz/elasticsearch-head/
- cd elasticsearch-hand-master      
- npm install
- npm run start

> 5. 安装kibana

- 下载
- 进入 bin目录
- 运行：./kibana (注意：需要在elasticsearch已经启动的情况下，才不会报错)
- 测试：开发工具 http://localhost:5621/

## 2、SpringBoot集成ES

1. 官网 -> Java REST Client -> 高级客户端

2. 找到原生maven依赖

```xml
<!-- https://mvnrepository.com/artifact/org.elasticsearch/elasticsearch -->
<dependency>
    <groupId>org.elasticsearch</groupId>
    <artifactId>elasticsearch</artifactId>
    <version>7.17.0</version>
</dependency>
```

3. 找对象

4. 分析类中的方法即可！

###  2.1、创建空项目并初始化

1. 保证springboot的项目版本和es的版本一致

2. 构建一个对象 

###  2.2、测试

> 创建索引

1. 创建索引

```java
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
```

2. 测试是否能创建成功

```java
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
```

> 测试获取索引

```java
// 测试获取索引
@Test
void testExistIndex() throws IOException {
  GetIndexRequest request = new GetIndexRequest("chen_index");
  boolean exists = restHighLevelClient.indices().exists(request, RequestOptions.DEFAULT);
  System.out.println(exists);
}
```
### 3、JD实战项目小demo
1. 具体的参看上面的代码部分
2. 创建Maven项目
3. 导入相关依赖，查看pom.xml文件
4. 具体代码看src部分




