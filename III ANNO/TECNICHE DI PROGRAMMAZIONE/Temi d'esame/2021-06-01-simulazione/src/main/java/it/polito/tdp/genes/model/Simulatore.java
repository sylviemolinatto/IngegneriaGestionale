package it.polito.tdp.genes.model;

import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;

import it.polito.tdp.genes.model.Evento.EventType;

public class Simulatore {
	
	// Modello
	List<Evento> eventi;
	
	// Parametri della simulazione
	private int NUM_INGEGNERI;
	private Genes GENE_PARTENZA;
	private Graph<Genes,DefaultWeightedEdge> grafo;
	private double probStessoGene = 0.3;
	private int DURATA_PROGETTO = 36;
	private int month;
	
	
	// Coda degli eventi
	PriorityQueue<Evento> coda;
	
	public void init(int nIngegneri, Genes gene, Graph<Genes,DefaultWeightedEdge> grafo) {
		this.eventi = new ArrayList<Evento>();
		this.coda = new PriorityQueue<Evento>();
		this.NUM_INGEGNERI=nIngegneri;
		this.GENE_PARTENZA=gene;
		this.grafo = grafo;
		this.month=0;
		
	    for(int i=0; i<this.NUM_INGEGNERI;i++) {
	    	coda.add(new Evento(EventType.STESSO_GENE,month,gene));
	    }
	    month++;
	}
	

	private void creaEvento(Genes gene, Graph<Genes,DefaultWeightedEdge> grafo, int Eventmonth) {
		
			if(Math.random()<this.probStessoGene) {
				this.coda.add(new Evento(EventType.STESSO_GENE,Eventmonth+1,gene));
				this.eventi.add(new Evento(EventType.STESSO_GENE,Eventmonth+1,gene));
			}
			else {
				double pesoTot=0;
				for(Genes g : Graphs.neighborListOf(grafo,gene)) {
					{
						pesoTot=grafo.getEdgeWeight(grafo.getEdge(gene, g));
					}
				}
				
				double prob = Math.random()*pesoTot;
				Genes geneScelto = null;
				double somma=0.0;
				for(Genes g : Graphs.neighborListOf(grafo,gene)){
					somma+=grafo.getEdgeWeight(grafo.getEdge(gene, g));
					if(somma>prob) {
						geneScelto=g;
						break;
					}
				}
				
				if(geneScelto!=null) {
					coda.add(new Evento(EventType.GENE_ADIACENTE,Eventmonth+1,geneScelto));
					this.eventi.add(new Evento(EventType.STESSO_GENE,Eventmonth+1,gene));
				}
			}
			
	}


	public void run() {
		while(!coda.isEmpty()) {
			Evento e = coda.poll();
			processEvent(e);
		}
	}
	
	private void processEvent(Evento e) {
		
		if(e.getMonth()<=this.DURATA_PROGETTO) {
			this.creaEvento(e.getGene(), grafo, e.getMonth());
		}
	}
	
	public Map<Genes,Integer> geniInStudioENumIng(int nIngegneri, Genes genes, Graph<Genes, DefaultWeightedEdge> graph){
		
	    this.init(nIngegneri, genes, graph);
	    this.run();
		Map<Genes,Integer> result = new HashMap<Genes,Integer>();
		for(Evento e : this.eventi) {
			if(e.getMonth()==36 && !result.containsKey(e.getGene())) {
				result.put(e.getGene(),0);
			}
		}
		
		for(Genes g : result.keySet()) {
			for(Evento e : this.eventi) {
				if(e.getMonth()==36 && e.getGene().equals(g)) {
					result.replace(g,result.get(g), result.get(g)+1);
				}
			}
		}
		return result;
	}

}
