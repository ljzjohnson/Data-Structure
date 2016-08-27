/*   
 * Copyright (c) 2010-2020 Founder LZG. All Rights Reserved.   
 *   
 * This software is the confidential and proprietary information of   
 * Founder. You shall not disclose such Confidential Information   
 * and shall use it only in accordance with the terms of the agreements   
 * you entered into with Founder.   
 *   
 */
package linear_table.queue;

/**
 * 使用循环数组实现环状队列，可以避免假溢出现象。 循环数组逻辑结构上，就是将数组首尾相连，形成环。rear = (rear + 1) %
 * maxSize,front = (front + 1) % maxSize。 maxSize
 * 
 * @author luzhongguo
 * @version 1.0, 2016年8月8日
 */
public class QueueArray extends Queue {

    private final static int QUEUE_DEFAULT_SIZE = 10;// 默认队列大小
    private int[] queue;// 实现队列的循环数组
    private int size;// 队列实际大小

    private int front;
    private int rear;

    // 使用循环数组实现队列(环状队列)
    public QueueArray() {
	queue = new int[QUEUE_DEFAULT_SIZE];
	front = rear = 0;
    }

    /**
     * 入队
     * 
     * @param item
     * @return
     */
    public boolean offer(int item) {
	if (size != QUEUE_DEFAULT_SIZE) {
	    queue[rear] = item;
	    rear = (rear + 1) % QUEUE_DEFAULT_SIZE;
	    size++;
	} else {
	    System.out.println("队列已满！");
	    return false;// 对列满
	}
	return true;
    }

    /**
     * 出队
     * 
     * @return
     */
    public int poll() {
	if (size != 0) {
	    int tmp = queue[front];
	    front = (front + 1) % QUEUE_DEFAULT_SIZE;
	    size--;
	    return tmp;
	} else {
	    return Integer.MAX_VALUE;
	}
    }

    /**
     * 读取队头元素
     * 
     * @return
     */
    public int peek() {
	if (size != 0) {
	    return queue[front];
	} else {
	    return Integer.MAX_VALUE;
	}
    }

    public boolean isEmpty() {
	if (size != 0) {
	    return false;
	} else {
	    return true;
	}
    }

    public int size() {
	return size;
    }

    public void traverse() {
	if (size != 0) {
	    System.out.println("队列遍历：");
	    for (int i = 0; i < size; i++) {
		System.out.print(queue[i] + "\t");
	    }
	    System.out.println();
	} else {
	    System.out.println("队列为空！");
	}
    }

}
