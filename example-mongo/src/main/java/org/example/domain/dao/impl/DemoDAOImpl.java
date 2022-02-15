package org.example.domain.dao.impl;

import com.alibaba.fastjson.JSON;
import com.mongodb.client.result.UpdateResult;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.domain.dao.DemoDAO;
import org.example.domain.entity.DemoEntity;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * @author yaml
 * @since 2022/1/22
 */
@Slf4j
@Component
@AllArgsConstructor
public class DemoDAOImpl implements DemoDAO {

    private final MongoTemplate mongoTemplate;

    @Override
    public void saveDemo(DemoEntity demoEntity) {
        DemoEntity save = mongoTemplate.save(demoEntity);
        log.info("文档保存返回：{}", JSON.toJSONString(save));
    }

    @Override
    public void removeDemo(Long id) {
        mongoTemplate.remove(id);
    }

    @Override
    public void updateDemo(DemoEntity demoEntity) {
        Query query = new Query(Criteria.where("id").is(demoEntity.getId()));
        Update update = new Update();
        JSON.parseObject(JSON.toJSONString(demoEntity)).getInnerMap()
                .entrySet().stream()
                .filter(o -> !Objects.equals(o.getKey(), "id"))
                .forEach(entry -> update.set(entry.getKey(), entry.getValue()));
        UpdateResult updateResult = mongoTemplate.updateFirst(query, update, DemoEntity.class);
        log.info("文档更新返回：{}", JSON.toJSONString(updateResult));
    }

    @Override
    public DemoEntity findDemoById(Long id) {
        return mongoTemplate.findById(id, DemoEntity.class);
    }
}
