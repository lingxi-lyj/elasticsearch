package com.lyj.demo.elasticsearch;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpHost;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;

import java.io.IOException;
import java.util.Date;

/**
 * @program: springelasticSearchdemo
 * @Date: 2021/8/15 8:00
 * @Author: 李玉杰
 * @Description: es 文档修改
 */
public class ES_Doc_Update {

    public static void main(String[] args) throws IOException {
        // 创建es客户端连接
        RestHighLevelClient esClient = new RestHighLevelClient(RestClient.builder(new HttpHost("127.0.0.1", 9200, "http")));


        UpdateRequest request = new UpdateRequest();
        request.index("shopping").id("1002");
        // 局部修改
        request.doc(XContentType.JSON, "sex", "女");
        UpdateResponse response = esClient.update(request, RequestOptions.DEFAULT);

        System.out.println(response.getResult());
        // 关闭连接
        esClient.close();

    }
}
