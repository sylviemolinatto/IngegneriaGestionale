package it.polito.tdp.genes.model;

import java.util.LinkedList;
import java.util.List;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleDirectedWeightedGraph;

import it.polito.tdp.genes.db.GenesDao;

public class Model {
	
	private Graph<Integer,DefaultWeightedEdge> grafo;
	private GenesDao dao;
	private List<Integer> best;
	
	public Model() {
		this.grafo = new SimpleDirectedWeightedGraph<Integer,DefaultWeightedEdge>(DefaultWeightedEdge.class);
		this.dao = new GenesDao();
	}
	
	public void creaGrafo() {
		
		// aggiungo i vertici (Cromosomi)
		Graphs.addAllVertices(this.grafo, this.dao.getAllVertex());
		
		// aggiungo gli archi e il peso
		for(Edge e : dao.getAllEdges()) {
			Graphs.addEdge(this.grafo, e.getChromosome1(), e.getChromosome2(), e.getWeight());
		}
	}
	
	public Graph getGrafo() {
		return this.grafo;
	}
	
	public double getPesoMinimo() {
		
		double pesoMinimo=100000;
		for(DefaultWeightedEdge e : this.grafo.edgeSet()) {
			if(this.grafo.getEdgeWeight(e)<pesoMinimo) {
				pesoMinimo=this.grafo.getEdgeWeight(e);
			}
		}
		return pesoMinimo;
	}
	
	public double getPesoMassimo() {
		
		double pesoMassimo=0;
		for(DefaultWeightedEdge e : this.grafo.edgeSet()) {
			if(this.grafo.getEdgeWeight(e)>=pesoMassimo) {
				pesoMassimo=this.grafo.getEdgeWeight(e);
			}
		}
		return pesoMassimo;
	}
	
	public List<DefaultWeightedEdge> getNumArchiSopraSoglia(double soglia) {
		
		List<DefaultWeightedEdge> edgesSopraLaSoglia = new LinkedList<DefaultWeightedEdge>();
		for(DefaultWeightedEdge e : this.grafo.edgeSet()) {
			if(this.grafo.getEdgeWeight(e)>soglia) {
				edgesSopraLaSoglia.add(e);
			}
		}
		
		return edgesSopraLaSoglia;
	}
	
	public List<Integer> visitaGrafo(double soglia){
		
		List<Integer> parziale = new LinkedList<Integer>();
		this.best=null;
		this.recursive(this.grafo,0,parziale,soglia);
		
		return best;
	}

	private void recursive(Graph<Integer, DefaultWeightedEdge> grafo2, int livello, List<Integer> parziale, double soglia) {
		
		// condizione di terminazione
		if(condizioneDiTerminazione(parziale,soglia)) {

			double lunghezza = calcolaLunghezza(parziale);
			if(best==null || calcolaLunghezza(best)<lunghezza ) {
				best = new LinkedList<Integer>(parziale);
			}
		}
		else {
			
			for(int prova : this.grafo.vertexSet()) {
				if(aggiuntaValida(prova,parziale,soglia)) {
					parziale.add(prova);
					this.recursive(grafo, livello+1, parziale, soglia);
				
				}
			}
	    }
	}

	private boolean condizioneDiTerminazione(List<Integer> parziale, double soglia) {
		if(parziale.size()>0) {
			// non ci sono più vertici che io possa aggiungere
			int ultimoVertice = parziale.get(parziale.size()-1);
			int conta=0;
			for(int vertex : this.grafo.vertexSet()) {
				if(!aggiuntaValida(vertex,parziale,soglia)) {
					conta++;
				}
			}
		
			if(conta==this.grafo.vertexSet().size()) {
				return true;
			}
		}
		return false;
	}

	private double calcolaLunghezza(List<Integer> parziale) {
		double l = 0;
		for(int i=0;i<parziale.size()-1;i++) {
			l+=this.grafo.getEdgeWeight(this.grafo.getEdge(parziale.get(i),parziale.get(i+1)));
		}
		return l;
	}

	private boolean aggiuntaValida(int prova, List<Integer> parziale, double soglia) {
		
		if(parziale.size()==0) {
			return true;
		}
		
		else{
			int ultimoVertice = parziale.get(parziale.size()-1);
			
			// L'aggiunta è valida se esiste un arco che connette l'ultimo vertice in parziale e il nuovo e se il peso dell'arco è maggiore della soglia
			if(this.grafo.containsEdge(ultimoVertice, prova) && this.grafo.getEdgeWeight(this.grafo.getEdge(ultimoVertice,prova))>soglia && !parziale.contains(prova)) {
				return true;
			}
			return false;
		}
	}
	
	public DefaultWeightedEdge getEdge(int source, int destination) {
		
		return this.grafo.getEdge(source,destination);
	}
	
	public double getWeight(DefaultWeightedEdge e) {
		return this.grafo.getEdgeWeight(e);
	}
	
}