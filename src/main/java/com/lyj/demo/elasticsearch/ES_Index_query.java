package com.lyj.demo.elasticsearch;

import org.apache.http.HttpHost;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.client.indices.GetIndexResponse;

import java.io.IOException;
import java.util.Arrays;

/**
 * @program: springelasticSearchdemo
 * @Date: 2021/8/15 8:00
 * @Author: 李玉杰
 * @Description: es 索引查询
 */
public class ES_Index_query {

    public static void main(String[] args) throws IOException {
        // 创建es客户端连接
        RestHighLevelClient esClient = new RestHighLevelClient(RestClient.builder(new HttpHost("127.0.0.1", 9200, "http")));

        // 创建查询索引请求
        GetIndexRequest request = new GetIndexRequest("user");
        // 通过客户端查询索引
        GetIndexResponse response = esClient.indices().get(request, RequestOptions.DEFAULT);

        System.out.println(response.getAliases());
        System.out.println(Arrays.toString(response.getIndices()));
        System.out.println(response.getSettings());
        // 关闭连接
        esClient.close();

    }
}
