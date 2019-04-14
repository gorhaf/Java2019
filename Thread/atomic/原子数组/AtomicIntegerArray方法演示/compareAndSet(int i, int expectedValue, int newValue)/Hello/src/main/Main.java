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
        // 赋值之前获取一次下标为0的元素
        System.out.println("赋值之前：" + numbers.get(0));
        // 调用compareAndSet(int i, int expectedValue, int newValue)方法
        boolean result = numbers.compareAndSet(0, 0, 5);
        // 赋值是否成功
        System.out.println("赋值是否成功：" + result);
        // 赋值之后再获取一次下标为0的元素
        System.out.println("赋值之后：" + numbers.get(0));
    }
}