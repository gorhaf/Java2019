package lab;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 围棋
 */
public class Chess {

    /**
     * 棋局信息
     */
    private String message;

    /**
     * 记录棋局步数
     */
    private int count = 1;

    /**
     * 读写锁
     */
    private ReadWriteLock rwl = new ReentrantReadWriteLock(true);

    /**
     * 读锁
     */
    private Lock r = rwl.readLock();

    /**
     * 写锁
     */
    private Lock w = rwl.writeLock();

    /**
     * 抄写棋局信息
     */
    public void put() {
        // 获取写锁
        w.lock();
        try {
            // 记录当前棋局信息
            message = "第" + count++ + "手";
        } finally {
            // 释放写锁
            w.unlock();
        }
    }

    /**
     * 获取棋局信息
     *
     * @return 棋局信息
     */
    public String take() {
        // 获取读锁
        r.lock();
        try {
            // 返回棋局信息
            return message;
        } finally {
            // 释放读锁
            r.unlock();
        }
    }
}