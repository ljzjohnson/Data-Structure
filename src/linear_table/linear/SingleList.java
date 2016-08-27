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

import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;

//编程时，在能解决问题的情况下，还要考虑时间复杂度和空间复杂度。一般在时间复杂度相同的情况下，使空间复杂度尽量小。时间复杂度优先
/*
 * 链表中最常见的操作就是引用之间的赋值。注意，这里引用仅仅是一个地址，已创建的对象是不改变的。除非利用引用修改对象中的数据。
 * 一般需要使用临时引用变量,不改变原有引用变量的指向。比较经典的链表引用操作是链表构造的使用方式。
 */
/*
 * 防御性编程是一种编程习惯，是指预见在什么地方可能会出现问题，并为之提出相应的解决方案。
 * 简单的防御性编程是在函数的入口处做一些检查，尤其对有参数的函数。这样可以提高代码的鲁棒性。
 * 总之要做到很好的防御性编程，要多问几个“如果不。。。那么。。。”这样的问题。
 * 编程：基本功能+鲁棒性（主要是对参数的特殊情况进行考虑，如空指针、整数的数据类型（有符号、无符号等））
 */
/**
 * 编程不仅要实现基本功能（正常情况），还要尽可能的考虑各种情况（特殊情况--鲁棒性），要将问题考虑全面。
 * 
 * @author luzhongguo
 * @version 1.0, 2016年8月1日
 */
public class SingleList extends List {
    private Node listHeader;// 如果用反序链表构造栈，该引用相当于是栈顶指针
    private Node currentNode;// 增加一个当前节点，产生正序链表；否则只有一个listHeader只能产生反序链表

    // 实例化单向链表，创建一个头结点即可
    public SingleList() {
	listHeader = new Node();
	currentNode = listHeader;// 产生正序单向链表。如果产生反序链表，该语句不需要
    }

    // 产生反序链表（可用来构造栈）
    // @Override
    // public void add(int data) {
    // Node item = new Node(data, null);
    // item.nextNode = listHeader.nextNode;
    // listHeader.nextNode = item;
    // }

    // 产生正序链表
    public void add(int data) {
	Node item = new Node(data, null);
	currentNode.nextNode = item;
	currentNode = item;
	listSize++;
    }

    // 单向链表通过修改头结点，建立链表，所以输出是逆序
    public void traverse() {
	Node item = listHeader.nextNode;
	while (item != null) {
	    System.out.print(item.data + "\t");
	    item = item.nextNode;
	}
    }

    public boolean isEmpty() {
	if (listHeader.nextNode == null) {
	    return true;
	}
	return false;
    }

    public boolean contain(int data) {
	Node item = listHeader.nextNode;
	while (item != null) {
	    if (item.data == data)
		return true;
	    item = item.nextNode;
	}
	return false;
    }

    public Node headNoe() {
	return listHeader;
    }

    public Node subNode() {
	Random rand = new Random();
	ArrayList<Node> tmp = new ArrayList<Node>();
	Node node = listHeader;

	while (node.nextNode != null) {
	    tmp.add(node.nextNode);
	    node = node.nextNode;
	}
	node = tmp.get(rand.nextInt(listSize));
	return node;
    }

    // 给定头结点，从尾到头打印链表。
    // （即反序输出，所以用栈，其实也可以使用递归，递归的本质是栈，但是递归深度太深容易栈溢出，所以还是使用循环和栈）
    public ArrayList<Integer> inverseOuput(Node node) {
	if (node == null) {
	    return null;
	}

	Stack<Node> nodeSet = new Stack<SingleList.Node>();
	ArrayList<Integer> tmp = new ArrayList<Integer>();

	while (node.nextNode != null) {
	    nodeSet.push(node.nextNode);
	    node = node.nextNode;
	}

	while (!nodeSet.isEmpty()) {
	    tmp.add(nodeSet.pop().data);
	}
	return tmp;
    }

    // 给定头结点，指定一个节点，以O(1)时间删除该节点。这里假定待删除的节点一定在链表中。
    // 删除节点方法有两种：
    // 1、从头节点遍历到该节点，然后删除。
    // 2、直接取该节点的下一个节点，将其复制到该节点即可删除（覆盖了，也就不存在了）
    public boolean delete(Node head, Node subNode) {
	if (head == null || subNode == null) {
	    System.out.println("链表为空");
	    return false;
	}

	if (subNode == head) {// subNode是头节点
	    head = null;
	}

	if (subNode.nextNode != null) {// subNode是中间节点
	    subNode.data = subNode.nextNode.data;
	    subNode.nextNode = subNode.nextNode.nextNode;
	} else {// subNode是尾节点。这种情况只能使用遍历的方式，整个操作的平均时间是O(1)
	    Node tmpNode = head;
	    while (tmpNode.nextNode != subNode) {
		tmpNode = tmpNode.nextNode;
	    }
	    tmpNode.nextNode = null;
	}
	return true;
    }

