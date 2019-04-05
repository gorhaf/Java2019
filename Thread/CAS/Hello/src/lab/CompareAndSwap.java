package lab;

/**
 * 比较并交换CAS技术
 */
public class CompareAndSwap {

    /**
     * 值
     */
    private int value;

    /**
     * 获取原值
     *
     * @return 原值
     */
    public synchronized int get() {
        return value;
    }

    /**
     * 比较并交换
     *
     * @param expectedValue 预期原值
     * @param newValue      新值
     * @return 原值
     */
    public synchronized int compareAndSwap(int expectedValue, int newValue) {
        // 获取原值
        int oldValue = value;
        // 当预期原值与原值相等时
        if (oldValue == expectedValue) {
            // 赋新值
            value = newValue;
        }
        // 返回原值
        return oldValue;
    }

    /**
     * 比较并交换操作是否成功
     *
     * @param expectedValue 预期原值
     * @param newValue      新值
     * @return 当新值设置成功时返回true，否则返回false。
     */
    public synchronized boolean compareAndSet(int expectedValue, int newValue) {
        // 当预期原值等于compareAndSwap(int expectedValue, int newValue)方法返回的原值时，方法返回true，否则返回false。
        return expectedValue == compareAndSwap(expectedValue, newValue);
    }
}