package it.polito.tdp.genes.model;

public class Edge {
	
	String l1;
	String l2;
	int weight;
	
	public Edge(String i1, String i2, int weight) {
		this.l1 = i1;
		this.l2 = i2;
		this.weight = weight;
	}

	public String getL1() {
		return l1;
	}

	public String getL2() {
		return l2;
	}

	public int getWeight() {
		return weight;
	}

	@Override
	public String toString() {
		return l1 +" - "+l2 + " : " + weight;
	}
	
	
	
	

}
