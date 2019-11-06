package com.wandou.proxy;

import org.junit.Test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author liming
 * @date 2018/8/28 17:13
 * @description
 */
public class ProxyJdk {
    @Test
    public void m1() {
        Target target = new Target();

        TargetInterface proxy = (TargetInterface) Proxy.newProxyInstance(
                target.getClass().getClassLoader(),//与目标对象相同的类加载器
                new Class[]{TargetInterface.class},//与目标对象相同接口字节码对象数组【推荐】
                //target.getClass().getInterfaces(),//慎用，可能多级实现、继承
                new InvocationHandler() {
                    /**
                     *
                     * @param proxy 代理对象引用 慎用 死循环
                     * @param method 目标方法字节码对象
                     * @param args 调用代理对象的方法时传递的参数 //调用 invoke 方法传进的参数
                     * @return
                     * @throws Throwable
                     */
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        /*
                         * proxy.show1();  method是目标对象show1  args是null
                         * proxy.show2("str");  method是目标对象show2  args是Object[]{"str"}
                         */
//                        System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");

                        //对参数修改，前置增强
                        System.out.println("前置增强");

                        Object invoke = method.invoke(target, args);
                        invoke = invoke + " 运行后 返回值加增强";
                        //对返回值修改，后置增强
                        System.out.println("后置增强");

                        return invoke;
                    }
                });

        //调用代理对象的方法
        proxy.show1();//代理对象调用方法，实际是调用 invoke
        proxy.show2("str");
        String show3 = proxy.show3(15);
        System.out.println(show3);
    }
}
