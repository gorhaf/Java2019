package lab;

/**
 * 文字集合
 */
public class TextList {

    /**
     * 存储文字的数组
     */
    private String[] items = new String[10];

    /**
     * 记录当前存储元素位置
     */
    private int position = 0;

    /**
     * 添加元素
     *
     * @param text 文字
     */
    public void add(String text) {
        // 当当前位置不为文字容器最后一个位置时，换言之就是文字容器未满时
        if (position < items.length) {
            // 向容器中添加元素
            items[position] = text;
            // 当前位置往后移一位
            position++;
        }
    }

    /**
     * 迭代器
     */
    private class Itr implements Iterator{

        /**
         * 记录当前遍历元素的位置
         */
        private int next = 0;

        /**
         * 是否还有下一个元素
         *
         * @return true：还有下一个；false：没有下一个。
         */
        @Override
        public boolean hasNext() {
            return next < items.length;
        }

        /**
         * 获取下一个元素
         *
         * @return 返回下一个元素
         */
        @Override
        public Object next() {
            return items[next++];
        }
    }

    /**
     * 获取迭代器
     *
     * @return 迭代器对象
     */
    public Iterator iterator(){
        return new Itr();
    }
}