package it.polito.tdp.imdb.model;

public class Edge implements Comparable<Edge> {

	private Director d1;
	private Director d2;
	private int weight;
	
	public Edge(Director d1, Director d2, int weight) {
		this.d1 = d1;
		this.d2 = d2;
		this.weight = weight;
	}

	public Director getD1() {
		return d1;
	}

	public Director getD2() {
		return d2;
	}

	public int getWeight() {
		return weight;
	}

	@Override
	public int compareTo(Edge o) {
		return o.weight-this.weight;
	}
	
	
	
	
	
}
