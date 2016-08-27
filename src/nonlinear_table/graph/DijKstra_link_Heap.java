/*   
 * Copyright (c) 2010-2020 Founder LZG. All Rights Reserved.   
 *   
 * This software is the confidential and proprietary information of   
 * Founder. You shall not disclose such Confidential Information   
 * and shall use it only in accordance with the terms of the agreements   
 * you entered into with Founder.   
 *   
 */
package nonlinear_table.graph;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

/**
 * Heap + Dijkstra算法求单源最短路径 采用邻接表存储图数据 邻接表结构: 头结点--Node对象数组
 * 邻接节点--Node对象中的HashMap * @author DuXiangYu
 * */
public class DijKstra_link_Heap {
    static int nodeCount;
    static int edgeCount;
    static Node[] firstArray;// 邻接表表头数组
    static int[][] dist;// 最短路径数组
    static int[] ref;
    static int max = 1000000;

    // 节点类
    static class Node {
	private HashMap<Integer, Integer> map = null;// 邻接顶点map

	public void addEdge(int end, int edge) {
	    if (this.map == null) {
		this.map = new HashMap<Integer, Integer>();
	    }
	    this.map.put(end, edge);
	}
    }

    // Heap + Dijkstra算法实现
    private static void djst() {
	dist = new int[2][nodeCount + 1];
	ref = new int[nodeCount + 1];
	Node tempNode = firstArray[1];
	for (int i = 2; i < nodeCount + 1; i++) {
	    HashMap<Integer, Integer> tempMap = tempNode.map;
	    dist[0][i] = tempMap.containsKey(i) ? tempMap.get(i) : max;
	    dist[1][i] = i;
	    ref[i] = i;
	    minUp(i);
	}
	int flag = nodeCount;
	while (flag >= 2) {
	    int index = dist[1][2];// 用index这个点去更新它的邻接点到开始点的距离
	    changeKey(2, flag);
	    maxDown(2, --flag);
	    HashMap<Integer, Integer> m = firstArray[index].map;
	    if (m == null) {
		continue;
	    }
	    Set<Integer> set = m.keySet();
	    Iterator<Integer> it = set.iterator();
	    while (it.hasNext()) {
		int num = it.next();
		if (m.get(num) + dist[0][flag + 1] < dist[0][ref[num]]) {
		    dist[0][ref[num]] = m.get(num) + dist[0][flag + 1];
		    minUp(ref[num]);
		}
	    }
	}
	for (int i = 2; i < nodeCount + 1; i++) {
	    System.out.println(dist[0][ref[i]]);
	}
    }

    // 下滤
    private static void maxDown(int index, int end) {
	int temp = dist[0][index];
	int left = index * 2 - 1;
	while (left <= end) {// 判断左右子节点大小
	    if (left + 1 <= end && dist[0][left + 1] < dist[0][left]) {
		left++;
	    }
	    if (dist[0][left] > temp) {// 如果左右子节点都比temp大的话结束下沉操作
		break;
	    }
	    changeKey(index, left); // 交换子节点和父节点
	    index = left;
	    left = index * 2 - 1;
	}
    }

    // 上滤
    private static void minUp(int n) {
	int f = (n + 1) / 2;
	while (f >= 2 && dist[0][f] > dist[0][n]) {
	    changeKey(f, n);
	    n = f;
	    f = (n + 1) / 2;
	}
    }

    // 交换两个值
    private static void changeKey(int a, int b) {
	int n = dist[1][a];
	int m = dist[1][b];
	int temp = ref[n];
	ref[n] = ref[m];
	ref[m] = temp;
	temp = dist[0][a];
	dist[0][a] = dist[0][b];
	dist[0][b] = temp;
	temp = dist[1][a];
	dist[1][a] = dist[1][b];
	dist[1][b] = temp;
    }

    public static void main(String[] args) {
	Scanner sc = new Scanner(System.in);
	nodeCount = sc.nextInt();
	edgeCount = sc.nextInt();
	firstArray = new Node[nodeCount + 1];
	for (int i = 0; i < nodeCount + 1; i++) {
	    firstArray[i] = new Node();
	}
	for (int i = 0; i < edgeCount; i++) {
	    int begin = sc.nextInt();
	    int end = sc.nextInt();
	    int edge = sc.nextInt();
	    firstArray[begin].addEdge(end, edge);
	}
	sc.close();
	long begin = System.currentTimeMillis();
	djst();
	long end = System.currentTimeMillis();
	System.out.println(end - begin + "ms");
    }
}