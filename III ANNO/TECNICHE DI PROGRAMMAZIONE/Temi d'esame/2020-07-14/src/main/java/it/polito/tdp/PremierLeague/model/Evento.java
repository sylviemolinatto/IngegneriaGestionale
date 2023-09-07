package it.polito.tdp.PremierLeague.model;

public class Evento implements Comparable<Evento>{
	
	Match match;

	public Evento(Match match) {
		this.match = match;
	}

	public Match getMatch() {
		return match;
	}

	public void setMatch(Match match) {
		this.match = match;
	}

	@Override
	public int compareTo(Evento o) {
		// TODO Auto-generated method stub
		return this.match.getDate().compareTo(o.getMatch().getDate());
	}
	

}
