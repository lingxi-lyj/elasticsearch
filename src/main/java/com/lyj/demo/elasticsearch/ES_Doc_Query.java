package com.lyj.demo.elasticsearch;

import org.apache.http.HttpHost;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;

import java.io.IOException;

/**
 * @program: springelasticSearchdemo
 * @Date: 2021/8/15 8:00
 * @Author: 李玉杰
 * @Description: es 文档查询
 */
public class ES_Doc_Query {

    public static void main(String[] args) throws IOException {
        // 创建es客户端连接
        RestHighLevelClient esClient = new RestHighLevelClient(RestClient.builder(new HttpHost("127.0.0.1", 9200, "http")));


        GetRequest request = new GetRequest();
        request.index("shopping").id("1002");

        GetResponse response = esClient.get(request, RequestOptions.DEFAULT);

        System.out.println(response.getSourceAsString());
        System.out.println(response.getFields());
        System.out.println(response.getIndex());
        // 关闭连接
        esClient.close();

    }
}
