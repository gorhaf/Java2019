package lab;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * 不可重入锁
 */
public class NotReentrantLock implements Lock {

    /**
     * 用于绑定线程的变量
     */
    private Thread thread;

    /**
     * 获取锁
     */
    @Override
    public void lock() {
        // 获取当前线程
        Thread currentThread = Thread.currentThread();
        // 当当前线程为已经拿到锁的线程时
        while (currentThread == thread) {
            // 同步对象为this的同步代码块
            synchronized (this) {
                try {
                    // 使当前线程等待
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        // 记录当前线程
        thread = currentThread;
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {
    }

    @Override
    public boolean tryLock() {
        return false;
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return false;
    }

    /**
     * 释放锁
     */
    @Override
    public void unlock() {
        // 获取当前线程
        Thread currentThread = Thread.currentThread();
        // 当当前线程不为绑定线程时
        while (currentThread != thread) {
            // 同步对象为this的同步代码块
            synchronized (this) {
                try {
                    // 使当前线程等待
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        // 将绑定线程置空
        thread = null;
        // 同步对象为this的同步代码块
        synchronized (this){
            // 唤醒等待该锁的所有线程
            notifyAll();
        }
    }

    @Override
    public Condition newCondition() {
        return null;
    }
}