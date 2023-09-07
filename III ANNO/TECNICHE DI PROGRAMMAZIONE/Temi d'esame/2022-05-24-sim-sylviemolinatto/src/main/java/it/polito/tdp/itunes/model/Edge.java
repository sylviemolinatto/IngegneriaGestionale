package it.polito.tdp.itunes.model;

public class Edge {
	
	int trackID1;
	int trackID2;
	double weight;
	
	public Edge(int trackID1, int trackID2, double weight) {
		this.trackID1 = trackID1;
		this.trackID2 = trackID2;
		this.weight = weight;
	}

	public int getTrackID1() {
		return trackID1;
	}

	public void setTrackID1(int trackID1) {
		this.trackID1 = trackID1;
	}

	public int getTrackID2() {
		return trackID2;
	}

	public void setTrackID2(int trackID2) {
		this.trackID2 = trackID2;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}
	
	
	
	

}
