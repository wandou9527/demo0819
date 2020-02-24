package com.wandou.common;

import com.wandou.annotation.XParam;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

/**
 * xhc
 * 方法的扩展参数信息标志,这个参数可指定用户编号，角色，触发场景等
 */

@Slf4j
@Component
public class XParamsArgument implements HandlerMethodArgumentResolver {

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        log.info("进入 supportsParameter()");
        return parameter.hasParameterAnnotation(XParam.class);
    }

    /**
     * 不好使呢
     * 2020-01-17 好使了，需要 com.wandou.config.MyWebMVCConfig#addArgumentResolvers(java.util.List)
     *
     * @param parameter
     * @param mavContainer
     * @param webRequest
     * @param binderFactory
     * @return
     * @throws Exception
     */
    @Override
    public Object resolveArgument(MethodParameter parameter,
                                  ModelAndViewContainer mavContainer,
                                  NativeWebRequest webRequest,
                                  WebDataBinderFactory binderFactory) throws Exception {
        XParam annotation = parameter.getParameterAnnotation(XParam.class);

        switch (annotation.value()) {
            case UID:
                String token = webRequest.getHeader("token");
                if (StringUtils.isBlank(token) && annotation.validate()) {
                    throw new HttpClientErrorException(HttpStatus.UNAUTHORIZED, "token不能为空");
                }
                Long uid = Constant.tokenUserIdMap.get(token);
                if (uid == null || uid <= 0) {
                    throw new HttpClientErrorException(HttpStatus.UNAUTHORIZED, "非法的角色");
                }
                return uid;
            case Scope:
                String scope = webRequest.getHeader("scope");
                if (StringUtils.isBlank(scope) && annotation.validate()) {
                    throw new HttpClientErrorException(HttpStatus.UNAUTHORIZED, "非法的角色");
                }
                if (StringUtils.isNotBlank(annotation.defaultValue())) {
                    return annotation.defaultValue();
                }
                return scope;
            case OpenID:
                String openid = webRequest.getHeader("uauth");
                if (StringUtils.isBlank(openid) && annotation.validate()) {
                    throw new HttpClientErrorException(HttpStatus.UNAUTHORIZED, "错误的登录信息");
                }
                if (StringUtils.isNotBlank(annotation.defaultValue())) {
                    return annotation.defaultValue();
                }
                return openid;
            case IP:
                String IP = webRequest.getHeader("ip");
                if (StringUtils.isBlank(IP) && annotation.validate()) {
                    throw new HttpClientErrorException(HttpStatus.UNAUTHORIZED, "错误的请求来源");
                }
                if (StringUtils.isNotBlank(annotation.defaultValue())) {
                    return annotation.defaultValue();
                }
                return IP;

        }

        return null;
    }
}
