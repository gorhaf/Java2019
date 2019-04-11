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
        // 获取value当前值
        System.out.println("设置新值前 --- " + value.get());
        // 设置新值
        value.set(1);
        // 获取value当前值
        System.out.println("设置新值后 --- " + value.get());
    }
}