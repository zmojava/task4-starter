package com.zmo.springboot.logingaspectstarter.aop;

import com.zmo.springboot.logingaspectstarter.LoggingProperties;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;


@Slf4j
@Aspect
public class HttpLoggingAspect {

    @Autowired
    private LoggingProperties properties;

    @Pointcut("within(@org.springframework.web.bind.annotation.RestController *)")
    public void restControllerMethods() {
    }

    @Before("restControllerMethods()")
    public void logRequest(JoinPoint joinPoint) {
        if (properties.isEnabled()) {
            String methodName = joinPoint.getSignature().getName();
            Object[] args = joinPoint.getArgs();
            log("Request", methodName, args);
        }
    }

    @AfterReturning(pointcut = "restControllerMethods()", returning = "result")
    public void logResponse(JoinPoint joinPoint, Object result) {
        if (properties.isEnabled()) {
            String methodName = joinPoint.getSignature().getName();
            log("Response", methodName, result);
        }
    }

    private void log(String type, String methodName, Object data) {
        switch (properties.getLevel().toLowerCase()) {
            case "debug":
                log.debug("{}: Method={}, Data={}", type, methodName, data);
                break;
            case "warn":
                log.warn("{}: Method={}, Data={}", type, methodName, data);
                break;
            case "error":
                log.error("{}: Method={}, Data={}", type, methodName, data);
                break;
            default:
                log.info("{}: Method={}, Data={}", type, methodName, data);
                break;
        }
    }
}