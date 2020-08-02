package com.chen.study.log;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;


@Component
@Aspect
public class AopLog {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    // 线程局部变量，用于解决多线程变量冲突的问题
    ThreadLocal<Long> startTime = new ThreadLocal<>();

    // 定义切入点
    @Pointcut("execution(public * com.chen..*.*(..))")
    public void aopWebLog(){
    }

    @Before(("aopWebLog()"))
    public void doBefore(JoinPoint joinPoint) throws Exception{
        startTime.set(System.currentTimeMillis());
        // 接收到的请求
        ServletRequestAttributes attributes =
                (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        // 记录下请求内容
        logger.info("URL:" + request.getRequestURL().toString());
        logger.info("HTTP方法：" + request.getMethod());
        logger.info("IP地址：" + request.getRemoteAddr());
        logger.info("类的方法：" + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
        logger.info("参数：" + request.getQueryString());
    }

    @AfterReturning(pointcut = "aopWebLog()",returning = "retObject")
    public void doAfterReturning(Object retObject) throws Exception{
        logger.info("应答值：" + retObject);
        logger.info("费时：" + (System.currentTimeMillis()  - startTime.get()));
    }

    @AfterThrowing(pointcut = "aopWebLog()",throwing = "ex")
    public void addAfterThrowing(JoinPoint joinPoint,Exception ex){
        logger.error("执行异常",ex);
    }

}
