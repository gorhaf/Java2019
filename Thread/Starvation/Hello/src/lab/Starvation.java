package lab;

/**
 * 饥饿线程示例
 */
public class Starvation {

    /**
     * 同步方法
     */
    public synchronized void task() {
        try {
            // 使当前线程睡眠5秒钟，以表示该同步方法需要很久的执行时间
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // 输出语句，目的是表示该同步方法执行完毕！
        System.out.println("你好，饥饿线程！");
    }
}