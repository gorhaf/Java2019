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

        // 创建线程
        Thread thread1 = new Thread() {
            @Override
            public void run() {
                try {
                    // 获取锁
                    lock.lock();
                    // 输出语句
                    System.out.println(Thread.currentThread().getName() + " --- 等待");
                    // await()方法造成当前线程在等待，直到它被唤醒，通常由被通知或中断。
                    condition.await();
                    // 输出语句
                    System.out.println(Thread.currentThread().getName() + " --- 被唤醒");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    // 释放锁
                    lock.unlock();
                }
            }
        };
        Thread thread2 = new Thread() {
            @Override
            public void run() {
                try {
                    // 获取锁
                    lock.lock();
                    // signalAll()方法作用是唤醒正在此对象监视器上等待的所有线程。
                    condition.signalAll();
                } finally {
                    // 释放锁
                    lock.unlock();
                }
            }
        };

        // 启动线程
        thread1.start();

        try {
            // 使主线程睡3秒钟
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // 启动线程
        thread2.start();
    }
}