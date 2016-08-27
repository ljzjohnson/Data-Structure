/*   
 * Copyright (c) 2010-2020 Founder LZG. All Rights Reserved.   
 *   
 * This software is the confidential and proprietary information of   
 * Founder. You shall not disclose such Confidential Information   
 * and shall use it only in accordance with the terms of the agreements   
 * you entered into with Founder.   
 *   
 */
package nonlinear_table.binary_tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * 排序二叉树更加适合数据的存储，遍历。删除，更新等操作比较耗时
 * 
 * @author luzhongguo
 * @version 1.0, 2016年8月4日
 */
public class BinaryTree {

    // 树中的节点类
    private class Node {
	private int data;
	public Node lefNode;
	public Node rigNode;

	public Node(int data) {
	    this.data = data;
	    lefNode = null;
	    rigNode = null;
	}
    }

    public Node root; // 树的根节点

    // preList和inList实现原二叉树重建
    public ArrayList<Integer> preList = new ArrayList<Integer>();
    public ArrayList<Integer> inList = new ArrayList<Integer>();

    public BinaryTree() {
	root = null;
    }

    // 递归建立排序二叉树(从根节点开始，边查找，边插入),节点的插入就是这个函数
    public void buildTreeRecursion(Node root, int data) {
	if (this.root == null) {
	    this.root = new Node(data);
	} else {
	    if (data < root.data) {// 左子树，查找
		if (root.lefNode == null) {
		    root.lefNode = new Node(data);// 插入
		} else {
		    buildTreeRecursion(root.lefNode, data);// 递归调用
		}
	    } else if (data > root.data) {
		if (root.rigNode == null) {// 右子树
		    root.rigNode = new Node(data);
		} else {
		    buildTreeRecursion(root.rigNode, data);
		}
	    }
	}
    }

    // 前序遍历,递归实现
    public void preOTraverse(Node root) {
	if (root != null) {
	    // System.out.print(root.data + "\t");
	    preList.add(root.data);
	    preOTraverse(root.lefNode);
	    preOTraverse(root.rigNode);
	}
    }

    // 中序遍历,递归实现
    public void inOTraverse(Node root) {
	if (root != null) {
	    inOTraverse(root.lefNode);
	    // System.out.print(root.data + "\t");
	    inList.add(root.data);
	    inOTraverse(root.rigNode);
	}
    }

    // 后序遍历,递归实现
    public void postOTraverse(Node root) {
	if (root != null) {
	    postOTraverse(root.lefNode);
	    postOTraverse(root.rigNode);
	    System.out.print(root.data + "\t");
	}
    }

    /*
     * 遍历所经过的节点路径一样，只是访问的时机不一样，从下面的非递归实现即可看出
     */

    // 前序遍历，非递归实现
    public void nrPreOTraverse(Node root) {
	Stack<BinaryTree.Node> stack = new Stack<BinaryTree.Node>();
	Node node = root;
	if (node == null) {
	    System.out.println("树为空");
	}
	while (node != null || !stack.isEmpty()) {
	    while (node != null) { // 将所有的左节点入栈
		System.out.print(node.data + "\t"); // 入栈前访问
		stack.push(node); // 入栈的始终是树的根节点
		node = node.lefNode;
	    }
	    if (!stack.isEmpty()) { // 依次处理右节点
		node = stack.pop();
		node = node.rigNode;// 如果右节点的子节点不为空，则将从右节点进行深入（相当于又进入了一个子树）
	    }
	}
    }

    // 中序遍历，非递归实现
    public void nrInOTraverse(Node root) {
	Stack<BinaryTree.Node> stack = new Stack<BinaryTree.Node>();
	Node node = root;
	if (node == null) {
	    System.out.println("树为空");
	}
	while (node != null || !stack.isEmpty()) {
	    while (node != null) {
		stack.push(node);
		node = node.lefNode;
	    }
	    if (!stack.isEmpty()) {
		node = stack.pop();
		System.out.print(node.data + "\t"); // 出栈后访问，出栈的始终是左节点
		node = node.rigNode;
	    }
	}
    }

