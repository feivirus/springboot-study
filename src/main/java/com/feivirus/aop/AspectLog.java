package com.feivirus.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @author feivirus
 */
@Aspect
@Component
public class AspectLog {

    @Pointcut("execution(* com.feivirus.demo.service.HelloWorldService.*(..))")
    public void logAop() {
        System.out.println("进入logAop");
    }

    @Around("logAop()")
    public void logAround(ProceedingJoinPoint joinPoint) {
        try {
            System.out.println("logAround");
            joinPoint.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }

    @Before("logAop()")
    public void logBefore(JoinPoint joinPoint) {
        System.out.println("logBefore");

    }

}
