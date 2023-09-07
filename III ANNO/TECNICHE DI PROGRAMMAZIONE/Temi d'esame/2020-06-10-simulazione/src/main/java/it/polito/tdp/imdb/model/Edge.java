package it.polito.tdp.imdb.model;

public class Edge {
	
	private Actor a1;
	private Actor a2;
	private int weight;
	
	public Edge(Actor a1, Actor a2, int weight) {
		super();
		this.a1 = a1;
		this.a2 = a2;
		this.weight = weight;
	}

	public Actor getA1() {
		return a1;
	}

	public void setA1(Actor a1) {
		this.a1 = a1;
	}

	public Actor getA2() {
		return a2;
	}

	public void setA2(Actor a2) {
		this.a2 = a2;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}
	
	

}
