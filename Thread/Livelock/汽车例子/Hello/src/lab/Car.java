package lab;

/**
 * 汽车
 */
public class Car {

    /**
     * 汽车行驶的方向
     */
    private String direction;

    /**
     * 汽车的颜色
     */
    private String color;

    /**
     * 构造方法
     *
     * @param color 汽车的颜色
     */
    public Car(String color) {
        // 设置汽车的颜色
        this.color = color;
    }

    /**
     * 行驶汽车
     */
    public void run() {
        // 输出语句
        System.out.println(color + "汽车行驶在" + getDirection() + "车道。");
    }

    /**
     * 获取汽车行驶的方向
     *
     * @return 汽车行驶的方向
     */
    public String getDirection() {
        return direction;
    }

    /**
     * 设置汽车行驶的方向
     *
     * @param direction 汽车行驶的方向
     */
    public void setDirection(String direction) {
        this.direction = direction;
    }
}