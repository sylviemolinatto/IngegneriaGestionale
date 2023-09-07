package it.polito.tdp.extflightdelays.model;

public class CoppiaID {
	
	private Integer originAirportId;
	private Integer destinationAirportId;
	private Double avdDistance;
	
	public CoppiaID(Integer originAirportId, Integer destinationAirportId, Double avgDistance) {
		super();
		this.originAirportId = originAirportId;
		this.destinationAirportId = destinationAirportId;
		this.avdDistance = avgDistance;
	}

	public Integer getOriginAirportId() {
		return originAirportId;
	}

	public void setOriginAirportId(Integer originAirportId) {
		this.originAirportId = originAirportId;
	}

	public Integer getDestinationAirportId() {
		return destinationAirportId;
	}

	public void setDestinationAirportId(Integer destinationAirportId) {
		this.destinationAirportId = destinationAirportId;
	}

	public Double getAvdDistance() {
		return avdDistance;
	}

	public void setAvdDistance(Double avdDistance) {
		this.avdDistance = avdDistance;
	}
	
	
	
	

}
