package nonlinear_table.graph;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.Set;

/**
 * PriorityQueue + Dijkstra算法求单源最短路径 首推此方法 虽然优先级队列优化比堆优化性能差一点，差距很小。
 * 但是优先级队列可以直接使用adt中的PriorityQueue来实现， 而堆优化实现非常复杂。
 * 
 * @author DuXiangYu
 * 
 */
public class DijKstra_link_Queue {

    static int nodeCount;
    static int edgeCount;
    // 邻接表表头数组
    static Node[] firstArray;
    // 最短路径数组
    // static int[] dist;
    // S集合,代表着已经找到最短路径的结点
    static HashSet<Integer> s;
    // 映射集合
    static dist[] distArray;
    // 优先级队列
    static PriorityQueue<dist> pq;
    static int max = 1000000;

    /**
     * 结点类
     * 
     * @author DuXiangYu
     */
    static class Node {
	// 邻接顶点map
	private HashMap<Integer, Integer> map = null;

	public void addEdge(int end, int edge) {
	    if (this.map == null) {
		this.map = new HashMap<Integer, Integer>();
	    }
	    this.map.put(end, edge);
	}
    }

    /**
     * dist: 保存源结点至每个结点的最短路径
     * 
     * @author DuXiangYu
     *
     */
    static class dist implements Comparable<dist> {

	int value;
	int index;

	public dist(int value, int index) {
	    this.value = value;
	    this.index = index;
	}

	@Override
	public int compareTo(dist o) {
	    if (o.value < this.value) {
		return 1;
	    } else if (o.value > this.value) {
		return -1;
	    } else {
		return 0;
	    }
	}

    }

    /**
     * PriorityQueue + Dijkstra算法实现
     */
    private static void djst() {
	s = new HashSet<Integer>();
	pq = new PriorityQueue<dist>(nodeCount);
	distArray = new dist[nodeCount + 1];
	Node tempNode = firstArray[1];
	for (int i = 2; i < nodeCount + 1; i++) {
	    HashMap<Integer, Integer> tempMap = tempNode.map;
	    if (tempMap.containsKey(i)) {
		dist d = new dist(tempMap.get(i), i);
		pq.offer(d);
		distArray[i] = d;
	    } else {
		dist d = new dist(max, i);
		pq.offer(d);
		distArray[i] = d;
	    }
	}
	s.add(1);

	while (s.size() < nodeCount) {

	    dist d = pq.poll();
	    int index = d.index;
	    int value = d.value;

	    s.add(index);

	    // 用indx这个点去更新它的邻接点到开始点的距离
	    HashMap<Integer, Integer> m = firstArray[index].map;
	    if (m == null) {
		continue;
	    }
	    Set<Integer> set = m.keySet();
	    Iterator<Integer> it = set.iterator();
	    while (it.hasNext()) {
		int num = it.next();
		if (num == 1) {
		    continue;
		}
		dist tempDist = distArray[num];
		if (m.get(num) + value < tempDist.value) {
		    pq.remove(tempDist);
		    tempDist.value = m.get(num) + value;
		    pq.offer(tempDist);
		    distArray[num] = tempDist;
		}
	    }
	}

	for (int i = 2; i < nodeCount + 1; i++) {
	    System.out.println(distArray[i].value);
	}
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