package lab;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 数据
 */
public class Data {

    /**
     * 数据信息
     */
    private String message;

    /**
     * 计数器
     */
    private int count = 0;

    /**
     * 当数据信息被消费时为true，当数据信息被生产时为false。
     */
    private boolean empty = true;

    /**
     * 显式锁
     */
    private Lock lock = new ReentrantLock(true);

    /**
     * 设置数据信息的Condition
     */
    private Condition setCondition = lock.newCondition();

    /**
     * 获取数据信息的Condition
     */
    private Condition getCondition = lock.newCondition();

    /**
     * 设置数据信息
     *
     * @param message 数据信息
     * @throws InterruptedException 当线程被中断时产生此异常
     */
    public void setMessage(String message) throws InterruptedException {
        // 获取锁
        lock.lock();
        try {
            // 当数据信息不为空时，即还有数据信息未消费
            while (!empty) {
                // 使当前生产者线程等待
                setCondition.await();
            }
            // 设置数据信息
            this.message = message + Thread.currentThread().getName() + " --- " + count++;
            // 数据信息没有被消费
            empty = false;

            try {
                // 让当前线程睡1秒钟
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // 唤醒正在此对象监视器上等待的单个线程
            getCondition.signal();
        } finally {
            // 释放锁
            lock.unlock();
        }
    }

    /**
     * 获取数据信息
     *
     * @return 数据信息
     * @throws InterruptedException 当线程被中断时产生此异常
     */
    public String getMessage() throws InterruptedException {
        // 获取锁
        lock.lock();
        try {
            // 当数据信息为空时
            while (empty) {
                // 使当前生产者线程等待
                getCondition.await();
            }
            // 数据信息已经被消费
            empty = true;
            // 唤醒正在此对象监视器上等待的单个线程
            setCondition.signal();
        } finally {
            // 释放锁
            lock.unlock();
        }
        // 返回数据信息
        return message;
    }
}