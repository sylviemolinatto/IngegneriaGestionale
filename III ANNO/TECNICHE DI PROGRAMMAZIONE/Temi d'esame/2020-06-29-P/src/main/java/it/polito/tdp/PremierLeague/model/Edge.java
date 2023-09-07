package it.polito.tdp.PremierLeague.model;

public class Edge {

	private Match m1;
	private Match m2;
	private int weight;
	
	public Edge(Match m1, Match m2, int weight) {		
		this.m1 = m1;
		this.m2 = m2;
		this.weight = weight;
	}

	public Match getM1() {
		return m1;
	}

	public Match getM2() {
		return m2;
	}

	public int getWeight() {
		return weight;
	}

	@Override
	public String toString() {
		return m1+" - "+m2;
	}
	
	
	
}
