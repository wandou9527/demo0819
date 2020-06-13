package com.wandou.demo.netty;

/**
 * @author liming
 * @date 2020-05-10
 * @description 简单责任链示例
 */
public class PipelineDemo {

    public HandlerChainContext head = new HandlerChainContext(new AbstractHandler() {
        @Override
        void doHandler(HandlerChainContext handlerChainContext, Object arg0) {
            handlerChainContext.runNext(arg0);
        }
    });

    public static void main(String[] args) {
        PipelineDemo pipelineDemo = new PipelineDemo();
        pipelineDemo.addLast(new Handler1());
        pipelineDemo.addLast(new Handler2());

        String str = new String("火车");
        pipelineDemo.requestProcess(str);
        System.out.println("str = " + str);
    }

    public void requestProcess(Object arg0) {
        this.head.handler(arg0);
    }

    public void addLast(AbstractHandler abstractHandler) {
        HandlerChainContext context = head;
        while (context.next != null) {
            context = context.next;
        }
        context.next = new HandlerChainContext(abstractHandler);
    }

}


abstract class AbstractHandler {

    /**
     * 处理器 这个处理器只做一件事情 在传入的字符串后买加个尾巴
     *
     * @param handlerChainContext
     * @param arg0
     */
    abstract void doHandler(HandlerChainContext handlerChainContext, Object arg0);
}


class Handler1 extends AbstractHandler {

    @Override
    void doHandler(HandlerChainContext handlerChainContext, Object arg0) {
        arg0 = arg0.toString() + "..handler1的小尾巴....";
        System.out.println("我是Handler1的实例，我在处理: " + arg0);
        handlerChainContext.runNext(arg0);
    }
}

class Handler2 extends AbstractHandler {

    @Override
    void doHandler(HandlerChainContext handlerChainContext, Object arg0) {
        arg0 = arg0.toString() + "..handler2的小尾巴....";
        System.out.println("我是Handler2的实例，我在处理: " + arg0);
        handlerChainContext.runNext(arg0);
    }
}


class HandlerChainContext {

    HandlerChainContext next;
    AbstractHandler handler;

    public HandlerChainContext(AbstractHandler handler) {
        this.handler = handler;
    }

    void handler(Object arg0) {
        this.handler.doHandler(this, arg0);
    }

    void runNext(Object arg0) {
        if (this.next != null) {
            this.next.handler(arg0);
        }
    }
}
