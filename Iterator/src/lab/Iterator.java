package lab;

/**
 * 迭代器
 */
public interface Iterator {

    /**
     * 判断是否还有下一个元素
     *
     * @return true：还有下一个；false：没有下一个。
     */
    boolean hasNext();

    /**
     * 获取下一个元素
     *
     * @return 返回下一个元素
     */
    Object next();
}