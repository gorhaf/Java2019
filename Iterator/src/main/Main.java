package main;

import lab.Iterator;
import lab.NumberList;
import lab.TextList;

public class Main {

    /**
     * 主入口
     *
     * @param args 参数列表
     */
    public static void main(String[] args) {
        // 创建数字集合
        NumberList numberList = new NumberList();
        // 添加元素
        for (int i = 0; i < 20; i++) {
            numberList.add(i);
        }

        // 遍历元素
        iteration(numberList.iterator());
        // 打印数字集合
        printList(numberList);

        // 创建文字集合
        TextList textList = new TextList();
        // 添加元素
        textList.add("Hi");
        textList.add("Java");
        textList.add("Hello");

        // 遍历元素
        iteration(textList.iterator());
    }

    /**
     * 打印数字集合
     *
     * @param numberList 数字集合
     */
    private static void printList(NumberList numberList) {
        iteration(numberList.iterator());
    }

    /**
     * 迭代元素
     *
     * @param iterator 迭代器
     */
    public static void iteration(Iterator iterator){
        while (iterator.hasNext()){
            Object item = iterator.next();
            System.out.println(item);
        }
    }
}