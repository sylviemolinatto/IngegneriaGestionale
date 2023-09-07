package it.polito.tdp.nyc.model;

public class Edge implements Comparable<Edge>{
	
	private String city1;
	private String city2;
	private double lat_l1;
	private double long_l1;
	private double lat_l2;
	private double long_l2;
	private double weight;
	
	public Edge(String city1, String city2, double lat_l1, double long_l1, double lat_l2, double long_l2) {
		this.city1 = city1;
		this.city2 = city2;
		this.lat_l1=lat_l1;
		this.long_l1=long_l1;
		this.lat_l2=lat_l2;
		this.long_l2=long_l2;
	}
	
	public Edge(String city1, String city2, double weight) {
		this.city1=city1;
		this.city2=city2;
		this.weight=weight;
	}

	public String getCity1() {
		return city1;
	}

	public void setCity1(String city1) {
		this.city1 = city1;
	}

	public String getCity2() {
		return city2;
	}

	public void setCity2(String city2) {
		this.city2 = city2;
	}

	public double getWeight() {
		return weight;
	}

	public double getLat_l1() {
		return lat_l1;
	}

	public void setLat_l1(double lat_l1) {
		this.lat_l1 = lat_l1;
	}

	public double getLong_l1() {
		return long_l1;
	}
	
	

	public void setLong_l1(double long_l1) {
		this.long_l1 = long_l1;
	}

	public double getLat_l2() {
		return lat_l2;
	}

	public void setLat_l2(double lat_l2) {
		this.lat_l2 = lat_l2;
	}

	public double getLong_l2() {
		return long_l2;
	}

	public void setLong_l2(double long_l2) {
		this.long_l2 = long_l2;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	@Override
	public int compareTo(Edge o) {
		
		return  Double.compare(this.weight, o.weight);
	}
	
	
	
	

}
