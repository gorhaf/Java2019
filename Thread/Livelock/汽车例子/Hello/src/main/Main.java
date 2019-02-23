package main;

import lab.Car;

public class Main {

    /**
     * 主入口
     *
     * @param args 参数列表
     */
    public static void main(String[] args) {
        // 创建汽车对象
        Car redCar = new Car("红色");
        Car blueCar = new Car("蓝色");

        // 设置汽车行驶的方向
        redCar.setDirection("上方");
        blueCar.setDirection("上方");

        // 创建线程
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                // 行驶汽车
                redCar.run();

                // 当我方汽车与对方汽车在同一车道上时
                while (redCar.getDirection().equals(blueCar.getDirection())) {
                    // 设置我方汽车行驶方向调整至另外一条车道上
                    redCar.setDirection(redCar.getDirection().equals("上方") ? "下方" : "上方");

                    // 行驶汽车
                    redCar.run();
                }
            }
        });
        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                // 行驶汽车
                blueCar.run();

                // 当我方汽车与对方汽车在同一车道上时
                while (blueCar.getDirection().equals(redCar.getDirection())) {
                    // 设置我方汽车行驶方向调整至另外一条车道上
                    blueCar.setDirection(blueCar.getDirection().equals("上方") ? "下方" : "上方");

                    // 行驶汽车
                    blueCar.run();
                }
            }
        });

        // 启动线程
        thread1.start();
        thread2.start();
    }
}