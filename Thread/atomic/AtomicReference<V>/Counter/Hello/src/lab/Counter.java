package lab;

import java.util.concurrent.atomic.AtomicReference;
import java.util.function.UnaryOperator;

/**
 * 全局计数器
 */
public class Counter {

    /**
     * 当前值
     */
    private static AtomicReference<Integer> value = new AtomicReference<>(0);
    
    /**
     * 禁止创建对象
     */
    private Counter() {

    }

    /**
     * 获取当前值
     *
     * @return 返回value
     */
    public static int get() {
        return value.get();
    }

    /**
     * 递增
     */
    public static void increment() {
        value.getAndUpdate(new UnaryOperator<Integer>() {
            @Override
            public Integer apply(Integer integer) {
                return integer + 1;
            }
        });
    }
}