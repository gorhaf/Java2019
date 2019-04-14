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
        // 设置指定下标元素的值
        numbers.set(0, 99);
        // 获取下标为0的元素
        int item = numbers.get(0);
        // 输出元素
        System.out.println(item);
    }
}