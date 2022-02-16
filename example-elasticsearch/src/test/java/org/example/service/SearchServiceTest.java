package org.example.service;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.unit.Fuzziness;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.RangeQueryBuilder;
import org.elasticsearch.index.query.TermsQueryBuilder;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.aggregations.AggregationBuilder;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.metrics.MaxAggregationBuilder;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.example.BaseTest;
import org.junit.Test;

import javax.annotation.Resource;

/**
 * @author yaml
 * @since 2022/2/16
 */
@Slf4j
public class SearchServiceTest extends BaseTest {

    @Resource
    private RestHighLevelClient client;

    /**
     * {
     * "query": {
     * "match_all": {}
     * }
     * }
     */
    @Test
    public void searchDoc() throws Exception {
        SearchRequest request = new SearchRequest();
        request.indices("user");
        request.source(new SearchSourceBuilder().query(QueryBuilders.matchAllQuery()));
        SearchResponse response = client.search(request, RequestOptions.DEFAULT);

        SearchHits hits = response.getHits();

        log.info("TotalHits===>{}", hits.getTotalHits());
        log.info("Took===>{}", response.getTook());

        hits.forEach(h -> {
            log.info("===>" + h.getSourceAsString());
        });
    }

    /**
     * {
     * "query": {
     * "term": {
     * "age": {
     * "value": "18"
     * }
     * }
     * }
     * }
     */
    @Test
    public void searchQueryDoc() throws Exception {
        SearchRequest request = new SearchRequest();
        request.indices("user");
        request.source(new SearchSourceBuilder().query(QueryBuilders.termQuery("age", 18)));
        SearchResponse response = client.search(request, RequestOptions.DEFAULT);

        SearchHits hits = response.getHits();

        log.info("TotalHits===>{}", hits.getTotalHits());
        log.info("Took===>{}", response.getTook());

        hits.forEach(h -> {
            log.info("===>" + h.getSourceAsString());
        });

    }

    /**
     * {
     * "query": {
     * "match_all": {}
     * },
     * "from": 0,
     * "size": 2
     * }
     */
    @Test
    public void searchPageDoc() throws Exception {
        SearchRequest request = new SearchRequest();
        request.indices("user");
        SearchSourceBuilder builder = new SearchSourceBuilder().query(QueryBuilders.matchAllQuery());
        builder.from(0);
        builder.size(2);
        request.source(builder);
        SearchResponse response = client.search(request, RequestOptions.DEFAULT);

        SearchHits hits = response.getHits();

        log.info("TotalHits===>{}", hits.getTotalHits());
        log.info("Took===>{}", response.getTook());

        hits.forEach(h -> {
            log.info("===>" + h.getSourceAsString());
        });

    }

    /**
     * {
     * "query": {
     * "match_all": {}
     * },
     * "sort": [
     * {
     * "age": {
     * "order": "asc"
     * }
     * }
     * ]
     * }
     */
    @Test
    public void searchOrderDoc() throws Exception {
        SearchRequest request = new SearchRequest();
        request.indices("user");
        SearchSourceBuilder builder = new SearchSourceBuilder().query(QueryBuilders.matchAllQuery());
        builder.sort("age", SortOrder.ASC);
        request.source(builder);
        SearchResponse response = client.search(request, RequestOptions.DEFAULT);

        SearchHits hits = response.getHits();

        log.info("TotalHits===>{}", hits.getTotalHits());
        log.info("Took===>{}", response.getTook());

        hits.forEach(h -> {
            log.info("===>" + h.getSourceAsString());
        });

    }

    /**
     * {
     * "query": {
     * "match_all": {}
     * },
     * "sort": [
     * {
     * "age": {
     * "order": "asc"
     * }
     * }
     * ],
     * "_source": {
     * "includes": [],
     * "excludes":["sex"]
     * }
     * }
     */
    @Test
    public void searchSourceDoc() throws Exception {
        SearchRequest request = new SearchRequest();
        request.indices("user");
        SearchSourceBuilder builder = new SearchSourceBuilder().query(QueryBuilders.matchAllQuery());
        builder.sort("age", SortOrder.ASC);
        String[] excludes = {"sex"};
        String[] includes = {};
        builder.fetchSource(includes, excludes);
        request.source(builder);
        SearchResponse response = client.search(request, RequestOptions.DEFAULT);

        SearchHits hits = response.getHits();

        log.info("TotalHits===>{}", hits.getTotalHits());
        log.info("Took===>{}", response.getTook());

        hits.forEach(h -> {
            log.info("===>" + h.getSourceAsString());
        });

    }

    /**
     * {
     * "query": {
     * "bool": {
     * "must": [
     * {
     * "match": {
     * "age": 18
     * }
     * }
     * ],
     * "must_not": [
     * {
     * "match": {
     * "sex": "女"
     * }
     * }
     * ]
     * }
     * }
     * }
     */
    @Test
    public void searchBoolDoc() throws Exception {
        SearchRequest request = new SearchRequest();
        request.indices("user");
        SearchSourceBuilder builder = new SearchSourceBuilder();

        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        boolQueryBuilder.must(QueryBuilders.matchQuery("age", 18));
        boolQueryBuilder.mustNot(QueryBuilders.matchQuery("sex", "女"));

        builder.query(boolQueryBuilder);

        request.source(builder);
        SearchResponse response = client.search(request, RequestOptions.DEFAULT);

        SearchHits hits = response.getHits();

        log.info("TotalHits===>{}", hits.getTotalHits());
        log.info("Took===>{}", response.getTook());

        hits.forEach(h -> {
            log.info("===>" + h.getSourceAsString());
        });

    }

