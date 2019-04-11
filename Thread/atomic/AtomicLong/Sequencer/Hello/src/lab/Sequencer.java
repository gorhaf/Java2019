package lab;

import java.util.concurrent.atomic.AtomicLong;

/**
 * 生产唯一序列号
 */
public class Sequencer {

    /**
     * 序列号
     */
    private static AtomicLong sequenceNumber = new AtomicLong(0);

    /**
     * 禁止创建对象
     */
    private Sequencer() {

    }

    /**
     * 获取当前序列号
     *
     * @return 序列号
     */
    public static long getSequenceNumber() {
        return sequenceNumber.get();
    }

    /**
     * 生成下一个序列号
     *
     * @return 序列号
     */
    public static long next() {
        return sequenceNumber.getAndIncrement();
    }
}