    // 后序遍历，非递归实现
    public void nrPosOTraverse(Node root) {
	Stack<BinaryTree.Node> stack = new Stack<BinaryTree.Node>();
	Node node = root;
	Node prev = root;// 表示已访问过的节点
	if (node == null) {
	    System.out.println("树为空");
	}
	while (node != null || !stack.isEmpty()) {
	    while (node != null) {
		stack.push(node);
		node = node.lefNode;
	    }
	    if (!stack.isEmpty()) {
		Node temp = stack.peek().rigNode;
		if (temp == null || temp == prev) {
		    node = stack.pop();
		    System.out.print(node.data + "\t");
		    prev = node;
		    node = null;
		} else {
		    node = temp;
		}
	    }
	}
    }

    // 树的层次遍历，非递归实现。
    // 如果是图的层次遍历的话，还要添加已访问标志，因为图的连通性很大，可能已经访问过的节点会被再次访问
    public void levelTraverse(Node root) {
	Queue<BinaryTree.Node> queue = new LinkedList<BinaryTree.Node>();
	if (root == null) {
	    System.out.println("树为空！");
	    return;
	}
	queue.offer(root);
	while (!queue.isEmpty()) {
	    Node node = queue.poll();
	    System.out.print(node.data + "\t");
	    if (node.lefNode != null) {
		queue.offer(node.lefNode);
	    }
	    if (node.rigNode != null) {
		queue.offer(node.rigNode);
	    }
	}
    }

    // 查找的非递归实现
    public Node searchNode(int data) {
	Node node = root;
	while (node != null) {
	    if (data < node.data) {
		node = node.lefNode;
	    } else if (data > node.data) {
		node = node.rigNode;
	    } else {
		return node;
	    }
	}
	return null;
    }

    // 查找的递归实现
    public Node searchNodeRecursion(Node root, int data) {
	Node result = null;
	if (root != null) {
	    if (data == root.data) {// 相等递归终止，递归终止条件
		result = root;
		return result;
	    }
	    if (data < root.data) {
		result = searchNodeRecursion(root.lefNode, data);
	    } else if (data > root.data) {
		result = searchNodeRecursion(root.rigNode, data);
	    }
	}
	return result;
    }

    // 这里的更新只是针对一般二叉树的跟新方式，如果是排序二叉树，更新完，必须保持树的整体有序性。
    public Node updateNode(int data, int newData) {
	Node node = searchNode(data);
	node.data = newData;
	return node;
    }

