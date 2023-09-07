package it.polito.tdp.yelp.model;

public class Edge {
	
	private Business business_1;
	private Business business_2;
	private double weight;
	
	public Edge(Business business_1, Business business_2, double weight) {
		this.business_1 = business_1;
		this.business_2 = business_2;
		this.weight = weight;
	}

	public Business getBusiness_1() {
		return business_1;
	}

	public Business getBusiness_2() {
		return business_2;
	}

	public double getWeight() {
		return weight;
	}
	
	
	
	

}
