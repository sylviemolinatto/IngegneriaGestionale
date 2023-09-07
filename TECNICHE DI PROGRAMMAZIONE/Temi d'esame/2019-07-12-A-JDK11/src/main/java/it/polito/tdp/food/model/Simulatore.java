package it.polito.tdp.food.model;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;

import it.polito.tdp.food.model.Evento.EventType;

public class Simulatore {

	// input
	private int K; //num stazioni di lavoro
	private Food foodIniziale;
	
	// output
	private List<Food> cibiPreparati;
	private double tempoTotale;
	
	// stato del mondo simulato
	private Graph<Food,DefaultWeightedEdge> grafo;
	
	// coda degli eventi
	private PriorityQueue<Evento> coda;
	
	public void init(int K, Food foodIniziale, List<Food> adiacenti, Graph<Food,DefaultWeightedEdge> grafo) {
		
		this.K=K;
		this.foodIniziale=foodIniziale;
		this.grafo=grafo;
		
		this.cibiPreparati = new ArrayList<Food>();
		this.coda = new PriorityQueue<Evento>();
		this.tempoTotale=0;
		
		// carico eventi nella coda
		this.coda.add(new Evento(EventType.INIZIO_PREPARAZIONE,0,foodIniziale,1));
		this.cibiPreparati.add(foodIniziale);
		
		if(adiacenti.size()>=K) {
			for(int i=2; i<=K; i++) {
				this.coda.add(new Evento(EventType.INIZIO_PREPARAZIONE,0,adiacenti.get(i-1),i));
				this.cibiPreparati.add(adiacenti.get(i-1));
			}
		}
		else {
			for(int i=2;i<=adiacenti.size();i++) {
				this.coda.add(new Evento(EventType.INIZIO_PREPARAZIONE,0,adiacenti.get(i),i));
				this.cibiPreparati.add(adiacenti.get(i));
			}
		}
	}
	
	public void run() {
		while(!this.coda.isEmpty()) {
			Evento e = this.coda.poll();
			processEvent(e);
			this.tempoTotale = e.getTime();
		}
	}

	private void processEvent(Evento e) {

		switch(e.getType()) {
		
		case INIZIO_PREPARAZIONE:
			if(!this.cibiPreparati.contains(e.getFood())) {
				this.cibiPreparati.add(e.getFood());
			}
			Food prossimo = prossimoDaPreparare(e.getFood());
			if(prossimo!=null) {
				double tempoPreparazione = this.grafo.getEdgeWeight(this.grafo.getEdge(e.getFood(), prossimo));
				// al termine della preparazione inserisco già il prossimo da preparare
				this.coda.add(new Evento(EventType.TERMINE_PREPARAZIONE,e.getTime()+tempoPreparazione,prossimo,e.getNumPostazione()));
			}
			
			break;
		
		case TERMINE_PREPARAZIONE:
			
			if(e.getFood()!=null) {
				this.coda.add(new Evento(EventType.INIZIO_PREPARAZIONE,e.getTime(),e.getFood(),e.getNumPostazione()));
			}
			break;
		}
		
	}

	private Food prossimoDaPreparare(Food food) {
		
		List<Food> adiacenti = Graphs.neighborListOf(this.grafo, food);
		int min=0;
		Food prossimo = null;
		for(Food f : adiacenti) {
			if(this.grafo.getEdgeWeight(this.grafo.getEdge(food,f))>min && !this.cibiPreparati.contains(f)) {
				prossimo = f;
			}
		}
		return prossimo;
	}
	
	public double getTempoTotale() {
		return this.tempoTotale;
	}
	
	public List<Food> getCibiPreparati(){
		return this.cibiPreparati;
	}
}
