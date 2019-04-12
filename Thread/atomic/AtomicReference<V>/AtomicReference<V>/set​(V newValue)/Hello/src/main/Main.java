package main;

import lab.Person;

import java.util.concurrent.atomic.AtomicReference;

public class Main {

    /**
     * 主入口
     *
     * @param args 参数列表
     */
    public static void main(String[] args) {
        // 创建AtomicReference对象
        AtomicReference<Person> value = new AtomicReference<>(new Person("Tom", 18));
        // 给value设置新值
        value.set(new Person("Jack",20));
        // 获取value当前值
        Person person = value.get();
        // 输出Person对象的姓名和年龄
        System.out.println(person.getName() + "：" + person.getAge());
    }
}