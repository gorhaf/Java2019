package main;

import lab.NotReentrantLock;

import java.util.concurrent.locks.Lock;

public class Main {
    // 计数器
    static int count = 0;

    /**
     * 主入口
     *
     * @param args 参数列表
     */
    public static void main(String[] args) {
        // 创建显式锁对象
        Lock lock = new NotReentrantLock();

        // 通过匿名内部类方式实现Runnable接口
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                try {
                    // 获取锁
                    lock.lock();
                    // 计数器+1
                    count++;
                    // 输出语句
                    System.out.println("外层同步调用，锁被获取次数 --- " + count);

                    try {
                        // 获取锁
                        lock.lock();
                        // 计数器+1
                        count++;
                        // 输出语句
                        System.out.println("内层同步调用，锁被获取次数 --- " + count);
                    } finally {
                        // 释放锁
                        lock.unlock();
                        // 计数器-1
                        count--;
                        // 输出语句
                        System.out.println("内层同步调用，锁被获取次数 --- " + count);
                    }
                } finally {
                    // 释放锁
                    lock.unlock();
                    // 计数器-1
                    count--;
                    // 输出语句
                    System.out.println("外层同步调用，锁被获取次数 --- " + count);
                }
            }
        };

        // 创建线程
        Thread thread = new Thread(runnable);

        // 启动线程
        thread.start();
    }
}