# ElasticSearch-demo
描述：主要实现使用如何使用elasticsearch实现信息构建、信息检索、检索信息高亮的实践操作。
# 前期准备
## 1、ElasticSearch版本7.17.0
1、下载、解压 -> 安装完成
2、熟悉目录
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
3、启动
​	http://localhost:9200/
> 4、安装可视化界面es head插件
1、下载地址： https://github.com/mobz/elasticsearch-head/
2、cd elasticsearch-hand-master      
3、npm install
4、npm run start
> 5、安装kibana
1、下载
2、进入 bin目录
3、运行：./kibana (注意：需要在elasticsearch已经启动的情况下，才不会报错)
4、测试：开发工具 http://localhost:5621/




