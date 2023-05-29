package org.example.rocketmq;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;

/**
 * @author yaml
 * @since 2022/3/7
 */
@Slf4j
@Component
@RocketMQMessageListener(topic = "example", consumerGroup = "example")
public class ExampleConsumer implements RocketMQListener<Object> {

    @Override
    public void onMessage(Object o) {
        log.info(JSON.toJSONString(o));
    }
}
