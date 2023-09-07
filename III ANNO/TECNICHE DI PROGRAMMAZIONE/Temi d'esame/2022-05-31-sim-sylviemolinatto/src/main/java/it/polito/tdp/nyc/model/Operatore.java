package it.polito.tdp.nyc.model;

public class Operatore {

	private int id;
	private int numHotspotRevisionati;
	
	public Operatore(int id) {
		this.id=id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getNumHotspotRevisionati() {
		return numHotspotRevisionati;
	}

	public void incrementaNumHotspotRevisionati() {
		this.numHotspotRevisionati++;
	}

	@Override
	public String toString() {
		return " id Operatore =" + id + ", numHotspotRevisionati=" + numHotspotRevisionati;
	}
	
	
	
}
