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

/**
 * description
 * 
 * @author luzhongguo
 * @version 1.0, 2016年8月4日
 */
public class BalancedBinaryTree {

    private class Node {
	private int data;
	public int balane;
	public Node lefNode;
	public Node rigNode;
	public Node parent;

	public Node(int data) {
	    this.data = data;
	    balane = 0;// 新加入节点的平衡因子都为0
	    parent = null;
	    lefNode = null;
	    rigNode = null;
	}
    }

    public Node root;

    public BalancedBinaryTree() {
	root = null;
    }

    // 递归建立平衡二叉树(从根节点开始，边查找，边插入，同时进行平衡调整)，平衡因子等于左子树高度减右子树高度
    // 平衡的调整主要有四种类型:LL,RR,RL,LR
    /*
     * 这里的代码有问题，旋转之后没有进行平衡因子的更新，博客写的很详细http://blog.csdn.net/zxman660/article/details
     * / 7940190
     */
    public void buildTreeBalanced(Node root, int data) {
	if (this.root == null) {
	    this.root = new Node(data);
	} else {
	    if (data < root.data) {// 左子树，查找
		if (root.lefNode == null) {
		    root.lefNode = new Node(data);// 插入
		    root.lefNode.parent = root;
		    // 插入新节点后，需要回溯判断父节点是否出现不平衡现象，依次向上回溯判断
		    while (root != null) {// 只可能是LL，RL两种情况
			root.balane++;
			if (root.balane == 2) {
			    // 进行平衡调整
			    if (root.rigNode == null
				    || root.rigNode.rigNode == null) {// LL
				llrightRotate(root);
			    } else {// RL
				RLRotate(root);
			    }
			}
			root = root.parent;
		    }
		} else {
		    buildTreeBalanced(root.lefNode, data);// 递归调用
		}
	    } else if (data > root.data) {
		if (root.rigNode == null) {// 右子树
		    root.rigNode = new Node(data);
		    root.rigNode.parent = root;
		    // 回溯判断是否平衡
		    while (root != null) {// 只可能是RR，LR两种情况
			root.balane--;
			if (root.balane == -2) {
			    // 进行平衡调整
			    if (root.lefNode == null
				    || root.lefNode.lefNode == null) {// RR
				rrleftRotate(root);
			    } else {// LR
				LRotate(root);
			    }
			}
			root = root.parent;
		    }
		} else {
		    buildTreeBalanced(root.rigNode, data);
		}
	    }
	}
    }

    // 在进行旋转时，改变的只是各个对象的引用
    // LL型、进行右旋
    private void llrightRotate(Node node) {
	if (node != null) {
	    Node tmpLNode = node.lefNode;
	    node.lefNode = tmpLNode.rigNode;// 把节点node的leftNode的右节点赋给node的左节点
	    if (tmpLNode.rigNode != null) {// 把节点node的leftNode的右节点是否为空
		node.lefNode.parent = node;
	    }
	    tmpLNode.parent = node.parent;
	    if (node.parent == null) {// 判断node的父节点是否为空
		root = tmpLNode;
	    } else if (node.parent.lefNode == node) {// 判断node是否为父节点的左节点
		node.parent.lefNode = tmpLNode;
	    } else {// 判断node是否为父节点的右节点
		node.parent.rigNode = tmpLNode;
	    }
	    tmpLNode.rigNode = node;
	    node.parent = tmpLNode;
	}
    }

    // RR型、进行左旋
    private void rrleftRotate(Node node) {
	Node tmpRNode = node.rigNode;
	node.rigNode = tmpRNode.rigNode;
	if (tmpRNode.rigNode != null) {
	    node.rigNode.parent = node;
	}
	tmpRNode.parent = node.parent;
	if (node.parent == null) {
	    root = tmpRNode;
	} else if (node.parent.lefNode == node) {
	    node.parent.lefNode = tmpRNode;
	} else {
	    node.parent.rigNode = tmpRNode;
	}
	tmpRNode.lefNode = node;
	node.parent = tmpRNode;
    }

    // 先旋转变成RR型，然后在左旋
    private void LRotate(Node node) {
	Node tmpNode1 = node.lefNode;
	Node tmpNode2 = tmpNode1.rigNode;
	node.lefNode = tmpNode2;
	tmpNode2.parent = node;
	tmpNode2.lefNode = tmpNode1;
	tmpNode1.parent = tmpNode2;
	rrleftRotate(node);
    }

    // 先旋转变成LL型，然后在右旋
    private void RLRotate(Node node) {
	Node tmpNode1 = node.rigNode;
	Node tmpNode2 = tmpNode1.lefNode;
	node.rigNode = tmpNode2;
	tmpNode2.parent = node;
	tmpNode2.rigNode = tmpNode1;
	tmpNode1.parent = tmpNode2;
	llrightRotate(node);
    }

    public static void main(String[] args) {
	BalancedBinaryTree bTree = new BalancedBinaryTree();
	int[] datas = { 3, 4, 2, 1, 5, 8, 7, 6, 9, 10 };
	for (int i : datas) {
	    bTree.buildTreeBalanced(bTree.root, i);
	}
    }
}
