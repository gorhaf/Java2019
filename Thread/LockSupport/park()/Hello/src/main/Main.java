package main;

import java.util.concurrent.locks.LockSupport;

public class Main {

    /**
     * 主入口
     *
     * @param args 参数列表
     */
    public static void main(String[] args) {
        // 创建线程
        Thread thread = new Thread() {
            @Override
            public void run() {
                // 被等待前输出语句
                System.out.println(Thread.currentThread().getName() + " --- 第一次被等待");
                // 使当前线程在等待，直到它被唤醒，通常由被唤醒或中断。
                LockSupport.park();
                // 被唤醒后输出语句
                System.out.println(Thread.currentThread().getName() + " --- 被唤醒");
            }
        };
        // 唤醒正在此对象监视器上等待的所有线程
        LockSupport.unpark(thread);
        // 启动线程
        thread.start();
    }
}