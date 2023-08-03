package com.yazukov.logrouter.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

@Configuration
@Aspect
public class AspectLogging {
    private static final Logger log = LoggerFactory.getLogger(AspectLogging.class);

    @Before(value = "within(com.yazukov.logrouter.repository.*)")
    public void loggingStatementBeforeRepo(JoinPoint joinPoint){
        log.info("Executing {}", joinPoint);
    }

    @After(value = "within(com.yazukov.logrouter.repository.*)")
    public void loggingStatementAfterRepo(JoinPoint joinPoint){
        log.info("Complete {}", joinPoint);
    }

    @Before(value = "within(com.yazukov.logrouter.service.*)")
    public void loggingStatementBeforeService(JoinPoint joinPoint){
        log.info("Executing {}", joinPoint);
    }

    @After(value = "within(com.yazukov.logrouter.service.*)")
    public void loggingStatementAfterService(JoinPoint joinPoint){
        log.info("Complete {}", joinPoint);
    }

    @Around(value = "execution(* com.yazukov.logrouter.service.*.*(..))")
    public Object exceptionHandlingService(ProceedingJoinPoint joinPoint) throws Throwable{
        try {
            Object obj = joinPoint.proceed();
            return obj;
        } catch (Exception ex){
            log.info("Exception message {}", ex.getMessage());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ex.getMessage());
        }
    }
}
