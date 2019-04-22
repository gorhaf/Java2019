package main;

import lab.ConcurrentStack;

import java.util.ArrayList;
import java.util.Comparator;

public class Main {

    // 计数器
    private static int count = 0;

    public static void main(String[] args) {
        // 创建ConcurrentStack对象
        ConcurrentStack<Integer> stack = new ConcurrentStack<>();
        // 存储所有数字
        ArrayList<Integer> numbers = new ArrayList<>();
        // 循环创建100个线程执行入栈任务
        for (int i = 0; i < 100; i++) {
            new Thread() {
                @Override
                public void run() {
                    // 入栈
                    stack.push(count++);
                }
            }.start();
        }
        // 循环创建100个线程执行出栈任务
        for (int i = 0; i < 100; i++) {
            new Thread() {
                @Override
                public void run() {
                    // 出栈
                    numbers.add(stack.pop());
                }
            }.start();
        }
        // 排序
        numbers.sort(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                // 大于返回大于0的数
                if (o1 > o2) {
                    return 1;
                }
                // 等于返回0
                else if (o1.equals(o2)) {
                    return 0;
                }
                // 小于返回小于0的数
                return -1;
            }
        });
        // 输出
        for (Integer number : numbers) {
            System.out.println(number);
        }
    }
}