package it.polito.tdp.borders.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultEdge;

public class Simulatore {

	// Coda degli eventi
	private PriorityQueue<Evento> queue;
	
	// Parametri di simulazione
	private int nInizialeMigranti;
	private Country nazioneIniziale;
	
	// Output della simulazione
	private int nPassi; // T
	private Map<Country,Integer> persone; // oppure List<CountryAndNumber> personeStanziali

	// Stato del mondo simulato
	private Graph<Country,DefaultEdge> grafo;
	// Map persone Country --> Integer


	public Simulatore(Graph<Country, DefaultEdge> grafo) {
		super();
		this.grafo = grafo;
	}
	
	public void init(Country partenza, int migranti) {
		this.nazioneIniziale=partenza;
		this.nInizialeMigranti=migranti;
		this.persone=new HashMap<Country,Integer>();
		for(Country c : this.grafo.vertexSet()) {
			this.persone.put(c, 0);
		}
		this.queue = new PriorityQueue<>();
		this.queue.add(new Evento(1,this.nazioneIniziale,this.nInizialeMigranti));
	}
	
	public void run() {
		while(!this.queue.isEmpty()) {
			Evento e = this.queue.poll();
			processEvent(e);
		}
	}

	private void processEvent(Evento e) {
		
		int stanziali = e.getPersone()/2;
		int migranti = e.getPersone()-stanziali;
		int confinanti = this.grafo.degreeOf(e.getNazione()); 
		int gruppiMigranti = migranti/confinanti;
	    stanziali += migranti%confinanti;
	    this.persone.put(e.getNazione(), this.persone.get(e.getNazione())+stanziali);
	    this.nPassi=e.getTime();
	    
	    List<Country> neighbours = Graphs.neighborListOf(this.grafo,e.getNazione());
	    if(gruppiMigranti!=0) {
	    	for(Country c : neighbours) {
	    		this.queue.add(new Evento(e.getTime()+1,c,gruppiMigranti));
	    	}
	    }
		
	}

	public int getnPassi() {
		return nPassi;
	}
	
	public Map<Country, Integer> getPersone() {
		return persone;
	}
	
	
	
	
}
