package com.wandou.proxy;

import org.junit.Test;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author liming
 * @date 2018/9/2
 * @description
 */
public class ProxyCglib {
    @Test
    public void m1() {
        //0 创建目标对象
        TargetCglib targetCglib = new TargetCglib();

        //1 创建增强器
        Enhancer enhancer = new Enhancer();
        // 2 设置父类
        enhancer.setSuperclass(TargetCglib.class);//为父类生成个子类充当代理
        //3 设置回调方法
        enhancer.setCallback(new MethodInterceptor() {
            @Override
            public Object intercept(Object proxy, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
                System.out.println("前置增强 。。。 ");

                //执行目标方法
                Object invoke = method.invoke(targetCglib, args);

                System.out.println("后置增强 。。。 ");

                return invoke;
            }
        });

        TargetCglib proxy = (TargetCglib) enhancer.create();

        proxy.show1();
        proxy.show2("lala");
        String s = proxy.show3(18);
        System.out.println(s);

    }
}
