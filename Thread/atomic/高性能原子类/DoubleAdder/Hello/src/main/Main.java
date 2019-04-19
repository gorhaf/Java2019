package main;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.DoubleAdder;

public class Main {

    /**
     * AtomicReference<Double>类型变量
     */
    private static AtomicReference<Double> atomicDouble = new AtomicReference<>(0.0);

    /**
     * DoubleAdder类型变量
     */
    private static DoubleAdder doubleAdder = new DoubleAdder();

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
    private static CompletionService<Double> completionService = new ExecutorCompletionService<>(pool);


    public static void main(String[] args) {
        new DoubleAdder().add(0.1);
        // 开始时间
        long start = System.currentTimeMillis();
        // 循环提交任务
        for (int i = 0; i < THREAD_SIZE; i++) {
            // 提交任务
            completionService.submit(new Callable<Double>() {
                @Override
                public Double call() {
                    // 循环递增
                    for (int j = 0; j < 1000000; j++) {
                        // atomicDouble.updateAndGet(new UnaryOperator<Double>() {
                        //     @Override
                        //     public Double apply(Double aDouble) {
                        //         return aDouble + 1.0;
                        //     }
                        // });
                        doubleAdder.add(1.0);
                    }
                    // 返回循环递增后的值
                    // return atomicDouble.get();
                    return doubleAdder.sum();
                }
            });
        }
        // 循环获取任务执行结果
        for (int i = 0; i < THREAD_SIZE; i++) {
            try {
                // 检索并移除表示下一个已完成任务的Future
                Future<Double> future = completionService.take();
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