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
        // 调用compareAndSet(int expectedValue, int newValue)方法之前获取一次value的值
        System.out.println("赋值之前：" + value.get());
        // 调用compareAndSet(int expectedValue, int newValue)方法
        System.out.println("赋值是否成功：" + value.compareAndSet(0, 2));
        // 再获取一次value的值
        System.out.println("赋值之后：" + value.get());
    }
}