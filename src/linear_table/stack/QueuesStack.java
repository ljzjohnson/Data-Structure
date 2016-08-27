/*   
 * Copyright (c) 2010-2020 Founder LZG. All Rights Reserved.   
 *   
 * This software is the confidential and proprietary information of   
 * Founder. You shall not disclose such Confidential Information   
 * and shall use it only in accordance with the terms of the agreements   
 * you entered into with Founder.   
 *   
 */
package linear_table.stack;

import java.util.LinkedList;

/**
 * 使用双队列实现栈(功能上的实现)。通过两个队列交替拷贝数据，每次只剩下一个，然后将其输出，即可实现后进先出的原则。注意这里实现，每次操作完成后，
 * 保证有一个队列是空。 这个只是考察使用队列实现栈， 个人觉得这样毫无价值可言，因为效率低下。
 * 
 * @author luzhongguo
 * @version 1.0, 2016年8月9日
 */
public class QueuesStack {
    LinkedList<Integer> queue1 = new LinkedList<Integer>();
    LinkedList<Integer> queue2 = new LinkedList<Integer>();

    public void push(int item) {
	if (queue1.isEmpty() && queue2.isEmpty()) {
	    queue1.offer(item);// 两个队列都为空时，队列1作为入队列。否则的话，以非空的队列作为入队列
	} else if (queue1.isEmpty()) {
	    queue2.offer(item);
	} else {
	    queue1.offer(item);
	}
    }

    public int pop() {
	if (queue1.isEmpty()) {
	    if (queue2.size() == 1) {
		return queue2.poll();
	    } else {
		while (queue2.size() > 1) {
		    queue1.offer(queue2.poll());
		}
		return queue2.poll();
	    }
	} else {
	    if (queue1.size() == 1) {
		return queue1.poll();
	    } else {
		while (queue1.size() > 1) {
		    queue2.offer(queue1.poll());
		}
		return queue1.poll();
	    }
	}
    }

    public int peek() {
	if (queue1.isEmpty()) {
	    if (queue2.size() == 1) {
		return queue2.peek();
	    } else {
		while (queue2.size() > 1) {
		    queue1.offer(queue2.poll());
		}
		int tmp = queue2.poll();
		queue1.offer(tmp);
		return tmp;
	    }
	} else {
	    if (queue1.size() == 1) {
		return queue1.poll();
	    } else {
		while (queue1.size() > 1) {
		    queue2.offer(queue1.poll());
		}
		int tmp = queue1.poll();
		queue2.offer(tmp);
		return tmp;
	    }
	}
    }

    public boolean isEmpty() {
	if (queue1.isEmpty() && queue2.isEmpty()) {
	    return true;
	} else {
	    return false;
	}
    }

    public int size() {
	return (queue1.size() + queue2.size());
    }

}
