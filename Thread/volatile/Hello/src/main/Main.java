package main;

public class Main {

    /**
     * 是否停止运行
     */
    private static volatile boolean isStop = false;

    /**
     * 主入口
     *
     * @param args 参数列表
     */
    public static void main(String[] args) {
        // 创建线程
        Thread thread = new Thread() {
            @Override
            public void run() {
                // 当不停止运行时
                while (!isStop) {

                }
            }
        };
        // 输出isStop的值
        System.out.println("启动thread线程前 --- " + isStop);
        // 启动线程
        thread.start();
        try {
            // 使当前线程睡1秒钟
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // 将isStop设置为true
        isStop = true;
        // 输出isStop的值
        System.out.println("设置完isStop值后 --- " + isStop);
    }
}