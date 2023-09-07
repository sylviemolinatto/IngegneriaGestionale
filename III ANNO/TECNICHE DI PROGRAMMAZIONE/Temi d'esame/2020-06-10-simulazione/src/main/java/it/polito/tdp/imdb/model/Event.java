package it.polito.tdp.imdb.model;

public class Event implements Comparable<Event>{

	public enum EventType{
		INTERVISTA,
		PAUSA
	}
	
	private EventType type;
	private Actor intervistato;
	private int giorno;
	
	public Event(EventType type, Actor intervistato, int giorno) {
		this.type = type;
		this.intervistato = intervistato;
		this.giorno = giorno;
	}

	public EventType getType() {
		return type;
	}

	public Actor getIntervistato() {
		return intervistato;
	}

	public int getGiorno() {
		return giorno;
	}

	@Override
	public int compareTo(Event o) {
		
		return this.giorno-o.giorno;
	}
	
	
	
	
}
