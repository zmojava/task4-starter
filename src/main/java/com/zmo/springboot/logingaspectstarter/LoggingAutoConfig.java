package com.zmo.springboot.logingaspectstarter;

import com.zmo.springboot.logingaspectstarter.aop.HttpLoggingAspect;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
@EnableConfigurationProperties(LoggingProperties.class)
@ConditionalOnProperty(prefix = "http.logging", name = "enabled", havingValue = "true")
public class LoggingAutoConfig {

    @Bean
    public HttpLoggingAspect httpLoggingAspect(){
        return new HttpLoggingAspect();
    }

}
