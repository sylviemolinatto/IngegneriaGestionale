package it.polito.tdp.genes.model;

public class Edge {
	
	private int chromosome1;
	private int chromosome2;
	private double weight;
	
	public Edge(int chromosome1, int chromosome2, double weight) {
		this.chromosome1 = chromosome1;
		this.chromosome2 = chromosome2;
		this.weight = weight;
	}

	public int getChromosome1() {
		return chromosome1;
	}

	public void setChromosome1(int chromosome1) {
		this.chromosome1 = chromosome1;
	}

	public int getChromosome2() {
		return chromosome2;
	}

	public void setChromosome2(int chromosome2) {
		this.chromosome2 = chromosome2;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}
	
	

}

