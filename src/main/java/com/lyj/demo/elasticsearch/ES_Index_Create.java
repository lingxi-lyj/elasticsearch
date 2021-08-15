package com.lyj.demo.elasticsearch;

import org.apache.http.HttpHost;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;

import java.io.IOException;

/**
 * @program: springelasticSearchdemo
 * @Date: 2021/8/15 8:00
 * @Author: 李玉杰
 * @Description: es 索引创建
 */
public class ES_Index_Create {

    public static void main(String[] args) throws IOException {
        // 创建es客户端连接
        RestHighLevelClient esClient = new RestHighLevelClient(RestClient.builder(new HttpHost("127.0.0.1", 9200, "http")));

        // 创建索引请求
        CreateIndexRequest request = new CreateIndexRequest("user");
        // 通过客户端创建索引
        CreateIndexResponse response = esClient.indices().create(request, RequestOptions.DEFAULT);
        // 是否响应成功
        System.out.println(response.isAcknowledged());
        System.out.println(response.toString());
        // 关闭连接
        esClient.close();

    }
}
