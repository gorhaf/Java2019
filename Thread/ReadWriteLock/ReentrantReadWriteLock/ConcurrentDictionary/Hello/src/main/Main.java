package main;

import lab.ConcurrentDictionary;

import java.util.List;

public class Main {

    /**
     * 主入口
     *
     * @param args 参数列表
     */
    public static void main(String[] args) {
        // 创建ConcurrentDictionary实例
        ConcurrentDictionary dictionary = new ConcurrentDictionary();
        // 存入数据
        dictionary.put("Java", "11");
        dictionary.put("Python", "22");
        dictionary.put("Go", "33");
        // 清空容器
        dictionary.clear();
        // 获取容器中所有的键
        List<String> allKeys = dictionary.allKeys();
        // 遍历所有的键
        for (String key : allKeys) {
            System.out.println(key);
        }
    }
}