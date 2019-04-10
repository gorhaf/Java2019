package main;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.IntBinaryOperator;

public class Main {

    /**
     * 主入口
     *
     * @param args 参数列表
     */
    public static void main(String[] args) {
        // 创建AtomicInteger对象
        AtomicInteger value = new AtomicInteger(2);
        // 返回当前值再使用自定义运算方式更新当前值
        int result = value.getAndAccumulate(3, new IntBinaryOperator() {
            @Override
            public int applyAsInt(int left, int right) {
                return left * right;
            }
        });
        // 输出结果
        System.out.println("运算结果：" + result);
        // 先使用自定义运算方式更新当前值再返回当前值
        result = value.accumulateAndGet(2, new IntBinaryOperator() {
            @Override
            public int applyAsInt(int left, int right) {
                return left * right;
            }
        });
        // 输出结果
        System.out.println("运算结果：" + result);
    }
}