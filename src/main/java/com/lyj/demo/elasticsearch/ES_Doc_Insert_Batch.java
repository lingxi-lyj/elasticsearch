package com.lyj.demo.elasticsearch;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.apache.http.HttpHost;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;

import java.io.IOException;
import java.util.Arrays;
import java.util.Date;

/**
 * @program: springelasticSearchdemo
 * @Date: 2021/8/15 8:00
 * @Author: 李玉杰
 * @Description: es 文档批量插入
 */
public class ES_Doc_Insert_Batch {

    public static void main(String[] args) throws IOException {
        // 创建es客户端连接
        RestHighLevelClient esClient = new RestHighLevelClient(RestClient.builder(new HttpHost("127.0.0.1", 9200, "http")));


        BulkRequest request = new BulkRequest();
        request.add(new IndexRequest().index("user").id("1001").source(XContentType.JSON, "name", "张三"));
        request.add(new IndexRequest().index("user").id("1002").source(XContentType.JSON, "name", "张4"));
        request.add(new IndexRequest().index("user").id("1003").source(XContentType.JSON, "name", "张5"));
        request.add(new IndexRequest().index("user").id("1004").source(XContentType.JSON, "name", "张6"));
        BulkResponse response = esClient.bulk(request, RequestOptions.DEFAULT);

        System.out.println(Arrays.toString(response.getItems()));
        System.out.println(response.getTook());
        System.out.println(response.getIngestTook());
        // 关闭连接
        esClient.close();

    }
}
