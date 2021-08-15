package com.lyj.demo.elasticsearch;

import org.apache.http.HttpHost;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.client.indices.GetIndexResponse;

import java.io.IOException;
import java.util.Arrays;

/**
 * @program: springelasticSearchdemo
 * @Date: 2021/8/15 8:00
 * @Author: 李玉杰
 * @Description: es 索引删除
 */
public class ES_Index_delete {

    public static void main(String[] args) throws IOException {
        // 创建es客户端连接
        RestHighLevelClient esClient = new RestHighLevelClient(RestClient.builder(new HttpHost("127.0.0.1", 9200, "http")));


        DeleteIndexRequest request = new DeleteIndexRequest("user");

        AcknowledgedResponse response = esClient.indices().delete(request, RequestOptions.DEFAULT);

        System.out.println(response.isAcknowledged());
        // 关闭连接
        esClient.close();

    }
}
