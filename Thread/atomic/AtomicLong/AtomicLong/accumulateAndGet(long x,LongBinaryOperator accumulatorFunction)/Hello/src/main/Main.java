package main;

import java.util.concurrent.atomic.AtomicLong;
import java.util.function.LongBinaryOperator;

public class Main {

    /**
     * 主入口
     *
     * @param args 参数列表
     */
    public static void main(String[] args) {
        // 创建AtomicLong对象
        AtomicLong value = new AtomicLong(2);
        // 返回使用自定义运算方式更新前的值
        long result = value.getAndAccumulate(3, new LongBinaryOperator() {
            @Override
            public long applyAsLong(long left, long right) {
                return left * right;
            }
        });
        // 输出结果
        System.out.println("返回使用自定义运算方式更新前的值 --- " + result);
        // 返回使用自定义运算方式更新后的值
        result = value.accumulateAndGet(2, new LongBinaryOperator() {
            @Override
            public long applyAsLong(long left, long right) {
                return left * right;
            }
        });
        // 输出结果
        System.out.println("返回使用自定义运算方式更新后的值 --- " + result);
    }
}