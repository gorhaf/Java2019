package main;

import lab.Person;

import java.util.concurrent.atomic.AtomicReference;
import java.util.function.UnaryOperator;

public class Main {

    /**
     * 主入口
     *
     * @param args 参数列表
     */
    public static void main(String[] args) {
        // 创建AtomicReference对象
        AtomicReference<Person> value = new AtomicReference<>(new Person("Tom", 18));
        // 返回更新前的值
        Person oldPerson = value.getAndUpdate(new UnaryOperator<Person>() {
            @Override
            public Person apply(Person person) {
                // 设置新值
                return new Person("Jack",23);
            }
        });
        // 输出Person对象的姓名和年龄
        System.out.println(oldPerson.getName() + "：" + oldPerson.getAge());
    }
}