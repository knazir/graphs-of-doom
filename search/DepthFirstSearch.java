package search;

import java.util.Stack;

import graph.Vertex;

public class DepthFirstSearch<T> {
	
	/** Iterative version of DFS algorithm 
	 * 
	 * @param vertex The starting vertex to search from
	 */
	public void dfsIt(Vertex<T> vertex) {
		Stack<Vertex<T>> stack = new Stack<Vertex<T>>();
		stack.push(vertex);
		vertex.setVisited(true);
		
		while (!stack.isEmpty()) {
			Vertex<T> current = stack.pop();
			System.out.print(current + " ");
			
			for (Vertex<T> v : current.getNeighbors()) {
				if (!v.isVisited()) {
					v.setVisited(true);
					v.setPrevious(current);
					stack.push(v);
				}
			}
		}
	}
	
	
	/** Recursive version of DFS algorithm 
	 * 
	 * @param vertex The starting vertex to search from
	 */
	public void dfsRecur(Vertex<T> vertex) {
		System.out.print(vertex + " ");
		
		for (Vertex<T> v : vertex.getNeighbors()) {
			if (!v.isVisited()) {
				v.setVisited(true);
				v.setPrevious(vertex);
				dfsRecur(v);
			}
		}
	}
}
