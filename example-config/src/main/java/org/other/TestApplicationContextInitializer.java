package org.other;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.ConfigurableEnvironment;

/**
 * @author yaml
 * @since 2022/1/6
 */
@Slf4j
@Configuration
public class TestApplicationContextInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {

    @Override
    public void initialize(ConfigurableApplicationContext applicationContext) {
        log.info("TestApplicationContextInitializer initialize...");
        System.out.println("TestApplicationContextInitializer initialize...");
        ConfigurableEnvironment environment = applicationContext.getEnvironment();
        String[] activeProfiles = environment.getActiveProfiles();

        environment.setActiveProfiles();
    }
}
