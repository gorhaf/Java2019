package main;

import java.util.concurrent.atomic.AtomicLong;

public class Main {

    /**
     * 主入口
     *
     * @param args 参数列表
     */
    public static void main(String[] args) {
        // 创建AtomicLong对象
        AtomicLong value = new AtomicLong(0);
        // 返回加上任意值前的值
        System.out.println("返回加上任意值前的值 --- " + value.getAndAdd(3));
        // 返回加上任意值后的值
        System.out.println("返回加上任意值后的值 --- " + value.addAndGet(-1));
    }
}