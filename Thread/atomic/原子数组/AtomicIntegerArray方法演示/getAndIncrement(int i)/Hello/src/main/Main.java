package main;

import java.util.concurrent.atomic.AtomicIntegerArray;

public class Main {

    /**
     * 主入口
     *
     * @param args 参数列表
     */
    public static void main(String[] args) {
        // 创建AtomicIntegerArray对象并指定内置数组长度
        AtomicIntegerArray numbers = new AtomicIntegerArray(10);
        // 递增指定下标元素并返回递增前的值
        int result = numbers.getAndIncrement(0);
        // 输出结果
        System.out.println("getAndIncrement(int i)方法执行结果："+result);
        // 递增指定下标元素并返回递增后的值
        result = numbers.incrementAndGet(0);
        // 输出结果
        System.out.println("incrementAndGet(int i)方法执行结果："+result);
    }
}