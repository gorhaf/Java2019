package main;

import java.util.concurrent.atomic.AtomicIntegerArray;
import java.util.function.IntBinaryOperator;
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
        // 指定下标元素进行二元运算并返回运算前的值
        int result = numbers.getAndAccumulate(0, 4, new IntBinaryOperator() {
            @Override
            public int applyAsInt(int left, int right) {
                return left + right;
            }
        });
        // 输出结果
        System.out.println("getAndAccumulate(int i, int x, IntBinaryOperator accumulatorFunction)方法执行结果：" + result);
        // 指定下标元素进行二元运算并返回运算后的值
        result = result = numbers.accumulateAndGet(0, 5, new IntBinaryOperator() {
            @Override
            public int applyAsInt(int left, int right) {
                return left * right;
            }
        });
        // 输出结果
        System.out.println("accumulateAndGet(int i, int x, IntBinaryOperator accumulatorFunction)方法执行结果：" + result);
    }
}