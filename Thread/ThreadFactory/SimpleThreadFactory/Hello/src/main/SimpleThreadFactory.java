package main;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

public class SimpleThreadFactory implements ThreadFactory {

    /**
     * 线程池的序号
     */
    private static final AtomicInteger poolNumber = new AtomicInteger(1);

    /**
     * 线程的序号
     */
    private final AtomicInteger threadNumber = new AtomicInteger(1);

    /**
     * 线程名称前缀
     */
    private final String namePrefix;

    public SimpleThreadFactory() {
        // 线程名称前缀
        namePrefix = "pool-" +
                poolNumber.getAndIncrement() +
                "-thread-";
    }

    /**
     * 创建统一标准的线程
     *
     * @return 统一标准的线程
     */
    @Override
    public Thread newThread(Runnable r) {
        // 创建线程并指定线程名称
        Thread t = new Thread(r, namePrefix + threadNumber.getAndIncrement());
        // 返回线程
        return t;
    }
}