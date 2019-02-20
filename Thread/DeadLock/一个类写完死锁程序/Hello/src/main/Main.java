package main;

public class Main {

    /**
     * 同步锁：类型为Object的lock1对象
     */
    private static final Object lock1 = new Object();

    /**
     * 同步锁：类型为Object的lock2对象
     */
    private static final Object lock2 = new Object();

    /**
     * 主入口
     *
     * @param args 参数列表
     */
    public static void main(String[] args) {

        // 创建Runnable对象
        Runnable runnable = new Runnable() {
            /**
             * 标识
             */
            private boolean flag = false;

            @Override
            public void run() {
                // 当标识为true时
                if (flag) {
                    // 设置flag的值为false
                    flag = false;

                    // 同步对象为lock1的同步代码块
                    synchronized (lock1) {
                        // 同步对象为lock1的同步代码块输出语句
                        System.out.println("你好，lock1");

                        // 同步对象为boy的同步代码块
                        synchronized (lock2) {
                            // 在同步对象为lock1的同步代码块中执行同步对象为lock2的同步代码块内容
                            System.out.println("lock1同步代码块中的lock2");
                        }
                    }
                }
                // 当标识为false时
                else {
                    // 设置flag的值为true
                    flag = true;

                    // 同步对象为lock2的同步代码块
                    synchronized (lock2) {
                        // 同步对象为lock2的同步代码块输出语句
                        System.out.println("你好，lock2");

                        // 同步对象为girl的同步代码块
                        synchronized (lock1) {
                            // 在同步对象为lock2的同步代码块中执行同步对象为lock1的同步代码块内容
                            System.out.println("lock2同步代码块中的lock1");
                        }
                    }
                }
            }
        };

        // 创建多个线程
        Thread thread1 = new Thread(runnable);
        Thread thread2 = new Thread(runnable);

        // 启动线程
        thread1.start();
        thread2.start();
    }
}