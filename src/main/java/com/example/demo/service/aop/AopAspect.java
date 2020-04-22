package com.example.demo.service.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletResponse;

/**
 * Created by MI on 2019/9/8.
 */
@Component
//声明一个切面
@Aspect
public class AopAspect {

    //每个连接点是一个方法
    //多个连接点组成了切点
    @Pointcut("execution(* com.example.demo.service.aop..*.*(..))")
    private void pointCut() {

//        return "test AOP pointCut";
    }
    @Pointcut("execution(* com.example.demo.controller..*.*(..))")
    private void pointCutController() {

    }

//    @Before("pointCutController()")
    @Around("pointCutController()")
//    @After("pointCut()")
    public Object advice(ProceedingJoinPoint joinPoint) throws Throwable {
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();


        System.out.println("------sessionId------"+attr.getRequest().getSession().getId());

        System.out.println("------advice------");

        if((int)(Math.random()*100)%2==0){
            return joinPoint.proceed();
        }

        HttpServletResponse response = (attr).getResponse();

//        response.setCharacterEncoding("utf-8");
//
        response.sendError(401,"未登录--网易");
        return null;
//        throw new Exception("未登录");
    }

}
