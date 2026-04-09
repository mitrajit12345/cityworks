package com.cityworks.citizen_service.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class LoggingAspect {
    @Before("execution(* com.cityworks.citizen_service.service.*.*(..))")
    public void logBefore(JoinPoint joinPoint){
        log.info("Entering: {}", joinPoint.getSignature());
    }

    @AfterReturning(value = "execution(* com.cityworks.citizen_service.service.*.*(..))", returning = "result")
    public void logAfter(JoinPoint joinPoint, Object result){
        log.info("Exiting: {} Result: {}", joinPoint.getSignature(), result);
    }

    @AfterThrowing(value = "execution(* com.cityworks.citizen_service.service.*.*(..))", throwing = "ex")
    public void logError(JoinPoint joinPoint, Exception ex){
        log.error("Exception in {}: {}", joinPoint.getSignature(), ex.getMessage());
    }
}
