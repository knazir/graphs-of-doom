package graph;

public class Edge<T> {

	private double cost;
	private Vertex<T> target;

	public Edge(Vertex<T> target, double cost) {
		this.target = target;
		this.cost = cost;
	}

	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}

	public Vertex<T> getTarget() {
		return target;
	}

	public void setTarget(Vertex<T> target) {
		this.target = target;
	}

}
