package it.polito.tdp.food.model;

public class Evento implements Comparable<Evento> {

	public enum EventType{
		INIZIO_PREPARAZIONE,
		TERMINE_PREPARAZIONE
	}
	
	private EventType type;
	private double time;
	private Food food;
	private int numPostazione;
	
	public Evento(EventType type, double time, Food food, int numPostazione) {
		this.type = type;
		this.time = time;
		this.food = food;
		this.numPostazione = numPostazione;
	}

	public EventType getType() {
		return type;
	}

	public void setType(EventType type) {
		this.type = type;
	}

	public double getTime() {
		return time;
	}

	public void setTime(double time) {
		this.time = time;
	}

	public Food getFood() {
		return food;
	}

	public void setFood(Food food) {
		this.food = food;
	}

	public int getNumPostazione() {
		return numPostazione;
	}

	public void setNumPostazione(int numPostazione) {
		this.numPostazione = numPostazione;
	}

	@Override
	public int compareTo(Evento o) {
		return Double.compare(time, o.time);
	}
	
	
	
	
	
}
