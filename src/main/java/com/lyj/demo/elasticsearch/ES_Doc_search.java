package com.lyj.demo.elasticsearch;

import org.apache.http.HttpHost;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.unit.Fuzziness;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.*;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.aggregations.AggregationBuilder;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.bucket.terms.TermsAggregationBuilder;
import org.elasticsearch.search.aggregations.metrics.MaxAggregationBuilder;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.sort.SortOrder;

import java.io.IOException;
import java.util.Arrays;

/**
 * @program: springelasticSearchdemo
 * @Date: 2021/8/15 8:00
 * @Author: 李玉杰
 * @Description: es 文档查询
 */
public class ES_Doc_search {

    public static void main(String[] args) throws IOException {
        // 创建es客户端连接
        RestHighLevelClient esClient = new RestHighLevelClient(RestClient.builder(new HttpHost("127.0.0.1", 9200, "http")));

        /** ===========全量查询 */
        System.out.println("================全量查询开始===============");
        SearchRequest request = new SearchRequest();
        // 全量查询，该索引的文档
        request.indices("user");
        // 构建搜索条件，全部匹配条件
        SearchSourceBuilder query = new SearchSourceBuilder().query(QueryBuilders.matchAllQuery());
        request.source(query);
        SearchResponse response = esClient.search(request, RequestOptions.DEFAULT);

        System.out.println(response.getHits());
        System.out.println(response.getTook());
        System.out.println(response.getTotalShards());
        SearchHits hits = response.getHits();
        // 遍历查出的数据
        for (SearchHit item : hits) {
            System.out.println(item.getSourceAsString());
        }
        System.out.println("===============条件查询开始===============");
        /** ===========条件查询 */
        SearchRequest request1 = new SearchRequest();
        // 全量查询，该索引的文档
        request1.indices("user");
        // 构建搜索条件，全部匹配条件
        SearchSourceBuilder query1 = new SearchSourceBuilder().query(QueryBuilders.termQuery("name", "三"));
        request1.source(query1);
        SearchResponse response1 = esClient.search(request1, RequestOptions.DEFAULT);

        System.out.println(response1.getHits());
        System.out.println(response1.getTook());
        System.out.println(response1.getTotalShards());
        SearchHits hits1 = response1.getHits();
        // 遍历查出的数据
        for (SearchHit item : hits1) {
            System.out.println(item.getSourceAsString());
        }

        System.out.println("===============分页查询开始===============");
        /** ===========分页查询 */
        SearchRequest request2 = new SearchRequest();
        // 全量查询，该索引的文档
        request.indices("user");
        // 构建搜索条件，全部匹配条件
        SearchSourceBuilder query2 = new SearchSourceBuilder().query(QueryBuilders.matchAllQuery());
        // 设置起始位置 from的值=当前页码值-1
        query2.from(0);
        // 设置每页的数量
        query2.size(2);
        request2.source(query2);
        SearchResponse response2 = esClient.search(request2, RequestOptions.DEFAULT);

        System.out.println(response2.getHits());
        System.out.println(response2.getTook());
        System.out.println(response2.getTotalShards());
        SearchHits hits2 = response2.getHits();
        // 遍历查出的数据
        for (SearchHit item : hits2) {
            System.out.println(item.getSourceAsString());
        }

        System.out.println("===============查询排序开始===============");
        /** ===========查询排序 */
        SearchRequest request3 = new SearchRequest();
        // 全量查询，该索引的文档
        request3.indices("user");
        // 构建搜索条件，全部匹配条件
        SearchSourceBuilder query3 = new SearchSourceBuilder().query(QueryBuilders.matchAllQuery());
        // 设置起始位置 from的值=当前页码值-1
        query3.from(0);
        // 设置每页的数量
        query3.size(2);
        // 按年龄降序
        query3.sort("age", SortOrder.DESC);
        request3.source(query3);
        SearchResponse response3 = esClient.search(request3, RequestOptions.DEFAULT);

        System.out.println(response3.getHits());
        System.out.println(response3.getTook());
        System.out.println(response3.getTotalShards());
        SearchHits hits3 = response3.getHits();
        // 遍历查出的数据
        for (SearchHit item : hits3) {
            System.out.println(item.getSourceAsString());
        }

        System.out.println("===============过滤部分字段查询开始===============");
        /** ===========过滤部分字段查询排序 */
        SearchRequest request4 = new SearchRequest();
        // 全量查询，该索引的文档
        request4.indices("user");
        // 构建搜索条件，全部匹配条件
        SearchSourceBuilder query4 = new SearchSourceBuilder().query(QueryBuilders.matchAllQuery());
        // 设置起始位置 from的值=当前页码值-1
        query4.from(0);
        // 设置每页的数量
        query4.size(2);
        // 按年龄降序
        query4.sort("age", SortOrder.DESC);
        // 输出的字段,这里代表只输出这个字段的值
        String[] includes = {"name"};
        // 不输出的字段，这里代表全部输出，但排除的字段值不会输出
        String[] excludes = {};
        query4.fetchSource(includes, excludes);
        request4.source(query4);
        SearchResponse response4 = esClient.search(request4, RequestOptions.DEFAULT);

        System.out.println(response4.getHits());
        System.out.println(response4.getTook());
        System.out.println(response4.getTotalShards());
        SearchHits hits4 = response4.getHits();
        // 遍历查出的数据
        for (SearchHit item : hits4) {
            System.out.println(item.getSourceAsString());
        }


        System.out.println("===============组合查询开始===============");
        /** ===========组合查询排序 */
        SearchRequest request5 = new SearchRequest();
        // 全量查询，该索引的文档
        request5.indices("user");
        // 构建搜索条件，
        SearchSourceBuilder query5 = new SearchSourceBuilder();
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        // must必须符合该条件 mustnot必须不符合条件，mus组合是与的关系
        boolQueryBuilder.must(QueryBuilders.matchQuery("userName", "zhangsan"));
        boolQueryBuilder.must(QueryBuilders.matchQuery("sex", "女"));
        // should 多个组合是或的关系
//        boolQueryBuilder.should(QueryBuilders.matchQuery("userName", "zhangsan"));
//        boolQueryBuilder.should(QueryBuilders.matchQuery("userName", "lisi"));
        query5.query(boolQueryBuilder);
        request5.source(query5);
        SearchResponse response5 = esClient.search(request5, RequestOptions.DEFAULT);

        System.out.println(response5.getHits());
        System.out.println(response5.getTook());
        System.out.println(response5.getTotalShards());
        SearchHits hits5 = response5.getHits();
        // 遍历查出的数据
        for (SearchHit item : hits5) {
            System.out.println(item.getSourceAsString());
        }

        System.out.println("===============范围查询开始===============");
        /** ===========范围查询排序 */
        SearchRequest request6 = new SearchRequest();
        // 全量查询，该索引的文档
        request6.indices("user");
        // 构建搜索条件，
        SearchSourceBuilder query6 = new SearchSourceBuilder();
        RangeQueryBuilder rangeQuery = QueryBuilders.rangeQuery("age");
        // 大于等于10
        rangeQuery.gte(10);
        // 小于等于20
        rangeQuery.lte(20);


        query6.query(rangeQuery);
        request6.source(query6);
        SearchResponse response6 = esClient.search(request6, RequestOptions.DEFAULT);

        System.out.println(response6.getHits());
        System.out.println(response6.getTook());
        System.out.println(response6.getTotalShards());
        SearchHits hits6 = response6.getHits();
        // 遍历查出的数据
        for (SearchHit item : hits6) {
            System.out.println(item.getSourceAsString());
        }


        System.out.println("===============模糊查询开始===============");
        /** ===========模糊查询排序 */
        SearchRequest request7 = new SearchRequest();
        // 全量查询，该索引的文档
        request7.indices("user");
        // 构建搜索条件，
        SearchSourceBuilder query7 = new SearchSourceBuilder();
        // 模糊查询条件，偏差字符为1个时，也能算匹配成功，比如zhagnsan1，zhangsan2都可以匹配成功，zhangsan12就不能匹配成功
        FuzzyQueryBuilder queryBuilder = QueryBuilders.fuzzyQuery("name", "zhangsan").fuzziness(Fuzziness.ONE);

        query7.query(queryBuilder);
        request7.source(query7);
        SearchResponse response7 = esClient.search(request7, RequestOptions.DEFAULT);

        System.out.println(response7.getHits());
        System.out.println(response7.getTook());
        System.out.println(response7.getTotalShards());
        SearchHits hits7 = response7.getHits();
        // 遍历查出的数据
        for (SearchHit item : hits7) {
            System.out.println(item.getSourceAsString());
        }

        System.out.println("===============高亮查询开始===============");
        /** ===========高亮查询排序 */
        SearchRequest request8 = new SearchRequest();
        // 全量查询，该索引的文档
        request8.indices("user");
        // 构建搜索条件，
        SearchSourceBuilder query8 = new SearchSourceBuilder();
        TermQueryBuilder termQueryBuilder = QueryBuilders.termQuery("name", "zhangsan");
        query8.query(termQueryBuilder);
        HighlightBuilder highlightBuilder = new HighlightBuilder();
        // 高亮的字段
        highlightBuilder.field("name");
        // 设置前置标签
        highlightBuilder.preTags("<font color='red'>");
        // 设置后置标签
        highlightBuilder.postTags("</font>");
        // 高亮显示,设置高亮格式
        query8.highlighter(highlightBuilder);

        request8.source(query8);
        SearchResponse response8 = esClient.search(request8, RequestOptions.DEFAULT);

        System.out.println(response8.getHits());
        System.out.println(response8.getTook());
        System.out.println(response8.getTotalShards());
        SearchHits hits8 = response8.getHits();
        // 遍历查出的数据
        for (SearchHit item : hits8) {
            System.out.println(item.getSourceAsString());
        }

        System.out.println("===============聚合查询开始===============");
        /** ===========聚合查询排序 */
        SearchRequest request9 = new SearchRequest();
        // 全量查询，该索引的文档
        request9.indices("user");
        // 构建搜索条件，
        SearchSourceBuilder query9 = new SearchSourceBuilder();
        // 设置聚合查询条件及输出字段和匹配条件
        MaxAggregationBuilder aggregationBuilder = AggregationBuilders.max("MaxAge").field("age");

        query9.aggregation(aggregationBuilder);

        request9.source(query9);
        SearchResponse response9 = esClient.search(request9, RequestOptions.DEFAULT);

        System.out.println(response9.getHits());
        System.out.println(response9.getTook());
        System.out.println(response9.getTotalShards());
        SearchHits hits9 = response9.getHits();
        // 遍历查出的数据
        for (SearchHit item : hits9) {
            System.out.println(item.getSourceAsString());
        }

        System.out.println("===============分组查询开始===============");
        /** ===========分组查询排序 */
        SearchRequest request10 = new SearchRequest();
        // 全量查询，该索引的文档
        request10.indices("user");
        // 构建搜索条件，
        SearchSourceBuilder query10 = new SearchSourceBuilder();
        // 设置分组查询及输出字段和匹配条件
        TermsAggregationBuilder termsAggregationBuilder = AggregationBuilders.terms("ageGroup").field("age");

        query10.aggregation(termsAggregationBuilder);

        request10.source(query10);
        SearchResponse response10 = esClient.search(request10, RequestOptions.DEFAULT);

        System.out.println(response10.getHits());
        System.out.println(response10.getTook());
        System.out.println(response10.getTotalShards());
        SearchHits hits10 = response10.getHits();
        // 遍历查出的数据
        for (SearchHit item : hits10) {
            System.out.println(item.getSourceAsString());
        }

        // 关闭连接
        esClient.close();

    }
}
