package org.example.service;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.example.BaseTest;
import org.example.domain.entity.User;
import org.junit.Test;

import javax.annotation.Resource;
import java.io.IOException;

/**
 * @author yaml
 * @since 2022/2/16
 */
@Slf4j
public class DocServiceTest extends BaseTest {

    @Resource
    private RestHighLevelClient client;

    @Test
    public void insertDoc() throws IOException {
        IndexRequest request = new IndexRequest();
        request.index("user").id("1");
        User user = new User().setId(1).setNickname("张三").setAge(18).setSex("男");
        request.source(JSON.toJSONString(user), XContentType.JSON);
        IndexResponse indexResponse = client.index(request, RequestOptions.DEFAULT);
        log.info("Result===>{}", indexResponse.getResult());
    }

    @Test
    public void selectDoc() throws IOException {
        GetRequest request = new GetRequest();
        request.index("user").id("1");
        GetResponse documentFields = client.get(request, RequestOptions.DEFAULT);
        log.info("===>{}", documentFields.getSourceAsString());

    }

    @Test
    public void deleteDoc() throws IOException {
        DeleteRequest request = new DeleteRequest();
        request.index("user").id("1");
        DeleteResponse response = client.delete(request, RequestOptions.DEFAULT);
        log.info("===>{}", response.toString());
    }


    @Test
    public void bulkInsertDoc() throws IOException {
        BulkRequest request = new BulkRequest();
        ObjectMapper mapper = new ObjectMapper();
        User user1 = new User().setId(1).setNickname("张三").setAge(18).setSex("男");
        User user2 = new User().setId(2).setNickname("李四").setAge(17).setSex("女");
        User user3 = new User().setId(3).setNickname("王五").setAge(16).setSex("女");

        request.add(new IndexRequest().index("user").id("1").source(mapper.writeValueAsString(user1), XContentType.JSON));
        request.add(new IndexRequest().index("user").id("2").source(mapper.writeValueAsString(user2), XContentType.JSON));
        request.add(new IndexRequest().index("user").id("3").source(mapper.writeValueAsString(user3), XContentType.JSON));

        BulkResponse response = client.bulk(request, RequestOptions.DEFAULT);
        log.info("Took===>{}", response.getTook());
        log.info("Items===>{}", response.getItems());
    }

    @Test
    public void bulkDeleteDoc() throws IOException {
        BulkRequest request = new BulkRequest();

        request.add(new DeleteRequest().index("user").id("1"));
        request.add(new DeleteRequest().index("user").id("2"));
        request.add(new DeleteRequest().index("user").id("3"));

        BulkResponse response = client.bulk(request, RequestOptions.DEFAULT);
        log.info("Took===>{}", response.getTook());
        log.info("Items===>{}", response.getItems());
    }


}