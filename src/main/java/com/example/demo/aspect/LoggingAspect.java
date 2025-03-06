package com.example.demo.aspect;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Getter
@Aspect
@Component
@Slf4j
public class LoggingAspect {

  private int counter;

  public void startCounter() {
    counter = 0;
  }

  @Before("execution(* com.example.demo.controller.*.*( .. ))")
  public void logBefore(JoinPoint joinPoint) {
    counter++;
    log.debug("{}.{}", joinPoint.getSignature().getDeclaringTypeName(), joinPoint.getSignature().getName());
  }

  @After("execution(* com.example.demo.controller.*.*( .. ))")
  public void logAfter(JoinPoint joinPoint) {
    counter++;
    log.debug("{}.{}", joinPoint.getSignature().getDeclaringTypeName(), joinPoint.getSignature().getName());
  }
}
