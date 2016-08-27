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
 * 队列相关操作：入队、出队、读队头元素、判断队空、遍历队、队长。 队的存储结构：循环数组、链接存储。
 * 
 * @author luzhongguo
 * @version 1.0, 2016年8月9日
 */
public abstract class Queue {

    public abstract boolean offer(int item);

    public abstract int poll();

    public abstract int peek();

    public abstract boolean isEmpty();

    public abstract int size();

    public abstract void traverse();
}
