package it.polito.tdp.PremierLeague.model;

public class Evento implements Comparable<Evento> {

	public enum EventType{
		GOAL,
		ESPULSIONE,
		INFORTUNIO
	}
	
	private EventType type;
	private int time;
	
	public Evento(EventType type, int time) {
		this.type = type;
		this.time = time;
	}

	public EventType getType() {
		return type;
	}

	public void setType(EventType type) {
		this.type = type;
	}

	public int getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
	}

	@Override
	public int compareTo(Evento o) {
		
		return this.time-o.time;
	}
	
	
	
	
}
