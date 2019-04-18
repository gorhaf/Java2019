package main;

import java.util.concurrent.atomic.AtomicMarkableReference;

public class Main {

    /**
     * 主入口
     *
     * @param args 参数列表
     */
    public static void main(String[] args) {
        // 创建AtomicMarkableReference对象
        AtomicMarkableReference<Integer> value = new AtomicMarkableReference<>(0, false);
        // 修改之前
        System.out.println("修改之前，值：" + value.getReference() + "，修改标记：" + value.isMarked());
        // 设置新值及新修改标记
        boolean result = value.compareAndSet(0, 1, false, true);
        // 输出赋值结果
        System.out.println("赋值是否成功：" + result);
        // 修改之后
        System.out.println("修改之后，值：" + value.getReference() + "，修改标记：" + value.isMarked());
    }
}