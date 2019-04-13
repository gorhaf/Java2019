package main;

import java.util.concurrent.atomic.AtomicIntegerArray;
import java.util.function.IntUnaryOperator;

public class Main {

    /**
     * 主入口
     *
     * @param args 参数列表
     */
    public static void main(String[] args) {
        // 创建AtomicIntegerArray对象并指定内置数组长度
        AtomicIntegerArray numbers = new AtomicIntegerArray(10);
        // 使用自定义方式更新指定下标元素并返回更新前的值
        int result = numbers.getAndUpdate(0, new IntUnaryOperator() {
            @Override
            public int applyAsInt(int operand) {
                return operand + 5;
            }
        });
        // 输出结果
        System.out.println("getAndUpdate(int i, IntUnaryOperator updateFunction)方法执行结果：" + result);
        // 使用自定义方式更新指定下标元素并返回更新后的值
        result = numbers.updateAndGet(0, new IntUnaryOperator() {
            @Override
            public int applyAsInt(int operand) {
                return operand * 2;
            }
        });
        // 输出结果
        System.out.println("updateAndGet(int i, IntUnaryOperator updateFunction)方法执行结果：" + result);
    }
}