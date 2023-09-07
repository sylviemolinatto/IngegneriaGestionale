package it.polito.tdp.food.model;

public class Edge {

	private String portionName1;
	private String portionName2;
	private int weight;
	
	public Edge(String portionName1, String portionName2, int weight) {
		super();
		this.portionName1 = portionName1;
		this.portionName2 = portionName2;
		this.weight = weight;
	}

	public String getPortionName1() {
		return portionName1;
	}

	public String getPortionName2() {
		return portionName2;
	}

	public int getWeight() {
		return weight;
	}
	
}
