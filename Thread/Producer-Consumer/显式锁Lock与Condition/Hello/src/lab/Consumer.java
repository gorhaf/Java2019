package lab;

/**
 * 消费者任务
 */
public class Consumer implements Runnable {

    /**
     * 数据
     */
    private Data data;

    /**
     * 构造方法
     *
     * @param data 数据
     */
    public Consumer(Data data) {
        // 设置数据
        this.data = data;
    }

    /**
     * 将需要线程执行的任务写在run()方法中
     */
    @Override
    public void run() {
        try {
            // 无限循环
            while (true){
                // 消费数据信息
                System.out.println("数据信息：" + data.getMessage());
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}