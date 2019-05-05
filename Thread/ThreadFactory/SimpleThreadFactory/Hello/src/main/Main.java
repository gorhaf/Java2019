package main;

public class Main {

    public static void main(String[] args) {
        // 创建任务
        Runnable r = new Runnable() {
            @Override
            public void run() {
                // 获取当前执行任务的线程名称
                System.out.println("线程名称: " + Thread.currentThread().getName());
            }
        };

        // 创建线程工厂
        SimpleThreadFactory threadFactory = new SimpleThreadFactory();
        // 创建线程
        Thread t = threadFactory.newThread(r);
        // 输出线程名称
        System.out.println(t.getName());
    }
}