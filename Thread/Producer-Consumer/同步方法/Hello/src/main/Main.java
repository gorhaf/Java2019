package main;

import lab.Consumer;
import lab.Data;
import lab.Producer;

public class Main {

    /**
     * 主入口
     *
     * @param args 参数列表
     */
    public static void main(String[] args) {
        // 数据
        Data data = new Data();

        // 创建生产者任务和消费者任务
        Producer producer = new Producer(data);
        Consumer consumer = new Consumer(data);

        // 创建生产者线程和消费者线程
        Thread producerThread1 = new Thread(producer);
        Thread consumerThread1 = new Thread(consumer);

        // 启动线程
        producerThread1.start();
        consumerThread1.start();
    }
}