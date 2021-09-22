package com.employetracker.AOPs;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @author : Vishal Srivastava
 * @Date : 04-09-2021
 **/

@Component
@Aspect
public class LoggingAdvice {

    // Using this advice or aop we are handling logging mechanism for all class across the application

    private Logger log = LoggerFactory.getLogger(LoggingAdvice.class);


    // this is just for testing
    // we can directly write the expression here also.
    @Before("execution(* com.employetracker.service.EmpServiceImpl.allEmployees())")
    public void wakeUp() {
        System.out.println("aop works fine");
    }


    // pointcut is the place in which we declare a location we want to apply our advice -- here we are applying across the application
    @Pointcut(value = "execution(* com.employetracker.*.*.*(..))")
    public void myPointCut() {

    }


    // around() advice is a combination of @before and @AfterReturning
    @Around("myPointCut()")
    public Object loggingAdvice(ProceedingJoinPoint pjp) throws Throwable{

        // This is used to represent as a string of json.
        ObjectMapper mapper = new ObjectMapper();

        // Getting values of the method like parameters, return type, className, method name etc.. we want to apply logging using ProceedingJoinPoint

        String methodName = pjp.getSignature().getName();
        String className = pjp.getTarget().getClass().getName();
        Object[] arguments = pjp.getArgs();
        // getting return values
        Object object = pjp.proceed();

        log.info("Method invoked : " + className + ":" + methodName + "()" + "arguments" + mapper.writeValueAsString(arguments));
        log.info(className + ":" + methodName + "()" + "response" + mapper.writeValueAsString(object));

        return object;

    }

}
