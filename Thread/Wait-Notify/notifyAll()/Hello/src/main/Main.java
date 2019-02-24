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

        try {
            // 让主线程睡眠3秒钟
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // 同步代码块
        synchronized (Main.class){
            // 唤醒正在此对象监视器上等待的所有线程
            Main.class.notifyAll();
        }
    }

    /**
     * 同步静态方法
     */
    public static synchronized void say() {
        // wait()方法调用前输出语句
        System.out.println("我是" + Thread.currentThread().getName() + " --- wait前");

        try {
            // 调用同步对象的wait()方法
            Main.class.wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // wait()方法调用后输出语句
        System.out.println("我是" + Thread.currentThread().getName() + " --- wait后");
    }
}