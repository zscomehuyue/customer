package com.sxf.cps.customer.infrastructure.config;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Service;

import static com.sxf.cps.customer.infrastructure.util.LogUtils.error;
import static com.sxf.cps.customer.infrastructure.util.LogUtils.info;


@Aspect
@Service
@Slf4j
public class LogAop {

    @Pointcut("execution(public * com.sxf.cps.customer.domain.merchant.service..*.*(..)) || " +
            "execution(public * com.sxf.cps.customer.domain..*.*(..)) || " +
            "execution(public * com.sxf.cps.customer.resources.façade..*.*(..))) ")
    public void pointcut() {
    }

    @Around("pointcut()")
    public Object around(ProceedingJoinPoint pj) throws Throwable {
        MethodSignature methodSignature = (MethodSignature) pj.getSignature();
        String name = pj.getTarget().getClass().getSimpleName();
        String methtLogName = name + "." + methodSignature.getMethod().getName();
        info(()->"=" + methtLogName + "=>");
        try {
            return pj.proceed();
        } catch (Throwable e) {
            e.printStackTrace();
            error(()->"="+methtLogName+"=>errors:", e);
            throw e;
        }
    }


}
