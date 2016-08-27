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

//A Java program for Dijkstra's single source shortest path algorithm.
//The program is for adjacency matrix representation of the graph

public class Dijkstra_src_target {
	// A utility function to find the vertex with minimum distance value,
	// from the set of vertices not yet included in shortest path tree
	static final int V = 9;
	int minDistance(int dist[], Boolean sptSet[]) {
		// Initialize min value
		int min = Integer.MAX_VALUE, min_index = -1;

		for (int v = 0; v < V; v++)
			if (sptSet[v] == false && dist[v] <= min) {
				min = dist[v];
				min_index = v;
			}
		return min_index;
	}

	// utility Function to print shortest path from source to j
	// using parent array
	// Value of parent[v] for a vertex v stores parent vertex of v in shortest
	// path tree.
	// Parent of root (or source vertex) is -1.
	// Whenever we find shorter path through a vertex u, we make u as parent of
	// current vertex.
	void printPath(int parent[], int j) {
		// Base Case : If j is source
		if (parent[j] == -1)
			return;

		printPath(parent, parent[j]);
		System.out.print("->" + j);
	}

	// A utility function to print the constructed distance
	// array
	void printSolution(int dist[], int src, int target, int parent[]) {

		System.out.println("Vertex\t Distance\t Path");
		System.out.print(src + "->" + target + " \t\t " + dist[target] + " \t "
				+ src);
		printPath(parent, target);
		System.out.println();
	}

	// finds shortest distances from source to target
	void dijkstra(int graph[][], int src, int target) {
		int dist[] = new int[V]; // The output array. dist[i] will hold
									// the shortest distance from src to i
		int parent[] = new int[V];

		// sptSet[i] will true if vertex i is included in shortest
		// path tree or shortest distance from src to i is finalized
		Boolean sptSet[] = new Boolean[V];

		// Initialize all distances as INFINITE and stpSet[] as false
		for (int i = 0; i < V; i++) {
			dist[i] = Integer.MAX_VALUE;
			sptSet[i] = false;
		}

		// Distance of source vertex from itself is always 0
		dist[src] = 0;
		parent[src] = -1;

		// Find shortest path for all vertices
		for (int count = 0; count < V - 1; count++) {
			// Pick the minimum distance vertex from the set of vertices
			// not yet processed. u is always equal to src in first
			// iteration.
			int u = minDistance(dist, sptSet);

			// Mark the picked vertex as processed
			sptSet[u] = true;

			// break the for loop when the picked minimum distance vertex is
			// equal to target
			if (u == target) {
				break;
			}

			// Update dist value of the adjacent vertices of the
			// picked vertex.
			for (int v = 0; v < V; v++)

				// Update dist[v] only if is not in sptSet, there is an
				// edge from u to v, and total weight of path from src to
				// v through u is smaller than current value of dist[v]
				if (!sptSet[v] && graph[u][v] != 0
						&& dist[u] != Integer.MAX_VALUE
						&& dist[u] + graph[u][v] < dist[v]) {
					parent[v] = u;
					dist[v] = dist[u] + graph[u][v];
				}
		}

		// print the constructed distance array
		printSolution(dist, src, target, parent);
	}

	// Driver method
	public static void main(String[] args) {
		/* Let us create the example graph discussed above */
		int graph[][] = new int[][]{{0, 4, 0, 0, 0, 0, 0, 8, 0},
				{4, 0, 8, 0, 0, 0, 0, 11, 0}, {0, 8, 0, 7, 0, 4, 0, 0, 2},
				{0, 0, 7, 0, 9, 14, 0, 0, 0}, {0, 0, 0, 9, 0, 10, 0, 0, 0},
				{0, 0, 4, 0, 10, 0, 2, 0, 0}, {0, 0, 0, 14, 0, 2, 0, 1, 6},
				{8, 11, 0, 0, 0, 0, 1, 0, 7}, {0, 0, 2, 0, 0, 0, 6, 7, 0}};

		LogUtil.printLog("Begin");
		Dijkstra_src_target t = new Dijkstra_src_target();
		t.dijkstra(graph, 0, 8);
		LogUtil.printLog("End");
	}
}
