package main;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Main {

    /**
     * 主入口
     *
     * @param args 参数列表
     */
    public static void main(String[] args) {
        // 创建显式锁对象
        Lock lock = new ReentrantLock(true);

        // 通过匿名内部类方式实现Runnable接口
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                // 无限循环
                while (true) {
                    try {
                        // 获取锁
                        lock.lock();
                        // 使当前线程睡1秒钟
                        Thread.sleep(1000);
                        // 输出语句
                        System.out.println(Thread.currentThread().getName() + " --- run");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        // 释放锁
                        lock.unlock();
                    }
                }
            }
        };

        // 创建线程
        Thread thread1 = new Thread(runnable);
        Thread thread2 = new Thread(runnable);
        Thread thread3 = new Thread(runnable);

        // 启动线程
        thread1.start();
        thread2.start();
        thread3.start();
    }
}