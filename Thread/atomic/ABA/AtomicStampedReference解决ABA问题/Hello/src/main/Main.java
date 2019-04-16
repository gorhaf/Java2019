package main;

import java.util.concurrent.atomic.AtomicStampedReference;

public class Main {

    /**
     * 以原子的方式更新int类型变量
     */
    private static AtomicStampedReference<Integer> value = new AtomicStampedReference<>(0, 0);

    /**
     * 主入口
     *
     * @param args 参数列表
     */
    public static void main(String[] args) {
        // 在更新value值之前获取一次value的值并输出
        System.out.println("最初：" + value.getReference());
        // 创建线程
        Thread thread1 = new Thread() {
            @Override
            public void run() {
                // CAS赋值操作
                boolean result = value.compareAndSet(0, 1, 0, 1);
                // 输出结果
                System.out.println("compareAndSet(0, 1, 0, 1)执行结果：" + result);
                // CAS赋值操作
                result = value.compareAndSet(1, 0, 1, 2);
                // 输出结果
                System.out.println("compareAndSet(1, 0, 1, 2)执行结果：" + result);
            }
        };
        Thread thread2 = new Thread() {
            @Override
            public void run() {
                // CAS赋值操作
                boolean result = value.compareAndSet(0, 2, 0, 1);
                // 输出结果
                System.out.println("compareAndSet(0, 2, 0, 1)执行结果：" + result);
            }
        };
        // 启动thread1线程
        thread1.start();
        try {
            // 使当前线程睡1秒钟
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // 启动thread2线程
        thread2.start();
        try {
            // 使当前线程睡1秒钟
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // 最后获取一次value的值并输出
        System.out.println("最后：" + value.getReference());
    }
}