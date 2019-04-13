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
        // 指定下标元素与任意值相加并返回相加前的值
        int result = numbers.getAndAdd(0, 2);
        // 输出结果
        System.out.println("getAndAdd(int i, int delta)方法执行结果：" + result);
        // 指定下标元素与任意值相加并返回相加后的值
        result = numbers.addAndGet(0, -3);
        // 输出结果
        System.out.println("addAndGet(int i, int delta)方法执行结果：" + result);
    }
}