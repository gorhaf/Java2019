package main;

import lab.Person;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

public class Main {

    /**
     * 主入口
     *
     * @param args 参数列表
     */
    public static void main(String[] args) {
        // 字段原子更新器
        AtomicIntegerFieldUpdater<Person> fieldUpdater = AtomicIntegerFieldUpdater.newUpdater(Person.class, "age");
        // 创建Person对象
        Person person = new Person();
        // 更新Person对象中的age字段任务
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                try {
                    // 使当前线程睡100毫秒
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                // 递增age
                fieldUpdater.getAndIncrement(person);
            }
        };
        // 循环创建100个线程并执行更新任务
        for (int i = 0; i < 100; i++) {
            new Thread(runnable).start();
        }
        try {
            // 使当前线程睡1秒钟
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // 输出Person对象的age字段
        System.out.println(person.age);
    }
}