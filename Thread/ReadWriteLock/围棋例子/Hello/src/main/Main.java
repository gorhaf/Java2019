package main;

import lab.Chess;

public class Main {

    /**
     * 主入口
     *
     * @param args 参数列表
     */
    public static void main(String[] args) {
        // 创建棋局
        Chess chess = new Chess();

        // 创建抄写棋局任务
        Runnable putRunnable = new Runnable() {
            @Override
            public void run() {
                // 抄写棋局
                chess.put();
            }
        };
        // 创建获取棋局信息任务
        Runnable takeRunnable = new Runnable() {
            @Override
            public void run() {
                // 获取棋局信息
                String step = chess.take();
                // 输出棋局信息
                System.out.println(step);
            }
        };

        new Thread() {
            @Override
            public void run() {
                try {
                    // 声明抄写棋局的线程
                    Thread putThread;
                    // 循环创建抄写棋局的人
                    for (int i = 0; i < 200; i++) {
                        // 使当前线程睡1秒钟
                        Thread.sleep(1000);
                        // 创建抄写棋局的线程
                        putThread = new Thread(putRunnable);
                        // 启动线程
                        putThread.start();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();

        new Thread() {
            @Override
            public void run() {
                // 声明观棋的线程
                Thread takeThread;
                // 循环创建观棋的线程
                for (; ; ) {
                    // 创建观棋的线程
                    takeThread = new Thread(takeRunnable);
                    // 启动线程
                    takeThread.start();
                }
            }
        }.start();
    }
}