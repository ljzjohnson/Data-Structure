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

import java.util.Stack;

/**
 * 使用双栈实现队列(功能上的实现)。入队时，将数据压入栈1，出栈时，如果栈2为空，将栈1中的数据全部输出到栈2，然后再从栈中输出数据；
 * 如果不为空，则直接从栈2中输出数据。两个栈分别维护入队数据和出队数据，相互独立。
 * 
 * @author luzhongguo
 * @version 1.0, 2016年8月9日
 */
public class StacksQueue {
    private Stack<Integer> stack1 = new Stack<Integer>();// 保存入队数据
    private Stack<Integer> stack2 = new Stack<Integer>();// 保存出队数据

    public StacksQueue() {
    }

    // 入队
    public void offer(int item) {
	stack1.push(item);
    }

    // 读取队头数据，但删除队头
    public int poll() {
	if (stack2.empty()) {
	    while (!stack1.empty()) {
		stack2.push(stack1.pop());
	    }
	    return stack2.pop();
	} else {
	    return stack2.pop();
	}
    }

    // 读取队头数据，但不删除队头
    public int peek() {
	if (stack2.empty()) {
	    while (!stack1.empty()) {
		stack2.push(stack1.pop());
	    }
	    return stack2.peek();
	} else {
	    return stack2.peek();
	}
    }

    public boolean isEmpty() {
	if (stack1.isEmpty() && stack2.isEmpty()) {
	    return true;
	} else {
	    return false;
	}
    }

    public int size() {
	return (stack1.size() + stack2.size());
    }

}
