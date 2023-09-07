package it.polito.tdp.genes.model;

public class Edge implements Comparable<Edge> {
	
	private Genes g1;
	private Genes g2;
	private double weight;
	
	public Edge(Genes g1, Genes g2, double weight) {
		this.g1 = g1;
		this.g2 = g2;
		this.weight = weight;
	}

	public Genes getG1() {
		return g1;
	}

	public void setG1(Genes g1) {
		this.g1 = g1;
	}

	public Genes getG2() {
		return g2;
	}

	public void setG2(Genes g2) {
		this.g2 = g2;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	@Override
	public int compareTo(Edge o) {
		return (int) (o.weight-this.weight);
	}
	
	
	

}
