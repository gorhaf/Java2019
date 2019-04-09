package lab;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 全局计数器
 */
public class Counter {

    /**
     * 当前值
     */
    private static AtomicInteger value = new AtomicInteger(0);

    /**
     * 单例模式
     */
    private Counter() {
    }

    /**
     * 获取当前值
     *
     * @return 返回value
     */
    public static int get() {
        return value.get();
    }

    /**
     * 递增
     */
    public static void increment() {
        value.incrementAndGet();
    }
}