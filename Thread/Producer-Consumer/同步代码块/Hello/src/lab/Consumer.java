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
        // 无限循环
        while (true){
            // 同步对象为data的同步代码块
            synchronized (data) {
                // 当数据信息为空时
                while (data.getMessage() == null) {
                    try {
                        // 等待
                        data.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                // 消费数据信息
                System.out.println("数据信息：" + data.getMessage());

                // 将数据对象里面的数据信息设置为null
                data.setMessage(null);

                // 唤醒正在此对象监视器上等待的所有线程
                data.notifyAll();
            }
        }
    }
}