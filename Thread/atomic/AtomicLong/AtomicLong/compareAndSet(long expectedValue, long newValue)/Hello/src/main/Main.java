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
        System.out.println("赋值之前：" + value.get());
        // 赋值
        boolean successed = value.compareAndSet(0, 2);
        // 输出赋值是否成功结果
        System.out.println("赋值是否成功：" + successed);
        // 获取value当前值
        System.out.println("赋值之后：" + value.get());
    }
}