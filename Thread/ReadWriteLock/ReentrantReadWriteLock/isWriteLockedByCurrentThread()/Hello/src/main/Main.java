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
        // 查询当前线程是否持有此写锁
        System.out.println("isHeldByCurrentThread --- " + w.isHeldByCurrentThread());
        System.out.println("isWriteLockedByCurrentThread --- " + rwl.isWriteLockedByCurrentThread());

        // 获取写锁
        w.lock();
        try {
            // 查询当前线程是否持有此写锁
            System.out.println("isHeldByCurrentThread --- " + w.isHeldByCurrentThread());
            System.out.println("isWriteLockedByCurrentThread --- " + rwl.isWriteLockedByCurrentThread());
        } finally {
            // 释放写锁
            w.unlock();
        }
    }
}