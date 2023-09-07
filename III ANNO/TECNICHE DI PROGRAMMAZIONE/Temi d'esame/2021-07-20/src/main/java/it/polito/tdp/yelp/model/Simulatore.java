package it.polito.tdp.yelp.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;

import it.polito.tdp.yelp.model.Evento.EventType;

public class Simulatore {

	// Dati in ingresso
	private int x1;
	private int x2;
	
	// Dati in uscita

	// I giornalisti sono rappresentati da un numero compreso tra 0 e x1-1
	private List<Giornalista> giornalisti;
	private int numeroGiorni;
	
	// Modello del mondo
	private Set<User> intervistati;
	private Graph<User,DefaultWeightedEdge> grafo;
	
	// Coda degli eventi
	PriorityQueue<Evento> queue;
	
	public Simulatore(Graph<User,DefaultWeightedEdge> grafo) {
		this.grafo=grafo;
	}
	
	public void init(int x1, int x2) {
		this.x1=x1;
		this.x2=x2;
		this.intervistati=new HashSet<User>();
		this.numeroGiorni=0;
		this.giornalisti = new ArrayList<Giornalista>();
		this.queue = new PriorityQueue<Evento>();
		
		for(int id=0;id<this.x1;id++) {
			this.giornalisti.add(new Giornalista(id));
		}
		
		// pre-carico la coda
		for(Giornalista g : giornalisti) {
			User intervistato = this.selezionaIntervistato(this.grafo.vertexSet());
			this.intervistati.add(intervistato);
			g.incrementaNumeroIntervistati();
			this.queue.add(new Evento(EventType.DA_INTERVISTARE,1,intervistato,g));
		}
	}
		
	
	public void run() {
		
		while(!this.queue.isEmpty() && this.intervistati.size()<x2) {
			Evento e = this.queue.poll();
			this.numeroGiorni=e.getGiorno();
			
			processEvent(e);
		}
	}

	private void processEvent(Evento e) {
		
		switch(e.getType()) {
		
		case DA_INTERVISTARE:
			
			double caso = Math.random();
			
			if(caso<0.6) { // CASO I
				User vicino = this.selezionaAdiacente(e.getIntervistato());
				if(vicino==null) {
					vicino = this.selezionaIntervistato(this.grafo.vertexSet());
				}
			this.queue.add(new Evento(EventType.DA_INTERVISTARE,e.getGiorno()+1,vicino,e.getGiornalista()));
			this.intervistati.add(vicino);
			e.getGiornalista().incrementaNumeroIntervistati();
				
				
			}else if(caso<0.8) { // CASO II
				
				this.queue.add(new Evento(EventType.FERIE,e.getGiorno()+1,e.getIntervistato(),e.getGiornalista()));
				
			}else { // CASO III : domani continuo con lo stesso intervistato
				
				this.queue.add(new Evento(e.getType(),e.getGiorno()+1,e.getIntervistato(),e.getGiornalista()));
				
			}
			break;
			
		case FERIE:
			User vicino = this.selezionaAdiacente(e.getIntervistato());
			if(vicino==null) {
				vicino = this.selezionaIntervistato(this.grafo.vertexSet());
			}
		    this.queue.add(new Evento(EventType.DA_INTERVISTARE,e.getGiorno()+2,vicino,e.getGiornalista()));
		    this.intervistati.add(vicino);
		    e.getGiornalista().incrementaNumeroIntervistati();
			
			break;
		}
	}

	public int getX1() {
		return x1;
	}

	public void setX1(int x1) {
		this.x1 = x1;
	}

	public int getX2() {
		return x2;
	}

	public void setX2(int x2) {
		this.x2 = x2;
	}

	public List<Giornalista> getGiornalisti() {
		return giornalisti;
	}

	public int getNumeroGiorni() {
		return numeroGiorni;
	}
	
	/*
	 * Seleziona un intervistato dalla lista specificata, evitando
	 * di selezionare coloro che sono già in this.intervistati
	 * 
	 */
	private User selezionaIntervistato(Collection<User> lista) {
		Set<User> candidati = new HashSet<User>(lista);
		candidati.removeAll(this.intervistati);
		
		int scelto = (int)Math.random()*candidati.size();
		 return (new ArrayList<User>(candidati).get(scelto)); 
	}
	
	private User selezionaAdiacente(User u) {
		
		List<User> vicini = Graphs.neighborListOf(this.grafo,u);
		vicini.removeAll(this.intervistati);
		
		if(vicini.size()==0) {
			return null; 
			// vertice isolaro
			// oppure tutti adiacenti già intervistati
		}
		
		double max=0;
		for(User v : vicini) {
			double peso = this.grafo.getEdgeWeight(this.grafo.getEdge(u, v));
			if(peso > max) {
				max=peso;
				
			}
		}
		
		List<User> migliori = new ArrayList<User>();
		for(User v : vicini) {
			double peso = this.grafo.getEdgeWeight(this.grafo.getEdge(u, v));
			if(peso == max) {
				migliori.add(v);
			}
		}
		
		int scelto = (int)(Math.random()*migliori.size());
		return migliori.get(scelto);
	}
	
	
	
	
}
