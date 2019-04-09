package main;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.IntUnaryOperator;

public class Main {

    /**
     * 主入口
     *
     * @param args 参数列表
     */
    public static void main(String[] args) {
        // 创建AtomicInteger对象
        AtomicInteger value = new AtomicInteger(0);
        // 先返回当前值再更新为指定值
        System.out.println("先返回当前值再更新为指定值：" + value.getAndUpdate(new IntUnaryOperator() {
            @Override
            public int applyAsInt(int operand) {
                return 1;
            }
        }));
        // 先更新为指定值再返回当前值
        System.out.println("先更新为指定值再返回当前值：" + value.updateAndGet(new IntUnaryOperator() {
            @Override
            public int applyAsInt(int operand) {
                return operand + 1;
            }
        }));
    }
}