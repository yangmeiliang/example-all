package org.other;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationStartingEvent;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;

/**
 * @author yaml
 * @since 2022/1/6
 */
@Slf4j
@Configuration
public class TestApplicationListener implements ApplicationListener<ApplicationEvent> {

    @Override
    public void onApplicationEvent(ApplicationEvent event) {
        log.info("TestApplicationListener onApplicationEvent:{}", event);
        System.out.println("TestApplicationListener onApplicationEvent: " + event);
        if(event instanceof ApplicationStartingEvent){
//            ((ApplicationStartingEvent)event).getSpringApplication().setAdditionalProfiles("grey");
        }
    }
}
