package it.polito.tdp.nyc.model;

import java.time.Duration;


public class Evento implements Comparable<Evento> {
	
	public enum EventType{
		ASSEGNAZIONE_LAVORO,
		TERMINE_LAVORO,
		NUOVO_QUARTIERE
	}
	private EventType type;
	private Duration time;
	private int idTecnico;
	private String hotspot;
	private String quartiere;
	
	public Evento(EventType type,Duration time, int idTecnico, String hotspot, String quartiere) {
		this.type=type;
		this.time=time;
		this.idTecnico = idTecnico;
		this.hotspot = hotspot;
		this.quartiere = quartiere;
	}

	
	public Duration getTime() {
		return time;
	}

	public void seiTime(Duration time) {
		this.time=time;
	}

	public int getIdTecnico() {
		return idTecnico;
	}

	public void setIdTecnico(int idTecnico) {
		this.idTecnico = idTecnico;
	}

	public String getHotspot() {
		return hotspot;
	}

	public void setHotspost(String hotspot) {
		this.hotspot = hotspot;
	}

	public String getQuartiere() {
		return quartiere;
	}

	public void setQuartiere(String quartiere) {
		this.quartiere = quartiere;
	}

	public EventType getType() {
		return type;
	}


	public void setType(EventType type) {
		this.type = type;
	}
	

	@Override
	public int compareTo(Evento o) {
		return this.time.compareTo(o.getTime());	
	}
	
	
	
	

}
