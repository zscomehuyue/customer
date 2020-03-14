package com.sxf.cps.customer.infrastructure.config;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Service;


@Aspect
@Service
@Slf4j
public class LogAop {

    @Pointcut("execution(public * com.sxf.cps.customer.domain.merchant.service..*.*(..)))")
    public void pointcut() {
    }

    @Around("pointcut()")
    public Object around(ProceedingJoinPoint pj) throws Throwable {
        MethodSignature methodSignature = (MethodSignature) pj.getSignature();
        String name = pj.getTarget().getClass().getSimpleName();
        String methtLogName = name + "." + methodSignature.getMethod().getName();
        log.info("=" + methtLogName + "=>");
        try {
            return pj.proceed();
        } catch (Throwable e) {
            e.printStackTrace();
            log.error("=around=>" + methtLogName + " ,errors:", e);
            throw e;
        }
    }


}
