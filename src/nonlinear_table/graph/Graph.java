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

class VertexClass {
	public int vertexNum;
	public EdgeClass firstEdge;;
}
class EdgeClass {
	public int adjvertexNum;
	public int cost;
	public EdgeClass nextEdge;
}

class LinkGraph {

	private String[] graph_split;
	private int cost, x_row, y_row;

	private int verticesNum;
	private int edgeNum;
	private VertexClass[] graph = null;
	private EdgeClass eNode = null;

	public LinkGraph(int vertices_num, int edges_num) {
		// TODO Auto-generated constructor stub
		verticesNum = vertices_num;
		edgeNum = edges_num;
		graph = new VertexClass[vertices_num];
		for (int i = 0; i < graph.length; i++) {
			graph[i].vertexNum = i;
			graph[i].firstEdge = null;
		}
	}

	// 返回顶点数目
	public int getNumOfVertex() {
		return this.verticesNum;
	}

	public int getNumOfEdge() {
		return this.edgeNum;
	}
	// 创建链表
	public void creatLinkTable() {
		for (int i = 0; i < graph_split.length; i++) {
			String[] sunStrng = graph_split[i].split(",");
			x_row = Integer.parseInt(sunStrng[1]);
			y_row = Integer.parseInt(sunStrng[2]);
			cost = Integer.parseInt(sunStrng[3]);
			eNode = new EdgeClass();
			eNode.adjvertexNum = y_row;
			eNode.cost = cost;
			eNode.nextEdge = null;
			if (graph[x_row].firstEdge == null) {
				graph[x_row].firstEdge = eNode;
			} else {
				linkLastNode(graph[x_row].firstEdge, eNode);
			}
		}
	}

	// 将节点添加到链表的最后
	private void linkLastNode(EdgeClass firstEdge, EdgeClass eNode) {
		EdgeClass pointer = firstEdge;
		while (pointer.nextEdge != null) {
			pointer = pointer.nextEdge;
		}
		pointer.nextEdge = eNode;
	}
}

class MatrixGraph {

	private String[] graph_split;
	private int edges_num, vertices_num;
	private int x_row, y_row;

	private int verticesNum;
	private int edgeNum;
	int[][] costmatrix;
	int[][] edgematrix;

	public MatrixGraph(int vertices_num, int edges_num) {
		// TODO Auto-generated constructor stub
		verticesNum = vertices_num;
		edgeNum = edges_num;
		edgematrix = new int[vertices_num][vertices_num];
		costmatrix = new int[vertices_num][vertices_num];
	}

	// 创建链接矩阵
	private void creatLinkTable() {
		for (int i = 0; i < graph_split.length; i++) {
			String[] sunStrng = graph_split[i].split(",");
			x_row = Integer.parseInt(sunStrng[1]);
			y_row = Integer.parseInt(sunStrng[2]);
			costmatrix[x_row][y_row] = Integer.parseInt(sunStrng[3]);
			edgematrix[x_row][y_row] = Integer.parseInt(sunStrng[0]);
		}
	}

}