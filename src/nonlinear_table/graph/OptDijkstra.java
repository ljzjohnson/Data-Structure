/*   
 * Copyright (c) 2010-2020 Founder LZG. All Rights Reserved.   
 *   
 * This software is the confidential and proprietary information of   
 * Founder. You shall not disclose such Confidential Information   
 * and shall use it only in accordance with the terms of the agreements   
 * you entered into with Founder.   
 *   
 */
package com.routesearch.route;

import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;

class Node implements Comparable<Node> {
    public int nodeNum;
    public int gVaule = 0;
    public Node parentNode = null;

    public Node() {

    }

    public Node(int nodeNum) {
	this.nodeNum = nodeNum;
    }

    // 计算起始节点到当前节点的代价
    public int calcG(int[][] costmatrix) {
	return this.parentNode.gVaule
		+ costmatrix[this.parentNode.nodeNum][this.nodeNum];
    }

    @Override
    public int compareTo(Node o) {
	return this.gVaule - o.gVaule;
    }
}

public class OptDijkstra {
    private static PriorityQueue<Node> openPQueue;// 存放已探测，但未访问的节点
    // private static LinkedList<Node> closeList;// 存放已访问的节点
    public static ArrayList<Integer> path;
    public static int minCost;

    // 唯一缺点就是优先队列删除非对头元素比较慢
    private static Node dijkstraRun(int[][] costmatrix, int source, int target) {
	openPQueue = new PriorityQueue<Node>();// 唯一缺点就是更新队列中的节点值比较麻烦，其删除，插入都很快
	// closeList = new LinkedList<Node>();
	Node startNode = new Node(source);
	startNode.parentNode = null;
	Node compNode;
	ArrayList<Node> replaceQueue = new ArrayList<Node>(Parms.vertices_num);
	for (int i = 0; i < Parms.vertices_num; i++) {
	    replaceQueue.add(new Node(i));
	}

	openPQueue.offer(startNode);
	while (openPQueue.size() != 0) {// openPQueue列表非空
	    Node currentNode = openPQueue.poll();
	    // closeList.add(currentNode);
	    if (currentNode.nodeNum == target) {
		return currentNode;
	    }

	    ArrayList<Integer> neighborList = Parms.SPMatrix
		    .get(currentNode.nodeNum);
	    if (neighborList.get(0) != Integer.MIN_VALUE) {
		for (Integer neighbPoint : neighborList) {
		    Node neighNode = new Node(neighbPoint);
		    neighNode.parentNode = currentNode;

		    neighNode.gVaule = neighNode.calcG(costmatrix);// 计算节点的估计函数值
		    // 用数组直接随机访问，判断neighbPoint是否在openPQueue比较高效
		    compNode = replaceQueue.get(neighbPoint);
		    if (compNode.gVaule != 0) {
			if (neighNode.gVaule < compNode.gVaule) {// neighNode的估价值小于openPQueue表的估价值
			    openPQueue.remove(compNode);// 效率较低
			    openPQueue.offer(neighNode);// 更新openPQueue表中的估价值;
			    replaceQueue.set(neighbPoint, neighNode);
			}// 松弛操作,只有openPQueue中有，才会执行松弛，否则就是直接加入
		    } else {
			openPQueue.offer(neighNode);// 将neighbPoint插入openPQueue中;
			replaceQueue.set(neighbPoint, neighNode);
		    }
		}// end for
	    }// end if list size大小判断
	}// end while
	return null;
    }

    // 返回最终路径
    private static ArrayList<Integer> makePath(Node node) {
	ArrayList<Integer> finalPath = new ArrayList<Integer>();
	if (node == null) {// 未求得结果
	    return null;
	}
	while (node.parentNode != null) {
	    finalPath.add(node.nodeNum);// 在第一个元素处添加
	    node = node.parentNode;// 将node赋值为parent node
	}
	// finalPath.add(node.nodeNum);
	Collections.reverse(finalPath);
	return finalPath;
    }

    public static int dijkstra(int[][] costmatrix, int source, int target) {
	Node resultNode = dijkstraRun(costmatrix, source, target);
	path = makePath(resultNode);
	minCost = resultNode.gVaule;
	// System.out.println("代价:" + minCost);
	// System.out.println("路径编号：" + resultList);
	return minCost;
    }

}
