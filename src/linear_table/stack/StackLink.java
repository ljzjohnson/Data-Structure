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

/**
 * description
 * 
 * @author luzhongguo
 * @version 1.0, 2016年8月9日
 */
public class StackLink extends Stack {

    private class Node {
	public int data;
	public Node next;

	public Node() {
	}

	public Node(int data) {
	    this.data = data;
	}
    }

    private Node head;// front==head,队头
    private int size = 0;

    public StackLink() {
	head = new Node();
    }

    public boolean push(int data) {
	Node node = new Node(data);
	node.next = head.next;
	head.next = node;
	size++;
	return true;
    }

    public int pop() {
	if (size != 0) {
	    int tmp = head.next.data;
	    head.next = head.next.next;
	    size--;
	    return tmp;
	} else {
	    return Integer.MAX_VALUE;
	}
    }

    public int peek() {
	if (size != 0) {
	    return head.next.data;
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

}
