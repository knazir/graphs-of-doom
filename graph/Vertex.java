package graph;

import java.util.*;

public class Vertex<T> implements Comparable<Vertex<T>> {

	/* general member fields */
	private String name; // for examples where points represent real item
	private T data;
	private boolean visited;
	private List<Vertex<T>> neighbors;
	private Vertex<T> previous; // for tracing back path

	/* for IDDFS search */
	private int depthLevel;

	/* for A* or Djikstra's search */
	private double gScore;
	private double hScore; // heuristic score
	private double fScore; // gScore + hScore
	private double x;
	private double y;
	private List<Edge<T>> edges;

	public Vertex(String name) {
		this.name = name;
		this.neighbors = new ArrayList<Vertex<T>>();
		this.depthLevel = 0;
		this.edges = new ArrayList<Edge<T>>();
	}

	public Vertex(String name, T data) {
		this.name = name;
		this.data = data;
		this.neighbors = new ArrayList<Vertex<T>>();
		this.depthLevel = 0;
		this.edges = new ArrayList<Edge<T>>();
	}

	public Vertex(T data, double x, double y) {
		this.data = data;
		this.x = x;
		this.y = y;
		this.neighbors = new ArrayList<Vertex<T>>();
		this.depthLevel = 0;
		this.edges = new ArrayList<Edge<T>>();
	}
	

	public void addNeighbor(Vertex<T> vertex) {
		this.neighbors.add(vertex);
	}

	
	public void addNeighbor(Edge<T> edge) {
		this.edges.add(edge);
	}
	
	/**
	 * Returns a string of the path from the current node back to the node from
	 * which the current search originated from
	 */
	public String path() {
		Vertex<T> iterator = this;
		String path = "";
		while (iterator != null) {
			path = iterator + " " + path;
			iterator = iterator.getPrevious();
		}
		return path;
	}

	@Override
	public String toString() {
		if (name != null && data != null)
			return name + "|" + data;
		else if (name != null)
			return name;
		else
			return "" + this.data;
	}

	@Override
	public int compareTo(Vertex<T> o) {
		return Double.compare(this.fScore, o.getfScore());
	}

	/* Start getters and setters */

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public boolean isVisited() {
		return visited;
	}

	public void setVisited(boolean visited) {
		this.visited = visited;
	}

	public List<Vertex<T>> getNeighbors() {
		return neighbors;
	}

	public int getNumNeighbors() {
		return neighbors.size();
	}

	public Vertex<T> getPrevious() {
		return previous;
	}

	public void setPrevious(Vertex<T> previous) {
		this.previous = previous;
	}

	public int getDepthLevel() {
		return this.depthLevel;
	}

	public void setDepthLevel(int depthLevel) {
		this.depthLevel = depthLevel;
	}

	public double getgScore() {
		return gScore;
	}

	public void setgScore(double gScore) {
		this.gScore = gScore;
	}

	public double gethScore() {
		return hScore;
	}

	public void sethScore(double hScore) {
		this.hScore = hScore;
	}

	public double getfScore() {
		return fScore;
	}

	public void setfScore(double fScore) {
		this.fScore = fScore;
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public List<Edge<T>> getEdges() {
		return edges;
	}

}
