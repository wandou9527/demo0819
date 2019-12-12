package com.wandou.config;

import com.wandou.annotation.XParam;
import com.wandou.common.Constant;
import com.wandou.common.XParamsArgument;
import com.wandou.enums.XParamsType;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.aspectj.lang.reflect.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Arrays;

/**
 * 拦截controller切面
 */

@Aspect
@Component
@Slf4j
public class DAspect {

    @Around("@annotation(requestMapping)")
    public Object m1(ProceedingJoinPoint proceedingJoinPoint, RequestMapping requestMapping) throws Throwable {
        log.info("requestMapping 切面拦截");
        Object[] args = proceedingJoinPoint.getArgs();
        System.out.println("args " + Arrays.asList(args));
        Signature signature = proceedingJoinPoint.getSignature();
        System.out.println("signature = " + signature);
        // Signature 这里切的是方法 Signature为org.springframework.aop.aspectj.MethodInvocationProceedingJoinPoint$MethodSignatureImpl（内部类）
        System.out.println("signature.getClass() = " + signature.getClass());
        Class<?> clazz = proceedingJoinPoint.getTarget().getClass();
        Class[] parameterTypes = ((MethodSignature) signature).getParameterTypes();
        Method method = clazz.getMethod(signature.getName(), parameterTypes);
//        XParam xParamAnnotation = method.getAnnotation(XParam.class);
        Parameter[] parameters = method.getParameters();
        System.out.println("parameters = " + Arrays.toString(parameters));
        System.out.println("args.length = " + args.length);
        System.out.println("parameters.length = " + parameters.length);

        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();


        for (int i = 0; i < args.length; i++) {
            Parameter parameter = parameters[i];
            if (parameter.getAnnotation(XParam.class) != null) {
//                arg = new Long(666L);//不好使
                Long uid = 0L;
                String token = request.getHeader("token");
                if (StringUtils.isNotBlank(token)) {
                    uid = Constant.tokenUserIdMap.get(token);
                }
//                args[i] = new Long(666L);//好使
                args[i] = uid;
                break;
            }
        }

        Object proceed = proceedingJoinPoint.proceed(args);
        return proceed;

    }

//    @Before("@annotation(getMapping)")
//    public void m2(GetMapping getMapping, ProceedingJoinPoint proceedingJoinPoint) {
//        System.out.println("getMapping 切面 ");
//    }

}
