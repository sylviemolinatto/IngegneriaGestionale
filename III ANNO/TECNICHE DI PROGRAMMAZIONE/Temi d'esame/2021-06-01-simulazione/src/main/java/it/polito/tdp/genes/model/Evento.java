package it.polito.tdp.genes.model;

import java.time.Duration;

public class Evento implements Comparable<Evento> {
	
	public enum  EventType{
		STESSO_GENE,
		GENE_ADIACENTE
	}
	
	private EventType type;
	private int month;
	private Genes gene;
	
	public Evento(EventType type,int month, Genes gene) {
		this.type = type;
		this.month = month;
		this.gene = gene;
	}

	public EventType getType() {
		return type;
	}

	public void setType(EventType type) {
		this.type = type;
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public Genes getGene() {
		return gene;
	}

	public void setGene(Genes gene) {
		this.gene = gene;
	}

	@Override
	public int compareTo(Evento o) {
		// TODO Auto-generated method stub
		return this.month-o.month;
	}
	
	

}
