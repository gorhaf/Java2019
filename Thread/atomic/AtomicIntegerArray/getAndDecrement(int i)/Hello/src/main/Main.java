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
        // 递减指定下标元素并返回递减前的值
        int result = numbers.getAndDecrement(0);
        // 输出结果
        System.out.println("getAndDecrement(int i)方法执行结果：" + result);
        // 递减指定下标元素并返回递减后的值
        result = numbers.decrementAndGet(0);
        // 输出结果
        System.out.println("decrementAndGet(int i)方法执行结果：" + result);
    }
}