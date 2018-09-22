package com.wandou.jvm;

/**
 * @author liming
 * @date 2018/9/5 17:22
 * @description 一次对象自我拯救的演示
 */
public class FinalizeEscapeGC {
    public static FinalizeEscapeGC SAVE_HOOK = null;

    public void isAlive() {
        System.out.println("yes, i am still alive :)");
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        System.out.println("finalize method executed!");
        FinalizeEscapeGC.SAVE_HOOK = this;
    }

    public static void main(String[] args) throws InterruptedException {
        SAVE_HOOK = new FinalizeEscapeGC();

        //对象第一次成功解救自己
        SAVE_HOOK = null;
        System.gc();
        //因为 finalize 方法优先级低，所以暂停0.5秒以等待它
        Thread.sleep(500L);
//        SAVE_HOOK.isAlive();
        if (SAVE_HOOK != null) {
            SAVE_HOOK.isAlive();
        } else {
            System.out.println("no, i am dead :(");
        }

        //下面代码段与上面完全相同，但是这次自救却失败了
        SAVE_HOOK = null;
        System.gc();
        //因为 finalize 方法优先级低，所以暂停0.5秒以等待它
        Thread.sleep(500L);
        if (SAVE_HOOK != null) {
            SAVE_HOOK.isAlive();
        } else {
            System.out.println("no, i am dead :(");
        }



    }
}