    // 求链表中倒数第K个节点。比较笨的方法是遍历两次,第一次获得节点总述n,第二次获得第k个节点。利用栈也需要遍历两次
    /*
     * 只需要遍历一次即可完成。 利用两个指针（引用）进行操作，只需遍历一次即可。
     * 这种解法具有很好的应用性，可以利用这种思路判断链表是否成环（走的快的追上走的慢的）、取链表的中间节点等。
     * 一般是一个指针一次走一步，另一个一次走两步
     */
    // Java中没有无符号整数类型。链表中头结点一般不作为有效节点，链表的总节点个数一般不包括它。
    public Node findKthToTail(Node head, int k) {
	Node aheadNode = head;
	Node behindNode = head;

	if (head == null || k <= 0) {
	    return null;
	}

	for (int i = 0; i < k - 1; i++) {// 这里可能出现总节点数小于K（总节点数不包括头节点）
	    if (aheadNode.nextNode != null) {
		aheadNode = aheadNode.nextNode;
	    } else {
		System.out.println("链表总节点个数小于K");
		return null;
	    }
	}

	while (aheadNode.nextNode != null) {
	    aheadNode = aheadNode.nextNode;
	    behindNode = behindNode.nextNode;
	}
	return behindNode;
    }

    // 给定头节点，反转链表。扩展，如何利用递归实现链表反转？这里的链表反转改变了链表结构
    public Node inverse(Node head) {
	if (head == null) {
	    return null;// 输入空，输出也空
	}
	if (head.nextNode == null || head.nextNode.nextNode == null) {
	    return head;// 不需要反转
	}

	Node node1 = head.nextNode;
	Node node2 = node1.nextNode;
	Node tmpNode = null;

	while (node2.nextNode != null) {
	    tmpNode = node2.nextNode;
	    node2.nextNode = node1;
	    node1 = node2;
	    node2 = tmpNode;
	}
	node2.nextNode = node1;

	head.nextNode.nextNode = null;
	head.nextNode = node2;
	return head;
    }

    // 给定头节点，合并两个已排序的链表。其实这里的思路和归并排序中归并一样
    public Node merge(Node head1, Node head2) {
	if (head1 == null || head2 == null) {
	    return head1 == null ? head2 : head1;
	}

	Node tmpHeadNode = new Node();// 如果不开辟一个新空间的话，返回的链表是无头节点的链表
	Node currNode = tmpHeadNode;// 这和构造链表一样，只是这里的节点不是新建，而是从已有链表中取
	while (head1.nextNode != null && head2.nextNode != null) {
	    if (head1.nextNode.data < head2.nextNode.data) {
		currNode.nextNode = head1.nextNode;
		currNode = head1.nextNode;
		head1.nextNode = head1.nextNode.nextNode;
	    } else {
		currNode.nextNode = head2.nextNode;
		currNode = head2.nextNode;
		head2.nextNode = head2.nextNode.nextNode;
	    }
	}

	if (head1.nextNode != null) {// 链表2先到达尾节点
	    currNode.nextNode = head1.nextNode;
	}

	if (head2.nextNode != null) {// 链表1先到达尾节点
	    currNode.nextNode = head2.nextNode;
	}

	return tmpHeadNode;
    }

    // 两个链表的第一个公共节点
    /*
     * 此题有个隐含意思，从第一个公共节点开始，后面两个链表的节点都相同。 此题也可以用栈求解，将两个链表分别入栈， 然后从栈顶开始逐个进行比较，
     * 第一个不相等的情况的上一个节点即第一个公共节点
     * 。但是这种方法需要额外的空间。下面的解法不需要额外的空间：将长度较长的链表先走二者长度的差值（多余的部分一定不可能出现公共节点
     * ），然后才开始比较，这样剩下的长度相等。如果到链表的结尾否没有找到，则二者没有公共节点。
     */
    // 该题可以扩展为求解二叉树中两个叶节点的最低公共祖先。因为将该题的拓扑结构旋转一下就类似二叉树
    private int count(Node head) {
	int count = 0;
	Node tmpNode = head;
	while (tmpNode.nextNode != null) {// 获取两个链表的出度
	    count++;
	    tmpNode = tmpNode.nextNode;
	}
	return count;
    }

    public Node firstComNode(Node head1, Node head2) {
	if (head1 == null || head2 == null) {
	    return null;
	}
	// 两个链表的长度
	int count1 = count(head1);
	int count2 = count(head2);

	int diff = count1 - count2;
	Node tmpNode1 = head1, tmpNode2 = head2;
	if (diff > 0) {// 使较长的链表先走diff,以便于他们剩余的长度相等
	    for (int i = 0; i < diff; i++) {
		tmpNode1 = tmpNode1.nextNode;
	    }
	} else if (diff < 0) {
	    for (int i = 0; i < -diff; i++) {
		tmpNode2 = tmpNode2.nextNode;
	    }
	}

	while (tmpNode1.nextNode != null) {
	    if (tmpNode1.data == tmpNode2.data
		    && tmpNode1.nextNode == tmpNode2.nextNode) {
		return tmpNode1;
	    }
	    tmpNode1 = tmpNode1.nextNode;
	    tmpNode2 = tmpNode2.nextNode;
	}
	if (tmpNode1.data == tmpNode2.data) {// 比较最后一个节点是否相等
	    return tmpNode1;
	}

	return null;
    }

    static class Node {
	public int data;
	public Node nextNode;

	public Node() {
	}

	public Node(int data, Node node) {
	    this.data = data;
	    this.nextNode = node;
	}
    }

}