    /*
     * 树中节点的删除较为复杂，分为三种情况 1、待删除节点是叶子节点 2、待删除节点只有一个子节点
     * 3、待删除节点有两个子节点，这种情况最为复杂，需要在其右子树中找到合适的节点替换它
     * 为了使删除后整个树的大小顺序不变，应该找出右子树中最小的节点来替换它，最小节点即是 右子树的最左节点或是右节点本身。
     * 排序二叉树的左子树所有节点都比根节点要小，反之，右子树所有节点都比根节点大
     */
    public Node deleteNode(int data) {
	Node prevNode = root; // 父节点
	Node currentNode = root; // 当前工作节点

	if (root == null) {// 树为空的情况
	    System.out.println("树为空！");
	    return null;
	}

	while (currentNode != null) { // 树非空的情况下，进行删除
	    if (data < currentNode.data) {
		prevNode = currentNode;
		currentNode = currentNode.lefNode;
	    } else if (data > currentNode.data) {
		prevNode = currentNode;
		currentNode = currentNode.rigNode;
	    } else {// 找到待删除节点currentNode
		if (currentNode.lefNode == null && currentNode.rigNode == null) {// currentNode节点为叶节点
		    if (currentNode != root) {// 叶节点为非根节点
			if (prevNode.lefNode == currentNode) {
			    prevNode.lefNode = null;
			    return prevNode;
			} else {
			    prevNode.rigNode = null;
			    return prevNode;
			}
		    } else {
			root = null;// 叶节点为根节点
			return root;
		    }
		} else if (currentNode.lefNode != null
			&& currentNode.rigNode == null) {// currentNode节点只有左节点
		    if (currentNode != root) {// currentNode节点为非根节点
			if (prevNode.lefNode == currentNode) {
			    prevNode.lefNode = currentNode.lefNode;
			    return prevNode;
			} else {
			    prevNode.rigNode = currentNode.lefNode;
			    return prevNode;
			}
		    } else {// currentNode节点为根节点
			currentNode = currentNode.lefNode;
			return currentNode;
		    }
		} else if (currentNode.lefNode == null
			&& currentNode.rigNode != null) {// currentNode节点只有右节点
		    if (currentNode != root) {// currentNode节点为非根节点
			if (prevNode.lefNode == currentNode) {
			    prevNode.lefNode = currentNode.rigNode;
			    return prevNode;
			} else {
			    prevNode.rigNode = currentNode.rigNode;
			    return prevNode;
			}
		    } else {// currentNode节点为根节点
			currentNode = currentNode.rigNode;
			return currentNode;
		    }
		} else {// 节点有两个子节点
		    Node node = currentNode.rigNode;
		    Node subPreNode = null;
		    if (currentNode != root) {
			if (node.lefNode == null) {// currentNode节点的右节点的左节点为空
			    if (prevNode.lefNode == currentNode) {
				node.lefNode = currentNode.lefNode;
				prevNode.lefNode = node;
				return prevNode;
			    } else {
				node.lefNode = currentNode.lefNode;
				prevNode.rigNode = node;
				return prevNode;
			    }
			}
		    } else {
			node.lefNode = currentNode.lefNode;
			root = node;
			return root;
		    }

		    if (currentNode != root) {
			while (node.lefNode != null) {
			    subPreNode = node;
			    node = node.lefNode;
			}
			subPreNode.lefNode = node.rigNode;
			node.lefNode = currentNode.lefNode;
			node.rigNode = currentNode.rigNode;
			if (prevNode.lefNode == currentNode) {
			    prevNode.lefNode = node;
			} else {
			    prevNode.rigNode = node;
			}
			return prevNode;
		    } else {
			while (node.lefNode != null) {
			    subPreNode = node;
			    node = node.lefNode;
			}
			subPreNode.lefNode = node.rigNode;
			node.lefNode = currentNode.lefNode;
			node.rigNode = currentNode.rigNode;
			root = node;
			return root;
		    }
		}
	    }
	}
	return null;// 找不到该节点，返回空
    }

    public static void main(String[] args) {
	BinaryTree bTree = new BinaryTree();
	int[] datas = { 3, 4, 2, 1, 5, 8, 7, 6, 9, 10 };
	for (int i : datas) {
	    bTree.buildTreeRecursion(bTree.root, i);
	}
	System.out.println("前序遍历");
	bTree.preOTraverse(bTree.root);
	for (int i : bTree.preList) {
	    System.out.print(i + "\t");
	}
	System.out.println();
	bTree.nrPreOTraverse(bTree.root);

	System.out.println();
	System.out.println("中序遍历");
	bTree.inOTraverse(bTree.root);
	for (int i : bTree.inList) {
	    System.out.print(i + "\t");
	}
	System.out.println();
	bTree.nrInOTraverse(bTree.root);

	System.out.println();
	System.out.println("后序遍历");
	bTree.postOTraverse(bTree.root);
	System.out.println();
	bTree.nrPosOTraverse(bTree.root);

	System.out.println();
	System.out.println("层次遍历");
	bTree.levelTraverse(bTree.root);

	System.out.println();
	System.out.println("查找");
	// Node node = bTree.searchNode(8);
	Node node = bTree.searchNodeRecursion(bTree.root, 8);
	System.out.println(node.lefNode.data + "\t" + node.rigNode.data);

	System.out.println();
	System.out.println("更新");
	node = bTree.updateNode(10, 10);
	System.out.println("原始数值：" + 10 + "\t" + "更新数值：" + node.data);

	System.out.println();
	System.out.println("删除");
	node = bTree.deleteNode(8);
	System.out.println("删除数值：" + node.data);

	System.out.println();
	System.out.println("插入");
	bTree.buildTreeRecursion(bTree.root, 8);
	System.out.println("查找数值：" + bTree.searchNode(8).data);

	System.out.println();
	System.out.println("层次遍历");
	bTree.levelTraverse(bTree.root);

    }
}
