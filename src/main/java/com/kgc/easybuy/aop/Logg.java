package com.kgc.easybuy.aop;


import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * @author daidai
 */
@Aspect
@Component


public class Logg {
    private Logger log = LoggerFactory.getLogger(Logg.class);
    @Around(value = "execution(* com.kgc.easybuy.controller..*.*(..))||execution(* com.kgc.easybuy.service..*.*(..))")
    public Object around(ProceedingJoinPoint joinPoint){
        log.info("method:"+joinPoint.getSignature().getName()+"target:"+joinPoint.getTarget()+"params:"+ Arrays.toString(joinPoint.getArgs()));
        Object result;
        try {
            result=joinPoint.proceed();
            log.debug("method:"+joinPoint.getSignature().getName()+"target:"+joinPoint.getTarget()+"params:"+ Arrays.toString(joinPoint.getArgs())+"result"+result );
            return  result;
        } catch (Throwable e) {
            log.error("method:"+joinPoint.getSignature().getName()+"target:"+joinPoint.getTarget()+"params:"+ Arrays.toString(joinPoint.getArgs())+"e"+e,e );
            throw new RuntimeException(e);
        }

    }
}
