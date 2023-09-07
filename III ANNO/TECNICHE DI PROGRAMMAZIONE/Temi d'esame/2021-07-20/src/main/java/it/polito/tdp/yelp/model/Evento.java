package it.polito.tdp.yelp.model;

public class Evento implements Comparable<Evento> {

	public enum EventType{
		DA_INTERVISTARE,
		FERIE,
	}
	
	private EventType type;
	private int giorno;
	private User intervistato;
	private Giornalista giornalista;
	public Evento(EventType type, int giorno, User intervistato, Giornalista giornalista) {
		super();
		this.type = type;
		this.giorno = giorno;
		this.intervistato = intervistato;
		this.giornalista = giornalista;
	}
	public EventType getType() {
		return type;
	}
	public int getGiorno() {
		return giorno;
	}
	public User getIntervistato() {
		return intervistato;
	}
	public Giornalista getGiornalista() {
		return giornalista;
	}
	@Override
	public int compareTo(Evento o) {
		// TODO Auto-generated method stub
		return this.giorno-o.giorno;
	}
	
	
}
