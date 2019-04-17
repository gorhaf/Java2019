package main;

import java.util.concurrent.atomic.AtomicStampedReference;

public class Main {

    /**
     * 主入口
     *
     * @param args 参数列表
     */
    public static void main(String[] args) throws InterruptedException {
        // 创建AtomicStampedReference对象
        AtomicStampedReference<Integer> value = new AtomicStampedReference<>(0, 0);
        // 调用set(V newReference, int newStamp)方法设置新值和新版本号
        value.set(1, 1);
        // 获取值和版本号
        System.out.println("值：" + value.getReference() + "，版本号：" + value.getStamp());
    }
}