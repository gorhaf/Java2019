package main;

import lab.DeadLock;

public class Main {

    /**
     * 主入口
     *
     * @param args 参数列表
     */
    public static void main(String[] args) {
        // 创建DeadLock对象
        DeadLock deadLock = new DeadLock();

        // 创建多个线程
        Thread thread1 = new Thread(deadLock);
        Thread thread2 = new Thread(deadLock);

        // 启动线程
        thread1.start();
        thread2.start();
    }
}