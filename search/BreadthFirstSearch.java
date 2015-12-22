package search;

import java.util.LinkedList;
import java.util.Queue;

import graph.Vertex;

public class BreadthFirstSearch<T> {

	/** Performs an iterative breadth first search from a given vertex
	 *  @param vertex The starting vertex to perform a serach from
	 */
	public void bfs(Vertex<T> vertex) {
		// linked list implements queue interface
		Queue<Vertex<T>> queue = new LinkedList<Vertex<T>>(); 
		vertex.setVisited(true);
		queue.add(vertex);

		while (!queue.isEmpty()) {
			Vertex<T> current = queue.remove();
			System.out.print(current + " ");
			
			// explores "layer-by-layer" (where each "layer" is the set of neighbors)
			for (Vertex<T> v : current.getNeighbors()) {
				if (!v.isVisited()) {
					v.setVisited(true);
					v.setPrevious(current);
					queue.add(v);
				}
			}
		}
	}
	
	
	/** Uses the breadth first search algorithm to find a path 
	 *  between two vertices and prints it to the console. Prints
	 *  an empty string if no path exists
	 *  
	 *  @param start The vertex to start the path from
	 *  @param end The vertex to end the path at
	 */
	public void bfsPath(Vertex<T> start, Vertex<T> end) {
		// linked list implements queue interface
		Queue<Vertex<T>> queue = new LinkedList<Vertex<T>>();
		start.setVisited(true);
		queue.add(start);
		
		while (!queue.isEmpty()) {
			Vertex<T> current = queue.remove();
			
			for (Vertex<T> v : current.getNeighbors()) {
				if (!v.isVisited()) {
					v.setVisited(true);
					v.setPrevious(current);
					queue.add(v);
				}
				
				if (v.equals(end)) {
					System.out.println(end.path());
					break;
				}
			}
		}
	}
}
