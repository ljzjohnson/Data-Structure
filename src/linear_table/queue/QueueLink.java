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
 * 使用单向链表实现队列。JAVA的jdk使用LinkedList实现队
 * 
 * @author luzhongguo
 * @version 1.0, 2016年8月9日
 */
public class QueueLink extends Queue {
    private class Node {
	public int data;
	public Node next;

	public Node() {
	}

	public Node(int data) {
	    this.data = data;
	    next = null;
	}
    }

    private Node head;// front==head,队头
    private Node currentNode;// rear==currentNode
    private int size = 0;

    public QueueLink() {
	head = new Node();
	currentNode = head;
    }

    public boolean offer(int data) {
	Node node = new Node(data);
	currentNode.next = node;
	currentNode = node;
	size++;
	return true;
    }

    public int poll() {
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

    public void traverse() {
	Node tmpNode = head.next;
	if (size != 0) {
	    while (tmpNode != null) {
		System.out.print(tmpNode.data + "\t");
		tmpNode = tmpNode.next;
	    }
	} else {
	    System.out.println("队列为空！");
	}
    }

}
