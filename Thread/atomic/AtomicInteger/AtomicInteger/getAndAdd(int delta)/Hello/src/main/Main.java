package main;

import java.util.concurrent.atomic.AtomicInteger;

public class Main {

    /**
     * 主入口
     *
     * @param args 参数列表
     */
    public static void main(String[] args) {
        // 创建AtomicInteger对象
        AtomicInteger value = new AtomicInteger(0);
        // 先返回当前值再加上任意值
        System.out.println("先返回当前值再加上任意值：" + value.getAndAdd(3));
        // 先加上任意值再返回当前值
        System.out.println("先加上任意值再返回当前值：" + value.addAndGet(-1));
    }
}