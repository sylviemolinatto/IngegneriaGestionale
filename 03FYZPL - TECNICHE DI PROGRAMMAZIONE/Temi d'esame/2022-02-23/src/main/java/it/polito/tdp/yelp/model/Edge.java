package it.polito.tdp.yelp.model;

public class Edge {

	private Review r1;
	private Review r2;
	private long weight;
	
	public Edge(Review r1, Review r2, long weight) {
		super();
		this.r1 = r1;
		this.r2 = r2;
		this.weight = weight;
	}
	public Review getR1() {
		return r1;
	}
	public void setR1(Review r1) {
		this.r1 = r1;
	}
	public Review getR2() {
		return r2;
	}
	public void setR2(Review r2) {
		this.r2 = r2;
	}
	public long getWeight() {
		return weight;
	}
	public void setWeight(long weight) {
		this.weight = weight;
	}
	
	
}
