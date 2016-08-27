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

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

//ͼ���ڽӾ���ͼ��
class AMWGraph {

	private ArrayList<String> vertexList;// �洢�������
	private int[][] edges;// �ڽӾ��������洢��
	private int numOfEdges;// �ߵ���Ŀ

	public AMWGraph(int n) {
		// ��ʼ������һά���飬�ͱߵ���Ŀ
		edges = new int[n][n];
		vertexList = new ArrayList<String>(n);
		numOfEdges = 0;
	}
	// �õ����ĸ���
	public int getNumOfVertex() {
		return vertexList.size();
	}
	// �õ��ߵ���Ŀ
	public int getNumOfEdges() {
		return numOfEdges;
	}
	// ���ؽ��i������
	public Object getValueByIndex(int i) {
		return vertexList.get(i);
	}
	// ����v1,v2��Ȩֵ
	public int getWeight(int v1, int v2) {
		return edges[v1][v2];
	}
	// ������
	public void insertVertex(String vertex) {
		vertexList.add(vertexList.size(), vertex);
	}
	// �����
	public void insertEdge(int v1, int v2, int weight) {
		edges[v1][v2] = weight;
		numOfEdges++;
	}
	// ɾ�����
	public void deleteEdge(int v1, int v2) {
		edges[v1][v2] = 0;
		numOfEdges--;
	}
	// �õ���һ���ڽӽ����±�
	public int getFirstNeighbor(int index) {
		for (int j = 0; j < vertexList.size(); j++) {
			if (edges[index][j] > 0) {
				return j;
			}
		}
		return -1;
	}
	// ����ǰһ���ڽӽ����±���ȡ����һ���ڽӽ��
	public int getNextNeighbor(int v1, int v2) {
		for (int j = v2 + 1; j < vertexList.size(); j++) {
			if (edges[v1][j] > 0) {
				return j;
			}
		}
		return -1;
	}
	// ˽�к�����������ȱ���
	private void depthFirstSearch(boolean[] isVisited, int i) {
		// ���ȷ��ʸý�㣬�ڿ���̨��ӡ����
		System.out.print(getValueByIndex(i) + "  ");
		// �øý��Ϊ�ѷ���
		isVisited[i] = true;

		int w = getFirstNeighbor(i);//
		while (w != -1) {
			if (!isVisited[w]) {
				depthFirstSearch(isVisited, w);
			}
			w = getNextNeighbor(i, w);
		}
	}
	// ���⹫��������������ȱ���������ͬ��˽�к������ڷ�������
	public boolean[] depthFirstSearch() {
		boolean[] isVisited = new boolean[getNumOfVertex()];// ��¼����Ƿ��Ѿ������ʵ�����
		for (int i = 0; i < getNumOfVertex(); i++) {
			isVisited[i] = false;// �����нڵ�����Ϊδ����
		}
		for (int i = 0; i < getNumOfVertex(); i++) {
			// ��Ϊ���ڷ���ͨͼ��˵��������ͨ��һ������һ�����Ա������н��ġ�
			if (!isVisited[i]) {
				depthFirstSearch(isVisited, i);
			}
		}
		return isVisited;
	}
	// ˽�к�����������ȱ���
	private void broadFirstSearch(boolean[] isVisited, int i) {
		int u, w;
		Queue<Integer> queue = new LinkedList<Integer>();

		// ���ʽ��i
		System.out.print(getValueByIndex(i) + "  ");
		isVisited[i] = true;
		// ��������
		queue.add(i);
		while (!queue.isEmpty()) {
			u = ((Integer) queue.remove()).intValue();
			w = getFirstNeighbor(u);
			while (w != -1) {
				if (!isVisited[w]) {
					// ���ʸý��
					System.out.print(getValueByIndex(w) + "  ");
					// ����ѱ�����
					isVisited[w] = true;
					// �����
					queue.add(w);
				}
				// Ѱ����һ���ڽӽ��
				w = getNextNeighbor(u, w);
			}
		}
	}
	// ���⹫��������������ȱ���
	public void broadFirstSearch() {
		boolean[] isVisited = new boolean[getNumOfVertex()];
		for (int i = 0; i < getNumOfVertex(); i++) {
			isVisited[i] = false;
		}
		for (int i = 0; i < getNumOfVertex(); i++) {
			if (!isVisited[i]) {
				broadFirstSearch(isVisited, i);
			}
		}
	}
}

// �����public������depthFirstSearch()��broadFirstSearch()��������Ϊ��Ӧ�Ե���ͼ�Ƿ���ͨͼ�������
// ����Ƿ���ͨͼ����ôֻͨ��һ��������޷���ȫ�������н��ġ�

// ���������������������ͼ����������ࣺ

public class Search {

	public static void main(String args[]) {
		int n = 8, e = 9;// �ֱ����������ͱߵ���Ŀ
		String labels[] = {"1", "2", "3", "4", "5", "6", "7", "8"};// ���ı�ʶ
		AMWGraph graph = new AMWGraph(n);
		for (String label : labels) {
			graph.insertVertex(label);// ������
		}
		// ���������
		graph.insertEdge(0, 1, 1);
		graph.insertEdge(0, 2, 1);
		graph.insertEdge(1, 3, 1);
		graph.insertEdge(1, 4, 1);
		graph.insertEdge(3, 7, 1);
		graph.insertEdge(4, 7, 1);
		graph.insertEdge(2, 5, 1);
		graph.insertEdge(2, 6, 1);
		graph.insertEdge(5, 6, 1);
		graph.insertEdge(1, 0, 1);
		graph.insertEdge(2, 0, 1);
		graph.insertEdge(3, 1, 1);
		graph.insertEdge(4, 1, 1);
		graph.insertEdge(7, 3, 1);
		graph.insertEdge(7, 4, 1);
		graph.insertEdge(4, 2, 1);
		graph.insertEdge(5, 2, 1);
		graph.insertEdge(6, 5, 1);

		boolean[] isVisited;
		System.out.println("���������������Ϊ��");
		isVisited = graph.depthFirstSearch();
		System.out.println();
		for (int i = 0; i < isVisited.length; i++) {
			System.out.print(isVisited[i] + "  ");
		}
		System.out.println();
		System.out.println("���������������Ϊ��");
		graph.broadFirstSearch();
	}
}