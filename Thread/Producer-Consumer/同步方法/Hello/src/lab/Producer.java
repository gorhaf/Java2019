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
            // 设置数据信息
            data.setMessage("你好！");
        }
    }
}