package it.polito.tdp.PremierLeague.model;

public class Edge implements Comparable<Edge> {
	
	private Team t1;
	private Team t2;
	private int weight;
	
	public Edge(Team t1, Team t2) {
		this.t1 = t1;
		this.t2 = t2;
	}

	public Edge(Team t1, Team t2, int weight) {
		super();
		this.t1 = t1;
		this.t2 = t2;
		this.weight = weight;
	}



	public Team getT1() {
		return t1;
	}

	public void setT1(Team t1) {
		this.t1 = t1;
	}

	public Team getT2() {
		return t2;
	}

	public void setT2(Team t2) {
		this.t2 = t2;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	@Override
	public int compareTo(Edge o) {
		// TODO Auto-generated method stub
		return this.weight-o.getWeight();
	}
	
	
	
	

}
