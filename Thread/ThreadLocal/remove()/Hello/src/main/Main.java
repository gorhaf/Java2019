package main;

public class Main {

    /**
     * 主入口
     *
     * @param args 参数列表
     */
    public static void main(String[] args) {
        // 创建ThreadLocal对象
        ThreadLocal<Boolean> closedKey = new ThreadLocal<>();
        // 创建线程
        Thread thread1 = new Thread() {
            @Override
            public void run() {
                // 设置value
                closedKey.set(false);
                // 当链接未关闭时
                while (!closedKey.get()) {
                    // 输出语句
                    System.out.println(Thread.currentThread().getName() + " --- run");
                    try {
                        // 使当前线程睡1秒钟
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    // 设置当前线程上的变量副本
                    closedKey.set(true);
                }
                // 删除当前线程上的变量副本
                closedKey.remove();
                // 获取当前线程上的变量副本
                System.out.println(closedKey.get());
            }
        };
        Thread thread2 = new Thread() {
            @Override
            public void run() {
                // 将closed值设置为true
                closedKey.set(true);
            }
        };
        // 启动线程
        thread1.start();
        try {
            // 使当前线程睡3秒钟
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread2.start();
    }
}