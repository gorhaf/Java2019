package main;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.DoubleAdder;
import java.util.concurrent.atomic.LongAdder;

public class Main {

    /**
     * AtomicLong类型变量
     */
    private static AtomicLong atomicLong = new AtomicLong(0L);

    /**
     * LongAdder类型变量
     */
    private static LongAdder longAdder = new LongAdder();

    /**
     * 线程池中的线程个数
     */
    private static int THREAD_SIZE = 100;

    /**
     * 线程池
     */
    private static ExecutorService pool = Executors.newFixedThreadPool(THREAD_SIZE);

    /**
     * 任务管理服务
     */
    private static CompletionService<Long> completionService = new ExecutorCompletionService<>(pool);


    public static void main(String[] args) {
        new DoubleAdder().add(0.1);
        // 开始时间
        long start = System.currentTimeMillis();
        // 循环提交任务
        for (int i = 0; i < THREAD_SIZE; i++) {
            // 提交任务
            completionService.submit(new Callable<Long>() {
                @Override
                public Long call() {
                    // 循环递增
                    for (int j = 0; j < 10000000; j++) {
                        atomicLong.incrementAndGet();
                        // longAdder.increment();
                    }
                    // 返回循环递增后的值
                    return atomicLong.get();
                    // return longAdder.sum();
                }
            });
        }
        // 循环获取任务执行结果
        for (int i = 0; i < THREAD_SIZE; i++) {
            try {
                // 检索并移除表示下一个已完成任务的Future
                Future<Long> future = completionService.take();
                // 获取任务执行结果
                System.out.println(future.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
        // 计算耗时
        long end = System.currentTimeMillis();
        System.out.println("耗时：" + (end - start));
        // 关闭线程池
        pool.shutdown();
    }
}