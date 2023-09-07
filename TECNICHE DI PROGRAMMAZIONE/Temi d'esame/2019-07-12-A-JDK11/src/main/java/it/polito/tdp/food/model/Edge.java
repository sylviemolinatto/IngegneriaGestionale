package it.polito.tdp.food.model;

public class Edge implements Comparable<Edge> {

	private Food f1;
	private Food f2;
	private double weight;
	
	public Edge(Food f1, Food f2, double weight) {
		super();
		this.f1 = f1;
		this.f2 = f2;
		this.weight = weight;
	}

	public Food getF1() {
		return f1;
	}

	public void setF1(Food f1) {
		this.f1 = f1;
	}

	public Food getF2() {
		return f2;
	}

	public void setF2(Food f2) {
		this.f2 = f2;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	@Override
	public int compareTo(Edge o) {
		return Double.compare(o.weight, weight);
	}
	
	
}
