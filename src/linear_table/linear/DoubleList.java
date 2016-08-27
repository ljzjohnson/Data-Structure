/*   
 * Copyright (c) 2010-2020 Founder LZG. All Rights Reserved.   
 *   
 * This software is the confidential and proprietary information of   
 * Founder. You shall not disclose such Confidential Information   
 * and shall use it only in accordance with the terms of the agreements   
 * you entered into with Founder.   
 *   
 */
package linear_table.linear;

/**
 * description
 * 
 * @author luzhongguo
 * @version 1.0, 2016年8月1日
 */
public class DoubleList extends List {
    private Node listHeader = null;
    private Node listTail = null;

    // 实例化一个双向链表，分别创建一个头结点和尾节点
    public DoubleList() {
	listHeader = new Node();
	listTail = new Node();
	listSize = 2;
	listHeader.nextNode = listTail;
	listTail.preNode = listHeader;
    }

    // 添加一个节点
    @Override
    public void add(int data) {
	Node node = new Node(data, null, null);
	node.nextNode = listTail;
	node.preNode = listTail.preNode;
	listTail.preNode.nextNode = node;
	listTail.preNode = node;
	listSize++;
    }

    // 双向链表通过修改尾节点，建立链表，所以输出是正序
    public void traverse() {
	Node item = listHeader.nextNode;
	while (item.nextNode != null) {
	    System.out.print(item.data + "\t");
	    item = item.nextNode;
	}
    }

    public boolean isEmpty() {
	if (listSize != 2) {
	    return true;
	}
	return false;
    }

    public boolean contain(int data) {
	Node item = listHeader.nextNode;
	while (item.nextNode != null) {
	    if (item.data == data)
		return true;
	    item = item.nextNode;
	}
	return false;
    }

    private class Node {
	public int data;
	public Node preNode;
	public Node nextNode;

	public Node() {
	}

	public Node(int data, Node preNode, Node nextNode) {
	    this.data = data;
	    this.preNode = preNode;
	    this.nextNode = nextNode;
	}
    }
}
