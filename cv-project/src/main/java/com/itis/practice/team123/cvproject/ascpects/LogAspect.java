package com.itis.practice.team123.cvproject.ascpects;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class LogAspect {
    @Before("execution(* com.itis.practice.team123.cvproject.controllers.AdminController.*(..))")
    public void logBefore(JoinPoint joinPoint) {
        log.info(joinPoint.getSignature().toLongString());
        Object[] objects = joinPoint.getArgs();
        for (Object object : objects) {
            log.info("\t" + object.getClass() + " " + object.toString());
        }
    }
}
