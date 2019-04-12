package main;

import lab.Person;

import java.util.concurrent.atomic.AtomicReference;
import java.util.function.BinaryOperator;

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
        Person person = value.accumulateAndGet(new Person(), new BinaryOperator<Person>() {
            @Override
            public Person apply(Person left, Person right) {
                right.setName("Jack");
                right.setAge(left.getAge() + 5);
                return right;
            }
        });
        // 输出Person对象的姓名和年龄
        System.out.println(person.getName() + "：" + person.getAge());
    }
}