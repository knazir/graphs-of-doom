package graph;

import java.util.*;

public class Vertex<T> {
	
	private String name;
	private T data;
	private boolean visited;
	private List<Vertex<T>> neighbors;
	private Vertex<T> previous;
	
	public Vertex(String name, T data) {
		this.name = name;
		this.data = data;
		this.neighbors = new ArrayList<Vertex<T>>();
	}
	
	public Vertex(T data) {
		this.data = data;
		this.neighbors = new ArrayList<Vertex<T>>();
	}
	
	public void addNeighbor(Vertex<T> vertex) {
		this.neighbors.add(vertex);
	}
	
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
	
	@Override
	public String toString() {
		if (name != null) return name;
		else return "" + this.data;
	}
}