    /**
     * {
     * "query": {
     * "range": {
     * "age": {
     * "gte": 16,
     * "lte": 17
     * }
     * }
     * }
     * }
     */
    @Test
    public void searchRangeDoc() throws Exception {
        SearchRequest request = new SearchRequest();
        request.indices("user");
        SearchSourceBuilder builder = new SearchSourceBuilder();

        RangeQueryBuilder rangeQuery = QueryBuilders.rangeQuery("age");
        rangeQuery.gte(16);
        rangeQuery.lte(17);
        builder.query(rangeQuery);

        request.source(builder);
        SearchResponse response = client.search(request, RequestOptions.DEFAULT);

        SearchHits hits = response.getHits();

        log.info("TotalHits===>{}", hits.getTotalHits());
        log.info("Took===>{}", response.getTook());

        hits.forEach(h -> {
            log.info("===>" + h.getSourceAsString());
        });

    }

    @Test
    public void searchFuzzyDoc() throws Exception {
        SearchRequest request = new SearchRequest();
        request.indices("user");
        SearchSourceBuilder builder = new SearchSourceBuilder();
        builder.query(QueryBuilders.fuzzyQuery("name", "李六").fuzziness(Fuzziness.ONE));
        //.fuzziness(Fuzziness.ONE)匹配多少个

        request.source(builder);
        SearchResponse response = client.search(request, RequestOptions.DEFAULT);

        SearchHits hits = response.getHits();

        log.info("TotalHits===>{}", hits.getTotalHits());
        log.info("Took===>{}", response.getTook());

        hits.forEach(h -> {
            log.info("===>" + h.getSourceAsString());
        });
    }

    /**
     * 高亮查询
     * {
     * "query": {
     * "terms": {
     * "name": [
     * "金"
     * ]
     * }
     * },
     * "highlight": {
     * "fields": {
     * "name": {
     * "pre_tags": [
     * "<font color='red'>"
     * ],
     * "post_tags": [
     * "</font>"
     * ]
     * }
     * }
     * }
     * }
     */
    @Test
    public void searchHighlighterDoc() throws Exception {
        SearchRequest request = new SearchRequest();
        request.indices("user");
        SearchSourceBuilder builder = new SearchSourceBuilder();
        TermsQueryBuilder termQueryBuilder = QueryBuilders.termsQuery("name", "张");

        HighlightBuilder highlightBuilder = new HighlightBuilder();
        highlightBuilder.preTags("<font color='red'>");
        highlightBuilder.postTags("</font>");
        highlightBuilder.field("name");

        builder.highlighter(highlightBuilder);
        builder.query(termQueryBuilder);


        request.source(builder);
        SearchResponse response = client.search(request, RequestOptions.DEFAULT);

        SearchHits hits = response.getHits();

        log.info("TotalHits===>{}", hits.getTotalHits());
        log.info("Took===>{}", response.getTook());

        hits.forEach(h -> {
            log.info("===>" + h);
        });
    }

    /**
     * {
     * "aggs": {
     * "ageMax": {
     * "max": {
     * "field": "age"
     * }
     * }
     * }
     * }
     */
    @Test
    public void searchAggregationDoc() throws Exception {
        SearchRequest request = new SearchRequest();
        request.indices("user");
        SearchSourceBuilder builder = new SearchSourceBuilder();

        MaxAggregationBuilder aggregationBuilder = AggregationBuilders.max("maxAge").field("age");

        builder.aggregation(aggregationBuilder);

        request.source(builder);
        SearchResponse response = client.search(request, RequestOptions.DEFAULT);

        SearchHits hits = response.getHits();

        log.info("TotalHits===>{}", hits.getTotalHits());
        log.info("Took===>{}", response.getTook());
        log.info("Aggregations===>{}", JSON.toJSONString(response.getAggregations(), true));
    }

    /**
     * {
     * "aggs": {
     * "age_group": {
     * "terms": {
     * "field": "age"
     * }
     * }
     * }
     * }
     */
    @Test
    public void searchAggregationGroupDoc() throws Exception {
        SearchRequest request = new SearchRequest();
        request.indices("user");
        SearchSourceBuilder builder = new SearchSourceBuilder();

        AggregationBuilder aggregationBuilder = AggregationBuilders.terms("ageGroup").field("age");

        builder.aggregation(aggregationBuilder);

        request.source(builder);
        SearchResponse response = client.search(request, RequestOptions.DEFAULT);

        SearchHits hits = response.getHits();

        log.info("TotalHits===>{}", hits.getTotalHits());
        log.info("Took===>{}", response.getTook());
        log.info("Aggregations===>{}", JSON.toJSONString(response.getAggregations(), true));
    }


}