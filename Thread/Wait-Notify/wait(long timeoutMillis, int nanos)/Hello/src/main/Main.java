package main;

public class Main {

    /**
     * 主入口
     *
     * @param args 参数列表
     */
    public static void main(String[] args) {
        // 创建线程
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                // 调用say()方法
                say();
            }
        });
        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                // 调用say()方法
                say();
            }
        });

        // 启动线程
        thread1.start();
        thread2.start();
    }

    /**
     * 同步静态方法
     */
    public static synchronized void say() {
        // wait()方法调用前输出语句
        System.out.println("我是" + Thread.currentThread().getName() + " --- wait前");

        try {
            // 调用同步对象的wait(long timeoutMillis, int nanos)方法
            Main.class.wait(1000,1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // wait()方法调用后输出语句
        System.out.println("我是" + Thread.currentThread().getName() + " --- wait后");
    }
}