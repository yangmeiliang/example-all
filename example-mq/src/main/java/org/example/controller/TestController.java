package org.example.controller;

import com.alibaba.fastjson.JSONObject;
import lombok.AllArgsConstructor;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.IntStream;

/**
 * @author yaml
 * @since 2021/8/17
 */
@RestController
@AllArgsConstructor
public class TestController {

    private final RocketMQTemplate rocketMQTemplate;

    /**
     * 测试
     */
    @PostMapping("/mq/push")
    public Object test(@RequestBody JSONObject data) {
        Message<JSONObject> message = MessageBuilder.withPayload(data).build();
        IntStream.range(0, data.getIntValue("count")).forEach(i -> {
            rocketMQTemplate.syncSend("example", message);
        });
        return rocketMQTemplate.syncSend("example", message);
    }
}
