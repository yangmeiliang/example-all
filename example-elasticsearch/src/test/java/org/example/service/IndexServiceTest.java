package org.example.service;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.client.indices.GetIndexResponse;
import org.elasticsearch.cluster.metadata.AliasMetadata;
import org.elasticsearch.cluster.metadata.MappingMetadata;
import org.elasticsearch.common.settings.Settings;
import org.example.BaseTest;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @author yaml
 * @since 2022/2/16
 */
@Slf4j
public class IndexServiceTest extends BaseTest {

    @Resource
    private RestHighLevelClient client;

    @Test
    public void createIndex() throws Exception {
        CreateIndexRequest request = new CreateIndexRequest("user");
        request.settings();
        CreateIndexResponse createIndexResponse = client.indices().create(request, RequestOptions.DEFAULT);
        boolean acknowledged = createIndexResponse.isAcknowledged();
        log.info("索引操作===>{}", acknowledged);
    }

    @Test
    public void selectIndex() throws Exception {
        GetIndexRequest request = new GetIndexRequest("user");
        GetIndexResponse getIndexResponse = client.indices().get(request, RequestOptions.DEFAULT);
        Map<String, List<AliasMetadata>> aliases = getIndexResponse.getAliases();
        Map<String, MappingMetadata> mappings = getIndexResponse.getMappings();
        Map<String, Settings> settings = getIndexResponse.getSettings();
        log.info("aliases===>{}", JSON.toJSONString(aliases, true));
        log.info("mappings===>{}", JSON.toJSONString(mappings, true));
        log.info("settings===>{}", settings);
    }

    @Test
    public void deleteIndex() throws Exception {
        DeleteIndexRequest request = new DeleteIndexRequest("user");
        AcknowledgedResponse acknowledgedResponse = client.indices().delete(request, RequestOptions.DEFAULT);
        log.info("索引操作===>{}", acknowledgedResponse.isAcknowledged());
    }
}