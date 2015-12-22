/*
 * Inherits advantages of both BFS and DFS by performing a
 * DFS search with a depth value to mimic the BFS layer-by-layer
 * search.
 */

package search;

import java.util.Stack;

import graph.Vertex;

public class IterativeDeepeningDFS<T> {

	/**
	 * Very hacky way to prevent infinite looping if no path exists. This only
	 * works for graphs with max depth < 1000
	 */
	private static final int SAFETY_NET_DEPTH = 1000;

	public boolean iddfs(Vertex<T> start, Vertex<T> end) {
		int depth = 0;
		boolean found = false;

		while (!found && depth < SAFETY_NET_DEPTH) {
			found = dfs(start, end, depth);
			depth++;
		}
		return false;
	}

	private boolean dfs(Vertex<T> start, Vertex<T> end, int depth) {
		Stack<Vertex<T>> stack = new Stack<Vertex<T>>();
		start.setDepthLevel(0);
		stack.push(start);

		while (!stack.isEmpty()) {
			Vertex<T> current = stack.pop();

			if (current.getName().equals(end.getName())) {
				System.out.println(current.path());
				return true;
			}

			if (current.getDepthLevel() >= depth)
				continue;

			for (Vertex<T> v : current.getNeighbors()) {
				v.setDepthLevel(current.getDepthLevel() + 1);
				v.setPrevious(current);
				stack.push(v);
			}
		}
		return false;
	}

}
