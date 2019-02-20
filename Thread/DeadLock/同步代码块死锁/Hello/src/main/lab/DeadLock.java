package lab;

/**
 * 死锁
 */
public class DeadLock implements Runnable {

    /**
     * 同步锁：类型为Object的lock1对象
     */
    private final Object lock1 = new Object();

    /**
     * 同步锁：类型为Object的lock2对象
     */
    private final Object lock2 = new Object();

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
}