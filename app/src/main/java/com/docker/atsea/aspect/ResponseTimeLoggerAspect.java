package com.docker.atsea.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import com.docker.atsea.util.RequestContextHolder;


@Aspect
@Component
public class ResponseTimeLoggerAspect {

    private static final Logger LOGGER = LoggerFactory.getLogger(ResponseTimeLoggerAspect.class);

    @After("execution(* com.docker.atsea.controller.*.*(..))")
    public void logResponseTime(JoinPoint joinPoint) {
        long elapsedTime = System.currentTimeMillis() - RequestContextHolder.getRequestTime();
        RequestContextHolder.clearRequestTime();
        LOGGER.info("Response Time for {} took {} ms", joinPoint.getSignature().toShortString(), elapsedTime);
    }
}
