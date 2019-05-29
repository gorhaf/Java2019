package main;

import java.util.Iterator;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class Main {

    public static void main(String[] args) {
        // 创建线程池
        ThreadPoolExecutor threadPool = (ThreadPoolExecutor) Executors.newFixedThreadPool(1);
        // 创建任务
        Runnable runnable1 = new Runnable() {
            @Override
            public void run() {
                try {
                    TimeUnit.SECONDS.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        Runnable runnable2 = new Runnable() {
            @Override
            public void run() {
                try {
                    TimeUnit.SECONDS.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        // 提交任务
        threadPool.submit(runnable1);
        threadPool.submit(runnable2);
        // 获取任务队列
        BlockingQueue<Runnable> workQueue = threadPool.getQueue();
        // 任务队列迭代器
        Iterator<Runnable> iterator = workQueue.iterator();
        // 迭代任务队列中的元素
        while (iterator.hasNext()) {
            // 获取任务队列中的任务
            Runnable task = iterator.next();
            // 删除任务
            boolean removed = threadPool.remove(task);
            System.out.println("任务是否删除成功：" + removed);
        }
        // 关闭线程池
        threadPool.shutdown();
    }
}