/*
 * The current version of this app is just functioning on tree-style graphs.
 * I'll add more variety later, it's actually not much more work. For now I'm
 * working on a way to streamline graph creation so that I can make complex
 * graphs automatically.
 */

package client;

import java.util.ArrayList;

import graph.Vertex;
import search.BreadthFirstSearch;
import search.DepthFirstSearch;

public class App {
	
	private static ArrayList<Vertex<Integer>> vertexSet;
	
	public static void main(String[] args) {
		Vertex<Integer> root = createSampleTreeGraph();
		runSearchAlgorithms(root);
		runPathAlgorithms(root);
	}
	
	
	/** Creates the following graph:
	 *                1
	 *              /   \
	 *             2     4
	 *            / \     \
	 *           5   6     3
	 *           
	 *  and returns the root node (1)
	 */
	private static Vertex<Integer> createSampleTreeGraph() {
		// create nodes
		Vertex<Integer> vertex1 = new Vertex<Integer>(1);
		Vertex<Integer> vertex2 = new Vertex<Integer>(2);
		Vertex<Integer> vertex3 = new Vertex<Integer>(3);
		Vertex<Integer> vertex4 = new Vertex<Integer>(4);
		Vertex<Integer> vertex5 = new Vertex<Integer>(5);
		Vertex<Integer> vertex6 = new Vertex<Integer>(6);
		
		// add neighbors
		vertex1.addNeighbor(vertex2);
		vertex1.addNeighbor(vertex4);
		vertex4.addNeighbor(vertex3);
		vertex2.addNeighbor(vertex5);
		vertex2.addNeighbor(vertex6);
		
		// initialize vertex set list
		vertexSet = new ArrayList<Vertex<Integer>>();
		vertexSet.add(null);	// dummy vertex to fill index 0
		vertexSet.add(vertex1);
		vertexSet.add(vertex2);
		vertexSet.add(vertex3);
		vertexSet.add(vertex4);
		vertexSet.add(vertex5);
		vertexSet.add(vertex6);
		
		// return "root" of graph
		return vertex1;
	}
	
	
	/** Runs various search algorithms */
	private static void runSearchAlgorithms(Vertex<Integer> root) {
		BreadthFirstSearch<Integer> bfs = new BreadthFirstSearch<Integer>();
		System.out.println();
		System.out.println("============================");
		System.out.println("Running Breadth First Search");
		System.out.println("============================");
		bfs.bfs(root);
		resetGraph(root);
		System.out.println();
		
		DepthFirstSearch<Integer> dfs = new DepthFirstSearch<Integer>();
		System.out.println();
		System.out.println("====================================");
		System.out.println("Running Iterative Depth First Search");
		System.out.println("====================================");
		dfs.dfsIt(root);
		resetGraph(root);
		System.out.println();
		
		System.out.println();
		System.out.println("====================================");
		System.out.println("Running Recursive Depth First Search");
		System.out.println("====================================");
		dfs.dfsRecur(root);
		resetGraph(root);
		System.out.println();
	}
	
	
	/** Runs various pathfinding algorithms */
	private static void runPathAlgorithms(Vertex<Integer> root) {
		BreadthFirstSearch<Integer> bfs = new BreadthFirstSearch<Integer>();
		System.out.println();
		System.out.println("============================");
		System.out.println("Finding BFS Path from 1 to 6");
		System.out.println("============================");
		bfs.bfsPath(vertexSet.get(1), vertexSet.get(6));
		resetGraph(root);
		System.out.println();
	}
	
	
	/** Recursively resets graph vertices from starting vertex of last search */
	private static void resetGraph(Vertex<Integer> vertex) {
		vertex.setVisited(false);
		vertex.setPrevious(null);
		
		if (vertex.getNeighbors().isEmpty()) return;
			
		for (Vertex<Integer> v : vertex.getNeighbors()) {
			resetGraph(v);
		}
	}
}