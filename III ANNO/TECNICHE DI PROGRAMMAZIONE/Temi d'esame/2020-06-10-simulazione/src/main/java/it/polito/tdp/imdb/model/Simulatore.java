package it.polito.tdp.imdb.model;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;

import it.polito.tdp.imdb.model.Event.EventType;

public class Simulatore {
	
	// input
	private int numGiorni;
	private List<Actor> attori;
	
	// output
	private List<Actor> attoriIntervistati;
	private int numPause;
	
	// coda degli eventi
	PriorityQueue<Event> coda;
	
	// stato del mondo simulato
	private Graph<Actor,DefaultWeightedEdge> grafo;
	
	public Simulatore(Graph<Actor,DefaultWeightedEdge> grafo, int numGiorni) {
		this.grafo = grafo;
		this.numGiorni = numGiorni;
		this.attori = new ArrayList<Actor>(this.grafo.vertexSet());
	}
	
	public void init() {
		
		this.coda = new PriorityQueue<Event>();
		
		// inserisco il primo evento nella coda
		int random = (int) (Math.random()*this.attori.size());
		coda.add(new Event(EventType.INTERVISTA,this.attori.get(random),1));
		
		this.numPause=0;
		this.attoriIntervistati = new ArrayList<Actor>();
		
	}
	
	public void run() {
		
		while(!this.coda.isEmpty()) {
			Event e = this.coda.poll();
			processEvent(e);
		}
	}

	private void processEvent(Event e) {
		
		switch(e.getType()) {
		
		case INTERVISTA:
			Actor ultimoAttoreIntervistato = e.getIntervistato();
			this.attoriIntervistati.add(ultimoAttoreIntervistato);
			this.attori.remove(e.getIntervistato());
			if(e.getGiorno()==this.numGiorni) {
				return;
			}
			
			Actor nuovoAttore;

			// se il regista ha intervistato due attori dello stesso genere per due giorni consecutivi allora 
			// c'è il 90 % di possibilità che si prenda una pausa
			if(attoriIntervistati.size()>2 && attoriIntervistati.get(attoriIntervistati.size()-1).gender.equals(attoriIntervistati.get(attoriIntervistati.size()-2).gender)) {
					if(Math.random()<=0.9) {
						this.coda.add(new Event(EventType.PAUSA,null,e.getGiorno()+1));
					}
					else {
						double random = Math.random();
						if(random<=0.6) {
							nuovoAttore = this.attori.get((int) (Math.random()*this.attori.size()));
							this.coda.add(new Event(EventType.INTERVISTA,nuovoAttore,e.getGiorno()+1));
						}
						else {
							nuovoAttore = suggerito(ultimoAttoreIntervistato, attoriIntervistati);
							this.coda.add(new Event(EventType.INTERVISTA,nuovoAttore,e.getGiorno()+1));
						}
					}	
			}
			else {
				double random = Math.random();
				if(random<=0.6) {
					nuovoAttore = this.attori.get((int) (Math.random()*this.attori.size()));
					this.coda.add(new Event(EventType.INTERVISTA,nuovoAttore,e.getGiorno()+1));
				}
				else {
					nuovoAttore = suggerito(ultimoAttoreIntervistato, attoriIntervistati);
					this.coda.add(new Event(EventType.INTERVISTA,nuovoAttore,e.getGiorno()+1));
				}
			}
			
			break;
			
		case PAUSA:
			
			this.numPause++;
			if(e.getGiorno()==this.numGiorni) {
				return;
			}
			else {
				coda.add(new Event(EventType.INTERVISTA,this.attori.get((int) (Math.random()*this.attori.size())),e.getGiorno()+1));
			}
			break;
		}
		
	}

	private Actor suggerito(Actor ultimoAttoreIntervistato, List<Actor> attoriIntervistati) {
		
		Actor suggerito = null;
		int max=0;
		List<Actor> vicini = Graphs.neighborListOf(this.grafo, ultimoAttoreIntervistato);
		if(vicini.size()>0) {
			for(Actor a : vicini) {
				if(this.grafo.getEdgeWeight(this.grafo.getEdge(ultimoAttoreIntervistato, a))>max && !attoriIntervistati.contains(a)) {
					suggerito = a;
				}
			}
		}
		else {
			suggerito = this.attori.get((int) (Math.random()*this.attori.size()));
		}
		
		return suggerito;
	}
	
	public int getNumPause() {
		return this.numPause;
	}
	
	public List<Actor> getIntervistati(){
		return this.attoriIntervistati;
	}

}
