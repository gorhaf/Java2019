package main;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Main {

    /**
     * 主入口
     *
     * @param args 参数列表
     */
    public static void main(String[] args) {
        // 创建显式锁
        Lock lock = new ReentrantLock();
        // 获取Condition对象
        Condition condition = lock.newCondition();

        // 实现了Runnable接口的匿名内部类
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                try {
                    // 获取锁
                    lock.lock();
                    // 输出语句
                    System.out.println(Thread.currentThread().getName() + " --- 等待");
                    // awaitUninterruptibly()方法造成当前线程在等待，直到它被唤醒。
                    condition.awaitUninterruptibly();
                    // 输出语句
                    System.out.println(Thread.currentThread().getName() + " --- 被唤醒");
                } finally {
                    // 释放锁
                    lock.unlock();
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

        try {
            // 使主线程睡1秒钟
            Thread.sleep(1000);
            // 获取锁
            lock.lock();
            // signalAll()方法作用是唤醒正在此对象监视器上等待的所有线程。
            condition.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            // 释放锁
            lock.unlock();
        }
    }
}