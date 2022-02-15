package org.other;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.env.EnvironmentPostProcessor;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.ConfigurableEnvironment;

/**
 * @author yaml
 * @since 2022/1/6
 */
@Slf4j
@Configuration
public class TestEnvironmentPostProcessor implements EnvironmentPostProcessor {
    @Override
    public void postProcessEnvironment(ConfigurableEnvironment environment, SpringApplication application) {
        log.info("TestEnvironmentPostProcessor initialize...");
        System.out.println("TestEnvironmentPostProcessor initialize...");
    }
}
