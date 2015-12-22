/*
 * Uses the Manhattan distance as an heuristic function
 */

package search;

import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

import graph.Edge;
import graph.Vertex;

public class AStarSearch<T> {

	public void aStar(Vertex<T> start, Vertex<T> end) {
		PriorityQueue<Vertex<T>> unexplored = new PriorityQueue<Vertex<T>>();
		Set<Vertex<T>> explored = new HashSet<Vertex<T>>();

		start.setgScore(0); // set minimum distance
		unexplored.add(start);
		boolean found = false;

		while (!unexplored.isEmpty() && !found) {
			Vertex<T> current = unexplored.poll();

			if (current.getName().equals(end.getName()))
				found = true;

			for (Edge<T> e : current.getEdges()) {
				Vertex<T> child = e.getTarget();
				double cost = e.getCost();
				double tmpGScore = current.getfScore() + cost;
				double tmpFScore = tmpGScore + heuristic(child, end);

				if (explored.contains(child) && tmpFScore >= child.getfScore()) {
					continue;
				} else if (!unexplored.contains(child) || tmpFScore < child.getfScore()) {
					child.setPrevious(current);
					child.setgScore(tmpGScore);
					child.setfScore(tmpFScore);

					if (unexplored.contains(child)) {
						unexplored.remove(child);
					}
					unexplored.add(child);
				}
			}
		}
	}

	/** Manhattan heuristic (sum of legs of right triangle) */
	private double heuristic(Vertex<T> child, Vertex<T> end) {
		return Math.abs(child.getX() - end.getX()) + Math.abs(child.getY() - end.getY());
	}

}
