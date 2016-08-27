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
 * description
 * 
 * @author luzhongguo
 * @version 1.0, 2016年8月9日
 */
public class QueueTest {

    public static void main(String[] args) {
	int[] datas = { 3, 4, 1, 6, 9, 5, 2, 7, 8, 10 };
	System.out.println("原始数据：");
	for (int i : datas) {
	    System.out.print(i + "\t");
	}

	// QueueArray object = new QueueArray();
	// QueueLink object = new QueueLink();
	StacksQueue object = new StacksQueue();
	for (int i = 0; i < datas.length; i++) {
	    object.offer(datas[i]);
	}

	System.out.println();
	System.out.println("出队列：");
	for (int i = 0; i < 5; i++) {
	    System.out.print(object.poll() + "\t");
	}

	for (int i = 0; i < 3; i++) {
	    object.offer(i);
	}

	System.out.println();
	System.out.println("当前队列实际大小：" + object.size());
    }
}
