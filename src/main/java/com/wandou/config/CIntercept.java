package com.wandou.config;

import com.wandou.common.Constant;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 拦截器 token-》userId
 */

@Slf4j
//@Component
public class CIntercept implements HandlerInterceptor {

    /**
     * 拦截器管用的额
     *
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("request: {}", request);
        System.out.println("拦截器");
        System.out.println("handler = " + handler);
        Annotation[] annotations = handler.getClass().getAnnotations();
//        System.out.println(Arrays.asList(annotations));
        System.out.println("handler.getClass() = " + handler.getClass());
        HandlerMethod handlerMethod = (HandlerMethod)handler;
        MethodParameter[] methodParameters = handlerMethod.getMethodParameters();
        System.out.println("methodParameters = " + Arrays.toString(methodParameters));
        System.out.println("annotations = " + Arrays.toString(annotations));
        String token = request.getHeader("token");
        if (StringUtils.isBlank(token)) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "token不能为空");
        }
        Long userId = Constant.tokenUserIdMap.get(token);
        if (userId == null || userId <= 0) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "用户不存在");
        }
        log.info("token: {}, userId: {}", token, userId);
        request.setAttribute("userIdA", userId);

//        throw new RuntimeException();
        //拦截器 可以拦截到request 可以获取header中token 关键是怎么将uid压入参数
        Method method = handlerMethod.getMethod();


        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
