package lab;

import java.util.concurrent.atomic.AtomicReference;

/**
 * 非阻塞栈
 * ConcurrentStack中说明了非阻塞算法的所有特性：某项工作的完成具有不确定性，必须重新执行。
 * 在像ConcurrentStack这样的非阻塞算法中都能确保线程安全性，因为compareAndSet像锁定机制一样，既能提供原子性，又能提供可见性。
 * 当一个线程需要改变栈的状态时，将调动compareAndSet，这个方法与写入volaitle类型的变量有着相同的内存效果。
 * 当线程检查栈的状态时，将在同一个AtomicReference上调用get方法，这个方法与读取volaitle类型的变量有着相同的内存效果。
 * 因此，一个线程执行的任何修改结构都可以安全地发布给其他正在查看状态的线程。
 * 并且，这个栈是通过compareAndSet来修改的，因此将采用原子操作来更新top的引用，或者在发现存在其他线程干扰的情况下，修改操作将失败。
 *
 * @param <E> 泛型
 */
public class ConcurrentStack<E> {

    /**
     * 栈顶
     */
    private AtomicReference<Node<E>> top = new AtomicReference<>();

    /**
     * 入栈
     *
     * @param item 元素
     */
    public void push(E item) {
        // 创建出新节点
        Node<E> newNode = new Node<>(item);
        // 栈顶节点
        Node<E> oldHead;
        // 无限循环，直到栈顶更新成功
        do {
            // 获取栈顶节点
            oldHead = top.get();
            // 新节点替代栈顶节点，旧节点指向新节点
            newNode.next = oldHead;
            // 更新栈顶
        } while (!top.compareAndSet(oldHead, newNode));
    }

    /**
     * 出栈
     *
     * @return 栈顶元素
     */
    public E pop() {
        // 旧栈顶节点
        Node<E> oldNode;
        // 新栈顶节点
        Node<E> nextNode;
        do {
            // 获取旧栈顶节点
            oldNode = top.get();
            // 当栈顶节点为null时
            if (oldNode == null) {
                return null;
            }
            // 取出下一个节点
            nextNode = oldNode.next;
            // 设置为栈顶
        } while (!top.compareAndSet(oldNode, nextNode));
        // 返回栈顶数据
        return oldNode.item;
    }

    /**
     * 静态内部类：节点
     *
     * @param <E> 泛型
     */
    private static class Node<E> {

        /**
         * 元素
         */
        public final E item;

        /**
         * 指向下一个节点
         */
        public Node<E> next;

        /**
         * 节点构造方法
         *
         * @param item 元素
         */
        public Node(E item) {
            this.item = item;
        }
    }
}