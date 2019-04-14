package main;

import lab.Person;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.function.IntBinaryOperator;

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
        // getAndAccumulate(T obj, int x, IntBinaryOperator accumulatorFunction)方法的作用是指定对象的字段与参数x进行二元运算，运算方式由参数accumulatorFunction决定，方法返回运算前的值。
        int result = fieldUpdater.getAndAccumulate(person, 4, new IntBinaryOperator() {
            @Override
            public int applyAsInt(int left, int right) {
                return left + right;
            }
        });
        // 输出结果
        System.out.println("getAndAccumulate(T obj, int x, IntBinaryOperator accumulatorFunction)方法执行结果：" + result);
        // accumulateAndGet(T obj, int x, IntBinaryOperator accumulatorFunction)方法的作用是指定对象的字段与参数x进行二元运算，运算方式由参数accumulatorFunction决定，方法返回运算后的值。
        result = fieldUpdater.accumulateAndGet(person, 5, new IntBinaryOperator() {
            @Override
            public int applyAsInt(int left, int right) {
                return left * right;
            }
        });
        // 输出结果
        System.out.println("accumulateAndGet(T obj, int x, IntBinaryOperator accumulatorFunction)方法执行结果：" + result);
    }
}
// get(T obj)方法的作用是获取指定对象的字段。
// set(T obj, int newValue)方法的作用是设置指定对象的字段的值。参数obj是对象，newValue是我们可以指定的新值。
// getAndIncrement(T obj)方法的作用是递增指定对象的字段的值，返回递增前的值。
// incrementAndGet(T obj)方法的作用是递增指定对象的字段的值，返回递增后的值。
// getAndDecrement(T obj)方法的作用是递减指定对象的字段的值，返回递减前的值。
// decrementAndGet(T obj)方法的作用是递减指定对象的字段的值，返回递减后的值。
// getAndAdd(T obj, int delta)方法的作用是指定对象的字段与delta相加，返回相加前的值。
// addAndGet(T obj, int delta)方法的作用是指定对象的字段与delta相加，返回相加后的值。
// getAndUpdate(T obj, IntUnaryOperator updateFunction)方法的作用是更新指定对象的字段，更新方式由参数updateFunction决定，方法返回更新前的值。
// updateAndGet(T obj, IntUnaryOperator updateFunction)方法的作用是更新指定对象的字段，更新方式由参数updateFunction决定，方法返回更新后的值。
// getAndAccumulate(T obj, int x, IntBinaryOperator accumulatorFunction)方法的作用是指定对象的字段与参数x进行二元运算，运算方式由参数accumulatorFunction决定，方法返回运算前的值。
// getAndAccumulate(T obj, int x, IntBinaryOperator accumulatorFunction)方法的作用是指定对象的字段与参数x进行二元运算，运算方式由参数accumulatorFunction决定，方法返回运算后的值。
// compareAndSet(T obj, int expect, int update)方法的作用是如果当value==expect，那么就将update赋给value，否则什么也不做。