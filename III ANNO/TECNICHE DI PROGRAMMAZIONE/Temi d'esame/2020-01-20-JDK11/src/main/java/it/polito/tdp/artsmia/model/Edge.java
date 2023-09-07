package it.polito.tdp.artsmia.model;

public class Edge implements Comparable<Edge>{

	private Artist a1;
	private Artist a2;
	private int weight;
	
	public Edge(Artist a1, Artist a2, int weight) {
		super();
		this.a1 = a1;
		this.a2 = a2;
		this.weight = weight;
	}

	public Artist getA1() {
		return a1;
	}

	public Artist getA2() {
		return a2;
	}

	public int getWeight() {
		return weight;
	}

	@Override
	public int compareTo(Edge o) {
		// TODO Auto-generated method stub
		return o.getWeight()-this.weight;
	}

	@Override
	public String toString() {
		return a1+" - "+a2+" = "+weight;
	}
	
	
	
	
	
	
	
}
