package com.wandou.annotation;

import com.wandou.enums.XParamsType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 注入的参数
 */
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
public @interface XParam {
    //注入的参数处理
    XParamsType value() default XParamsType.UID;

    //是否必须
    boolean validate() default true;

    String defaultValue() default "";
}
