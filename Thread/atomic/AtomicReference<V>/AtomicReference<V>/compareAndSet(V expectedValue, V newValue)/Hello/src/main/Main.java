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
        // 创建两个Person对象
        Person person1 = new Person("Tom", 18);
        Person person2 = new Person("Jack", 23);
        // 创建AtomicReference对象
        AtomicReference<Person> value = new AtomicReference<>(person1);
        // 获取value当前值
        Person person = value.get();
        // 输出Person对象的姓名和年龄
        System.out.println("赋值之前：" + person.getName() + "：" + person.getAge());
        // CAS
        boolean successed = value.compareAndSet(person1, person2);
        // 输出赋值结果
        System.out.println("赋值是否成功：" + successed);
        // 获取value当前值
        person = value.get();
        // 输出Person对象的姓名和年龄
        System.out.println("赋值之后：" + person.getName() + "：" + person.getAge());
    }
}