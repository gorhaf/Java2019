package lab;

/**
 * 生产者任务
 */
public class Producer implements Runnable {

    /**
     * 数据
     */
    private Data data;

    /**
     * 计数器
     */
    private int count = 0;

    /**
     * 构造方法
     *
     * @param data 数据
     */
    public Producer(Data data) {
        // 设置数据
        this.data = data;
    }

    /**
     * 将需要线程执行的任务写在run()方法中
     */
    @Override
    public void run() {
        // 无限循环
        while (true) {

            try {
                // 让当前线程睡1秒钟
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // 同步对象为data的同步代码块
            synchronized (data) {

                // 当数据信息不为空时，即还有数据信息未消费
                while (data.getMessage() != null) {
                    try {
                        // 使当前生产者线程等待
                        data.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                // 数据信息
                String message = "你好！" + count++;

                // 设置数据信息
                data.setMessage(message);

                // 唤醒正在此对象监视器上等待的所有线程
                data.notifyAll();
            }
        }
    }
}