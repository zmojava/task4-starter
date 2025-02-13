package com.zmo.springboot.logingaspectstarter;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Slf4j
@Data
@NoArgsConstructor
@ConfigurationProperties( prefix = "http.logging")
public class LoggingProperties {

    private boolean enabled;
    private String level;

}
