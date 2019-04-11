package main;

import lab.Sequencer;

public class Main {

    /**
     * 主入口
     *
     * @param args 参数列表
     */
    public static void main(String[] args) {
        // 生成序列号任务
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                try {
                    // 使当前线程睡100毫秒
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                // 生成序列号
                System.out.println(Sequencer.next());
            }
        };
        // 循环创建线程执行生成序列号任务
        for (int i = 0; i < 100; i++) {
            new Thread(runnable).start();
        }
        try {
            // 使当前线程睡1秒钟
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // 生成序列号
        System.out.println(Sequencer.next());
    }
}