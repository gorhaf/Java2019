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
     * 读锁Condition
     */
    private static Condition rc = r.newCondition();

    /**
     * 主入口
     *
     * @param args 参数列表
     */
    public static void main(String[] args) {
        // 创建线程
        Thread thread1 = new Thread() {
            @Override
            public void run() {
                // 获取写锁
                w.lock();
                try {
                    // 被等待之前
                    System.out.println(Thread.currentThread().getName() + " --- 被等待");
                    // await()方法使当前线程在等待，直到它被唤醒，通常由被唤醒或中断。
                    wc.await();
                    // 被唤醒之后
                    System.out.println(Thread.currentThread().getName() + " --- 被唤醒");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    // 释放写锁
                    w.unlock();
                }
            }
        };
        Thread thread2 = new Thread() {
            @Override
            public void run() {
                // 获取写锁
                w.lock();
                try {
                    // signal()方法唤醒正在此对象监视器上等待的单个线程，选择是随机的。
                    wc.signal();
                } finally {
                    // 释放写锁
                    w.unlock();
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
        thread2.start();
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