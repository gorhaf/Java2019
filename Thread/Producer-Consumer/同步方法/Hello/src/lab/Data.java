package lab;

/**
 * 数据
 */
public class Data {

    /**
     * 数据信息
     */
    private String message;

    /**
     * 数据信息是否已消费
     */
    private boolean empty = true;

    /**
     * 计数器
     */
    private int count = 0;

    /**
     * 设置数据信息
     *
     * @param message 数据信息
     */
    public synchronized void setMessage(String message) {
        // 当数据信息不为空时，即还有数据信息未消费
        while (!empty) {
            try {
                // 使当前生产者线程等待
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        // 数据信息没有被消费
        empty = false;
        // 设置数据信息
        this.message = message + count++;

        try {
            // 让当前线程睡1秒钟
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // 唤醒正在此对象监视器上等待的所有线程
        notifyAll();
    }

    /**
     * 获取数据信息
     *
     * @return 数据信息
     */
    public synchronized String getMessage() {
        // 当数据信息为空时
        while (empty) {
            try {
                // 等待
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        // 数据信息已经被消费
        empty = true;
        // 唤醒正在此对象监视器上等待的所有线程
        notifyAll();
        // 返回数据信息
        return message;
    }
}