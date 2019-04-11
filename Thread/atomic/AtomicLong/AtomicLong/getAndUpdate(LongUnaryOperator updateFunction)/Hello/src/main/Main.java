package main;

import java.util.concurrent.atomic.AtomicLong;
import java.util.function.LongUnaryOperator;

public class Main {

    /**
     * 主入口
     *
     * @param args 参数列表
     */
    public static void main(String[] args) {
        // 创建AtomicLong对象
        AtomicLong value = new AtomicLong(0);
        // 返回更新前的值
        long result = value.getAndUpdate(new LongUnaryOperator() {
            @Override
            public long applyAsLong(long operand) {
                return operand + 1;
            }
        });
        // 输出结果
        System.out.println("返回更新前的值 --- " + result);
        // 返回更新后的值
        result = value.updateAndGet(new LongUnaryOperator() {
            @Override
            public long applyAsLong(long operand) {
                return operand * 2;
            }
        });
        // 输出结果
        System.out.println("返回更新后的值 --- " + result);
    }
}