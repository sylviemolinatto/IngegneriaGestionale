package it.polito.tdp.PremierLeague.model;

public class Edge implements Comparable<Edge> {
	
	private int p1;
	private int p2;
	private int weight;
	
	public Edge(int p1, int p2, int weight) {
		this.p1 = p1;
		this.p2 = p2;
		this.weight = weight;
	}

	public int getP1() {
		return p1;
	}

	public void setP1(int p1) {
		this.p1 = p1;
	}

	public int getP2() {
		return p2;
	}

	public void setP2(int p2) {
		this.p2 = p2;
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
		return o.weight-this.weight;
	}

	@Override
	public String toString() {
		return "[p1=" + p1 + ", p2=" + p2 + ", weight=" + weight + "]";
	}
	
	
	
	
	
	

}
