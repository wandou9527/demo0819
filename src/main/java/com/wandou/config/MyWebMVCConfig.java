package com.wandou.config;

import com.wandou.common.XParamsArgument;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.List;

//拦截器配置类
@Configuration
public class MyWebMVCConfig extends WebMvcConfigurerAdapter {

    /**
     * 好使
     *
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 多个拦截器组成一个拦截器链
        // addPathPatterns 用于添加拦截规则
        // excludePathPatterns 用户排除拦截

//        registry.addInterceptor(new CIntercept()) //添加拦截器
//                .addPathPatterns("/**") //拦截所有请求
//                .excludePathPatterns("/", "/admin_login", "/login"); //对应的不拦截的请求
        super.addInterceptors(registry);
    }

    /**
     * 小红唇的方式
     * 2020-01-17 好使了
     *
     * @param argumentResolvers
     */
    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(new XParamsArgument());
        super.addArgumentResolvers(argumentResolvers);
    }
}
