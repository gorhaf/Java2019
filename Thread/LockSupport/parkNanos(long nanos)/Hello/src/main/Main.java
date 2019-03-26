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
        // 创建线程
        Thread thread = new Thread() {
            @Override
            public void run() {
                // 被等待前输出语句
                System.out.println(Thread.currentThread().getName() + " --- 被等待");
                // 使当前线程阻塞，直到它超时或被唤醒，通常由被唤醒或中断。
                LockSupport.parkNanos(TimeUnit.SECONDS.toNanos(5));
                // 被唤醒后输出语句
                System.out.println(Thread.currentThread().getName() + " --- 被唤醒");
            }
        };
        // 启动线程
        thread.start();
        try {
            // 使当前线程睡1秒钟
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // 唤醒被阻塞的线程
        LockSupport.unpark(thread);
    }
}
/*
        // 创建线程
        Thread thread = new Thread() {
            @Override
            public void run() {
                // 获取锁
                lock.lock();
                try {
                    // 被等待前输出语句
                    System.out.println(Thread.currentThread().getName() + " --- 被等待");
                    // 使当前线程在等待，直到它被唤醒，通常由被唤醒或中断。
                    condition.await();
                    // 被唤醒后输出语句
                    System.out.println(Thread.currentThread().getName() + " --- 被唤醒");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    // 释放锁
                    lock.unlock();
                }
            }
        };

        // 创建线程
        Thread thread = new Thread() {
            @Override
            public void run() {
                // 同步对象为Main.class的同步代码块
                synchronized (Main.class) {
                    try {
                        // 被等待前输出语句
                        System.out.println(Thread.currentThread().getName() + " --- 被等待");
                        // 使当前线程在等待，直到它被唤醒，通常由被唤醒或中断。
                        Main.class.wait();
                        // 被唤醒后输出语句
                        System.out.println(Thread.currentThread().getName() + " --- 被唤醒");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };

static Object	getBlocker​(Thread t)
Returns the blocker object supplied to the most recent invocation of a park method that has not yet unblocked, or null if not blocked.
static void	park()
Disables the current thread for thread scheduling purposes unless the permit is available.
static void	park​(Object blocker)
Disables the current thread for thread scheduling purposes unless the permit is available.
static void	parkNanos​(long nanos)
Disables the current thread for thread scheduling purposes, for up to the specified waiting time, unless the permit is available.
static void	parkNanos​(Object blocker, long nanos)
Disables the current thread for thread scheduling purposes, for up to the specified waiting time, unless the permit is available.
static void	parkUntil​(long deadline)
Disables the current thread for thread scheduling purposes, until the specified deadline, unless the permit is available.
static void	parkUntil​(Object blocker, long deadline)
Disables the current thread for thread scheduling purposes, until the specified deadline, unless the permit is available.
static void	unpark​(Thread thread)
Makes available the permit for the given thread, if it was not already available.
 */