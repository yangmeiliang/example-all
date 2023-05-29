package org.example.service;

import com.alibaba.fastjson.JSONObject;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * @author yaml
 * @since 2022/2/16
 */
@Slf4j
@Service
@AllArgsConstructor
public class DocService {

    private final RestHighLevelClient client;

    public void insertDoc() throws IOException {
        IndexRequest request = new IndexRequest();
        request.index("user").id("1");
        JSONObject user = new JSONObject();
        user.put("id", 1);
        user.put("name", "张三");
        user.put("age", 18);
        user.put("sex", "男");
        request.source(user.toJSONString(), XContentType.JSON);
        IndexResponse indexResponse = client.index(request, RequestOptions.DEFAULT);
        log.info("Result===>{}", indexResponse.getResult());
    }


}
