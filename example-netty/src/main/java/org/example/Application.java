package org.example;

import cn.hutool.extra.spring.SpringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;

/**
 * @author yaml
 * @since 2021/4/14
 */
@SpringBootApplication
public class Application {

    private static final Logger log = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
        Environment environment = SpringUtil.getApplicationContext().getEnvironment();
        log.info("test.key01: {}", environment.getProperty("test.key01"));
        log.info("test.key01: {}", environment.getProperty("test.key01"));
        log.info("test.key02: {}", environment.getProperty("test.key02"));
        log.info("test.key03: {}", environment.getProperty("test.key03"));
    }
}
