package main;

import lab.CachedData;

public class Main {

    /**
     * 主入口
     *
     * @param args 参数列表
     */
    public static void main(String[] args) {
        // 创建缓存数据对象
        CachedData cachedData = new CachedData();

        // 创建设置缓存数据线程并启动
        new Thread() {
            @Override
            public void run() {
                // 设置缓存数据
                cachedData.setCachedData("hi,ReadWriteLock!");
            }
        }.start();

        // 获取缓存数据任务
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                // 获取缓存数据
                String data = cachedData.getCachedData();
                // 输出缓存数据
                System.out.println(data);
            }
        };
        // 无限循环
        while (true) {
            // 创建读取线程并启动
            new Thread(runnable).start();
        }
    }
}