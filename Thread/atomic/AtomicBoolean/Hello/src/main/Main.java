package main;

import java.util.concurrent.atomic.AtomicBoolean;

public class Main {

    /**
     * 应用已初始化
     */
    private static AtomicBoolean initialized = new AtomicBoolean(false);

    /**
     * 主入口
     *
     * @param args 参数列表
     */
    public static void main(String[] args) {
        // 创建线程并启动
        new Thread() {
            @Override
            public void run() {
                // 大家自行指定运行什么代码
                while (!initialized.get()) {

                }
            }
        }.start();
        try {
            // 使当前线程睡100毫秒
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // 应用已初始化
        initialized.set(true);
    }
}