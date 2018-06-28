package com.mmtech.icecloud.manager.interceptor;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.CodeSignature;
import org.springframework.stereotype.Component;

/**
 * @author Adam DENG
 * @Date 2018/6/14 14:20
 */
//@Component
//@Aspect
public class LogAspectInterceptor {

    @Pointcut(value = "execution(* com.mmtech.icecloud.manager.server.sys.service.*.*(..))")
    public void pointcut() {

    }

    @Before("pointcut()")
    public void before(JoinPoint joinPoint) {
        System.out.println("SimpleAspect.before()");
        // 获取传入的参数值
        Object[] args = joinPoint.getArgs();
        for (Object object : args) {
            System.out.println(object);
        }
        // 获取方法名
        Signature signature = joinPoint.getSignature();
        System.out.println(signature.getName());
        // 获取参数名
        CodeSignature codeSignature = (CodeSignature) signature;
        String[] parameterNames = codeSignature.getParameterNames();
        for (String string : parameterNames) {
            System.out.println(string);
        }
        // 啥玩意？触发事件？
        String kind = joinPoint.getKind();
        System.out.println("kind : " + kind);
        // 获取目标对象
        Object target = joinPoint.getTarget();
        System.out.println(target);
    }

}
