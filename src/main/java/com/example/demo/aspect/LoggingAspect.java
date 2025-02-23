package com.example.demo.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.Instant;

@Aspect
@Component
@Slf4j
public class LoggingAspect {

  @Before("execution(* com.example.demo.controller.*.*(..))")
  public void logBefore(JoinPoint joinPoint) {
    System.out.println(joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
    log.debug("{}.{}", joinPoint.getSignature().getDeclaringTypeName(), joinPoint.getSignature().getName());
  }

  @Around("execution(* com.example.demo.controller.*.*(..))")
  public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
    Instant start = Instant.now();
    Object result = joinPoint.proceed();
    Instant end = Instant.now();
    String duration = Duration.between(start, end).toMillis() + "ml";
    log.debug(duration);
    System.out.println(duration);
    return result;
  }
}
