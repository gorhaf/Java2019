package main;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Main {

    /**
     * 可重入读写锁
     */
    private static ReentrantReadWriteLock rwl = new ReentrantReadWriteLock(true);

    /**
     * 读锁
     */
    private static ReentrantReadWriteLock.ReadLock r = rwl.readLock();

    /**
     * 写锁
     */
    private static ReentrantReadWriteLock.WriteLock w = rwl.writeLock();

    /**
     * 写锁Condition
     */
    private static Condition wc = w.newCondition();

    /**
     * 主入口
     *
     * @param args 参数列表
     */
    public static void main(String[] args) {
        // 创建实现了Runnable接口的匿名内部类对象
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                // 获取写锁
                w.lock();
                try {
                    // await()方法使当前线程在等待，直到它被唤醒，通常由被唤醒或中断。
                    wc.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    // 释放写锁
                    w.unlock();
                }
            }
        };
        // 创建线程
        Thread thread1 = new Thread(runnable);
        // 启动线程
        thread1.start();
        try {
            // 使主线程睡1秒钟
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // 获取写锁
        w.lock();
        try {
            // 查询Condition上是否有线程正在等待
            System.out.println("Condition上是否有线程正在等待: " + rwl.hasWaiters(wc));
        } finally {
            // 释放写锁
            w.unlock();
        }
    }
}
/*
int	getQueueLength()
Returns an estimate of the number of threads waiting to acquire either the read or write lock.
int	getReadHoldCount()
Queries the number of reentrant read holds on this lock by the current thread.
int	getReadLockCount()
Queries the number of read locks held for this lock.
int	getWaitQueueLength​(Condition condition)
Returns an estimate of the number of threads waiting on the given condition associated with the write lock.
int	getWriteHoldCount()
Queries the number of reentrant write holds on this lock by the current thread.
boolean	hasQueuedThread​(Thread thread)
Queries whether the given thread is waiting to acquire either the read or write lock.
boolean	hasQueuedThreads()
Queries whether any threads are waiting to acquire the read or write lock.
boolean	hasWaiters​(Condition condition)
Queries whether any threads are waiting on the given condition associated with the write lock.
boolean	isFair()
Returns true if this lock has fairness set true.
boolean	isWriteLocked()
Queries if the write lock is held by any thread.
boolean	isWriteLockedByCurrentThread()
Queries if the write lock is held by the current thread.
String	toString()
Returns a string identifying this lock, as well as its lock state.

 */