package lab;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 高并发容器
 */
public class ConcurrentDictionary {

    /**
     * 数据容器
     */
    private Map<String, String> m = new TreeMap<>();

    /**
     * 可重入读写锁
     */
    private ReentrantReadWriteLock rwl = new ReentrantReadWriteLock(true);

    /**
     * 读锁
     */
    private ReentrantReadWriteLock.ReadLock r = rwl.readLock();

    /**
     * 写锁
     */
    private ReentrantReadWriteLock.WriteLock w = rwl.writeLock();

    /**
     * 添加数据并将旧值返回给调用者
     *
     * @param key   键
     * @param value 值
     * @return 旧值
     */
    public String put(String key, String value) {
        // 获取写锁
        w.lock();
        try {
            // 添加数据并将旧值返回给调用者
            return m.put(key, value);
        } finally {
            // 释放写锁
            w.unlock();
        }
    }

    /**
     * 获取数据
     *
     * @param key 键
     * @return 键所对应的值
     */
    public String get(String key) {
        // 获取读锁
        r.lock();
        try {
            // 返回键所对应的值
            return m.get(key);
        } finally {
            // 释放读锁
            r.unlock();
        }
    }

    /**
     * 获取容器中所有的key值
     *
     * @return 容器中所有的key值
     */
    public List<String> allKeys() {
        // 获取读锁
        r.lock();
        try {
            // 返回容器中所有的key值
            return new ArrayList<>(m.keySet());
        } finally {
            // 释放读锁
            r.unlock();
        }
    }

    /**
     * 清空容器
     */
    public void clear() {
        // 获取写锁
        w.lock();
        try {
            // 清空容器
            m.clear();
        } finally {
            // 释放写锁
            w.unlock();
        }
    }
}