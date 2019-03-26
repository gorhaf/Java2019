package main;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

public class Main {

    /**
     * 主入口
     *
     * @param args 参数列表
     */
    public static void main(String[] args) {
        // 创建同步对象
        Object syncObj = new Object();
        // 创建线程
        Thread thread = new Thread() {
            @Override
            public void run() {
                // 被等待前输出语句
                System.out.println(Thread.currentThread().getName() + " --- 被等待");
                // 使当前线程阻塞，直到它超时或被唤醒，通常由被唤醒或中断。
                LockSupport.parkNanos(syncObj, TimeUnit.SECONDS.toNanos(1));
                // 被唤醒后输出语句
                System.out.println(Thread.currentThread().getName() + " --- 被唤醒");
            }
        };
        // 启动线程
        thread.start();
    }
}