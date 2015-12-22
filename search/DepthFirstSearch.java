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
	
	/** Uses the recursive DFS algorithm to find a path 
	 *  between any two nodes in a graph. If no path 
	 *  exists, an empty string is printed.
	 *  
	 * @param current The current vertex being examined
	 * @param end The vertex to end the search at
	 */
	public boolean dfsPath(Vertex<T> current, Vertex<T> end) {
		if (current.equals(end)) {
			System.out.println(end.path());
			return true;
		}
		for (Vertex<T> v : current.getNeighbors()) {
			if (!v.isVisited()) {
				v.setVisited(true);
				v.setPrevious(current);
				if (dfsPath(v, end)) return true;
			}
		}
		
		return false;
	}
	
}
