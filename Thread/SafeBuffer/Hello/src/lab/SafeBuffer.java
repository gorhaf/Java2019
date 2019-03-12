package lab;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 安全缓冲区
 */
public class SafeBuffer {

    /**
     * 显式锁Lock
     */
    private Lock lock = new ReentrantLock();

    /**
     * 存入数据Condition
     */
    private Condition putCondition  = lock.newCondition();

    /**
     * 获取数据Condition
     */
    private Condition takeCondition = lock.newCondition();

    /**
     * 数据容器
     */
    private Object[] items = new Object[1];

    /**
     * 当前存入数据位置下标
     */
    private int putptr;

    /**
     * 当前获取数据位置下标
     */
    private int takeptr;

    /**
     * 记录当前数据容器已有数据的个数
     */
    private int count;

    /**
     * 存入数据
     *
     * @param item 数据
     * @throws InterruptedException 当线程被中断时产生此异常
     */
    public void put(Object item) throws InterruptedException {
        try {
            // 获取锁
            lock.lock();
            // 当当前数据容器已有数据的个数等于数组长度时
            while (count == items.length) {
                // await()方法使当前线程在等待，直到它被唤醒，通常由被唤醒或中断。
                putCondition.await();
            }
            // 根据下标存入数据
            items[putptr] = item;
            // 当存入数据下标大于最大下标时
            if (++putptr > items.length - 1) {
                // 重置存入数据下标
                putptr = 0;
            }
            // 累加当前数据容器已有数据的个数
            ++count;
            // signal()方法唤醒正在此对象监视器上等待的单个线程，选择是随机的。
            takeCondition.signal();
        } finally {
            // 释放锁
            lock.unlock();
        }
    }

    /**
     * 获取数据
     *
     * @return 数据
     * @throws InterruptedException 当线程被中断时产生此异常
     */
    public Object take() throws InterruptedException {
        try {
            // 获取锁
            lock.lock();
            // 当当前数据容器已有数据的个数为0时
            while (count == 0) {
                // await()方法使当前线程在等待，直到它被唤醒，通常由被唤醒或中断。
                takeCondition.await();
            }
            // 获取数据
            Object data = items[takeptr];
            // 当获取数据下标大于最大下标时
            if (++takeptr > items.length - 1) {
                // 重置获取数据下标
                takeptr = 0;
            }
            // 递减当前数据容器已有数据的个数
            --count;
            // signal()方法唤醒正在此对象监视器上等待的单个线程，选择是随机的。
            putCondition.signal();
            // 返回数据
            return data;
        } finally {
            // 释放锁
            lock.unlock();
        }
    }
}