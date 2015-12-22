/*
 * The current version of this app is just functioning on tree-style graphs.
 * I'll add more variety later, it's actually not much more work. For now I'm
 * working on a way to streamline graph creation so that I can make complex
 * graphs automatically.
 * 
 * TODO: Create Graph class and move resetGraph method there
 */

package client;

import java.util.ArrayList;

import graph.Edge;
import graph.Vertex;
import search.AStarSearch;
import search.BreadthFirstSearch;
import search.DepthFirstSearch;
import search.IterativeDeepeningDFS;

public class GraphApp {

	/**
	 * Vertex set lists to allow pathfinding between any two vertices where the
	 * indexes are (currently) the vertex's stored data/name
	 */
	private static ArrayList<Vertex<Integer>> vertexSet;
	private static ArrayList<Vertex<Integer>> aStarVertices;

	public static void main(String[] args) {
		Vertex<Integer> root = createSampleTreeGraph();
		createAStarGraph();
		runSearchAlgorithms(root);
		runPathAlgorithms(root);
	}

	/**
	 * Creates the following graph: 
	 * 		    1 
	 * 		   / \ 
	 * 		  2   4 
	 * 		 / \   \ 
	 * 		5   6   3
	 * 
	 * and returns the root node (1)
	 */
	private static Vertex<Integer> createSampleTreeGraph() {
		// create nodes
		Vertex<Integer> vertex1 = new Vertex<Integer>("1");
		Vertex<Integer> vertex2 = new Vertex<Integer>("2");
		Vertex<Integer> vertex3 = new Vertex<Integer>("3");
		Vertex<Integer> vertex4 = new Vertex<Integer>("4");
		Vertex<Integer> vertex5 = new Vertex<Integer>("5");
		Vertex<Integer> vertex6 = new Vertex<Integer>("6");

		// add neighbors
		vertex1.addNeighbor(vertex2);
		vertex1.addNeighbor(vertex4);
		vertex4.addNeighbor(vertex3);
		vertex2.addNeighbor(vertex5);
		vertex2.addNeighbor(vertex6);

		// initialize vertex set list
		vertexSet = new ArrayList<Vertex<Integer>>();
		vertexSet.add(null); // dummy vertex to fill index 0
		vertexSet.add(vertex1);
		vertexSet.add(vertex2);
		vertexSet.add(vertex3);
		vertexSet.add(vertex4);
		vertexSet.add(vertex5);
		vertexSet.add(vertex6);

		// return "root" of graph
		return vertex1;
	}
	
	/** Creates the following graph:
	 * 		
	 * 				10
	 *  	A --------------- B
	 *  	| \_______		  |
	 * 	100 |	   15 \____	  | 10
	 *  	|				\ |
	 *  	D --------------- C
	 * 				10
	 * 
	 * With vertices stored in the aStarVertices ArrayList
	 */
	private static void createAStarGraph() {
		// initialize vertex list
		aStarVertices = new ArrayList<Vertex<Integer>>();
		
		// create vertices
		Vertex<Integer> vertex1 = new Vertex<Integer>("A");
		Vertex<Integer> vertex2 = new Vertex<Integer>("B");
		Vertex<Integer> vertex3 = new Vertex<Integer>("C");
		Vertex<Integer> vertex4 = new Vertex<Integer>("D");
		
		// create edges
		vertex1.addNeighbor(new Edge<Integer>(vertex2, 10));
		vertex1.addNeighbor(new Edge<Integer>(vertex4, 100));
		vertex1.addNeighbor(new Edge<Integer>(vertex3, 15));
		vertex2.addNeighbor(new Edge<Integer>(vertex3, 10));
		vertex3.addNeighbor(new Edge<Integer>(vertex4, 10));
		
		// add to list
		aStarVertices.add(vertex1);
		aStarVertices.add(vertex2);
		aStarVertices.add(vertex3);
		aStarVertices.add(vertex4);
	}

	/**
	 * Runs various search algorithms. Although these algorithms (BFS and DFS)
	 * are usually used to find a path, this method shows the basic idea of how
	 * each algorithm traverses an entire graph
	 */
	private static void runSearchAlgorithms(Vertex<Integer> root) {
		BreadthFirstSearch<Integer> bfs = new BreadthFirstSearch<Integer>();
		printHeader("Running Breadth First Search");
		bfs.bfs(root);
		resetGraph(root);

		DepthFirstSearch<Integer> dfs = new DepthFirstSearch<Integer>();
		System.out.println();
		printHeader("Running Iterative Depth First Search");
		dfs.dfsIt(root);
		resetGraph(root);
		System.out.println();

		printHeader("Running Recursive Depth First Search");
		dfs.dfsRecur(root);
		resetGraph(root);
		System.out.println();
	}

	/** Runs various pathfinding algorithms */
	private static void runPathAlgorithms(Vertex<Integer> root) {
		BreadthFirstSearch<Integer> bfs = new BreadthFirstSearch<Integer>();
		printHeader("Finding BFS Path from 1 to 6");
		bfs.bfsPath(vertexSet.get(1), vertexSet.get(6));
		resetGraph(root);

		DepthFirstSearch<Integer> dfs = new DepthFirstSearch<Integer>();
		printHeader("Finding DFS Path from 1 to 6");
		dfs.dfsPath(vertexSet.get(1), vertexSet.get(6));
		resetGraph(root);

		IterativeDeepeningDFS<Integer> iddfs = new IterativeDeepeningDFS<Integer>();
		printHeader("Finding IDDFS Path from 1 to 6");
		iddfs.iddfs(vertexSet.get(1), vertexSet.get(6));
		resetGraph(root);
		
		AStarSearch<Integer> aStar = new AStarSearch<Integer>();
		printHeader("Finding A* Path from A to D");
		aStar.aStar(aStarVertices.get(0), aStarVertices.get(3));
		System.out.println(aStarVertices.get(3).path());
	}
	


	/** Prints header text for each algorithm run */
	private static void printHeader(String headerText) {
		String border = "";
		for (int i = 0; i < headerText.length(); i++)
			border += "=";

		System.out.println();
		System.out.println(border);
		System.out.println(headerText);
		System.out.println(border);
	}

	/** Recursively resets graph vertices from starting vertex of last search */
	private static void resetGraph(Vertex<Integer> vertex) {
		vertex.setVisited(false);
		vertex.setPrevious(null);

		if (vertex.getNeighbors().isEmpty())
			return;

		for (Vertex<Integer> v : vertex.getNeighbors()) {
			resetGraph(v);
		}
	}
}
