package it.polito.tdp.crimes.model;

public class Edge {

	private String id1;
	private String id2;
	private int weight;
	
	public Edge(String id1, String id2, int weight) {
		super();
		this.id1 = id1;
		this.id2 = id2;
		this.weight = weight;
	}

	public String getId1() {
		return id1;
	}

	public void setId1(String id1) {
		this.id1 = id1;
	}

	public String getId2() {
		return id2;
	}

	public void setId2(String id2) {
		this.id2 = id2;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	@Override
	public String toString() {
		return id1+" - "+id2;
	}
	
	
}
