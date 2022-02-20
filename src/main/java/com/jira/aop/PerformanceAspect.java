package com.jira.aop;

//import org.aspectj.lang.ProceedingJoinPoint;
//import org.aspectj.lang.annotation.Around;
//import org.aspectj.lang.annotation.Aspect;
//import org.aspectj.lang.annotation.Pointcut;
//import org.perf4j.StopWatch;
//import org.perf4j.slf4j.Slf4JStopWatch;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.perf4j.StopWatch;
import org.perf4j.slf4j.Slf4JStopWatch;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class PerformanceAspect {

    @Pointcut("execution(* com.jira.controller.Greeting.greeting())")
    public void performancePointCut() {

    }

    @Around("performancePointCut()")
    public Object PerformanceAdvisor(ProceedingJoinPoint pjp) throws Throwable {
        StopWatch stopWatch = new Slf4JStopWatch(pjp.getSignature().toShortString() + "#####################################################################");
        Object ob = null;
        System.out.println("execution begin .....");


        final Signature signature = pjp.getStaticPart().getSignature();
        if(signature instanceof MethodSignature){
            final MethodSignature ms = (MethodSignature) signature;
            final Class<?>[] parameterTypes = ms.getParameterTypes();
            for(final Class<?> pt : parameterTypes){
                System.out.println("Parameter type:" + pt);
            }
        }

        // retrieve the runtime method arguments (dynamic)
        for(final Object argument : pjp.getArgs()){
            System.out.println("Parameter value:" + argument);
        }

        try {
              ob = pjp.proceed();
        } finally {
            stopWatch.stop();
            System.out.println("execution done .....");
        }

        return ob;
    }

}