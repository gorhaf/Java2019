package lab;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 缓存数据
 */
public class CachedData {

    /**
     * 记录缓存数据
     */
    private String data;

    /**
     * 记录缓存数据创建时间（单位：毫秒）
     */
    private long createTimeMillis;

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
     * 设置缓存数据
     *
     * @param data 缓存数据
     */
    public void setCachedData(String data) {
        // 获取写锁
        w.lock();
        try {
            // 设置缓存数据
            this.data = data;
            // 获取当前时间（单位：毫秒）
            createTimeMillis = System.currentTimeMillis();
        } finally {
            // 释放写锁
            w.unlock();
        }
    }

    /**
     * 获取缓存数据
     *
     * @return 缓存数据
     */
    public String getCachedData() {
        // 获取读锁
        r.lock();
        
        // 获取当前时间（单位：毫秒）
        long currentTimeMillis = System.currentTimeMillis();
        // 时间差（单位：毫秒）
        long deltaTimeMillis = currentTimeMillis - createTimeMillis;
        // 当时间差大于过期时间时，缓存数据已过期，否则未过期。
        boolean expired = deltaTimeMillis > 3000;
        // 当缓存数据已过期时
        if (expired) {
            // 释放读锁
            r.unlock();
            // 获取写锁
            w.lock();
            try {
                // 将缓存数据设置为"数据已过期"
                data = "数据已过期";
                // 获取读锁
                r.lock();
            } finally {
                // 释放写锁
                w.unlock();
            }
        }

        try {
            // 返回缓存数据
            return data;
        } finally {
            // 释放读锁
            r.unlock();
        }
    }
}