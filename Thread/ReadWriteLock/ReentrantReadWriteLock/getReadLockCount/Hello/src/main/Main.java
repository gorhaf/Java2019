package main;

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
     * 主入口
     *
     * @param args 参数列表
     */
    public static void main(String[] args) {
        // 输出语句
        System.out.println("获取当前线程与读锁的保持数 --- " + rwl.getReadHoldCount());
        System.out.println("获取读锁的所有保持数 --- " + rwl.getReadLockCount());

        new Thread() {
            @Override
            public void run() {
                r.lock();
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    r.unlock();
                }
            }
        }.start();

        // 获取读锁
        r.lock();
        try {
            // 获取读锁
            r.lock();
            // 输出语句
            System.out.println("获取当前线程与读锁的保持数 --- " + rwl.getReadHoldCount());
            System.out.println("获取读锁的所有保持数 --- " + rwl.getReadLockCount());
        } finally {
            // 释放读锁
            r.unlock();
            // 输出语句
            System.out.println("获取当前线程与读锁的保持数 --- " + rwl.getReadHoldCount());
            System.out.println("获取读锁的所有保持数 --- " + rwl.getReadLockCount());
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