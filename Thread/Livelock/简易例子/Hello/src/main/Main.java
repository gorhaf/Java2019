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
                while (!"hello".contains("a")) {
                    System.out.println("线程thread1");
                }
            }
        });
        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                while (!"hello".contains("b")) {
                    System.out.println("线程thread2");
                }
            }
        });

        // 启动线程
        thread1.start();
        thread2.start();
    }
}