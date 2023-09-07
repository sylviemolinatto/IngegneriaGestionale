package it.polito.tdp.crimes.model;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

import it.polito.tdp.crimes.db.EventsDao;

public class Model {
	
	private Graph<String,DefaultWeightedEdge> grafo;
	private EventsDao dao;
	private List<String> best;
	
	public Model() {
		dao = new EventsDao();
	}
	
	public List<String> getCategorieReato(){
		return this.dao.getCategorieReato();
	}
	
	public Set<DefaultWeightedEdge> getArchi(){
		return this.grafo.edgeSet();
	}
	
	public Graph<String,DefaultWeightedEdge> getGrafo(){
		return this.grafo;
	}
	
	public void creaGrafo(String categoria, int mese) {
		grafo = new SimpleWeightedGraph<String,DefaultWeightedEdge>(DefaultWeightedEdge.class);
		
		// aggiunta vertici
		Graphs.addAllVertices(this.grafo, dao.getVertici(categoria, mese));
		
		// aggiunta archi
		for(Adiacenza a : dao.getArchi(categoria, mese)) {
			Graphs.addEdgeWithVertices(this.grafo,a.getV1(), a.getV2(), a.getPeso());
		}
		
		System.out.println("Grafo creato!");
		System.out.println("# VERTICI : "+this.grafo.vertexSet().size());
		System.out.println("# ARCHI : "+this.grafo.edgeSet().size());
	}
	
	public List<Adiacenza> getArchiMaggioriPesoMedio(){
		
		double pesoTot=0.0;
		
		for(DefaultWeightedEdge e : this.grafo.edgeSet()) {
			pesoTot+=this.grafo.getEdgeWeight(e);
		}
		double avg = pesoTot/this.grafo.edgeSet().size();
		System.out.println(avg);
		List<Adiacenza> result = new ArrayList<Adiacenza>();
		
		for(DefaultWeightedEdge e : this.grafo.edgeSet()) {
			if(this.grafo.getEdgeWeight(e)>avg) {
				result.add(new Adiacenza(this.grafo.getEdgeSource(e),this.grafo.getEdgeTarget(e),(int)this.grafo.getEdgeWeight(e)));
			}
		}
		return result;
	}
	
	public List<String> calcolaPercorso(String sorgente, String destinazione){
		
		this.best = new LinkedList<String>();
		List<String> parziale = new LinkedList<String>();
		parziale.add(sorgente);
		cerca(parziale,destinazione);
		
		return best;
	}

	private void cerca(List<String> parziale, String destinazione) {
	
		// caso terminale
		if(parziale.get(parziale.size()-1).equals(destinazione)){
			if(parziale.size()>best.size()) {
				best = new LinkedList<String>(parziale);
			}
			return;
		}
		
		// scorro i vicini dell'ultimo inserito e provo le varie strade
		for(String v : Graphs.neighborListOf(this.grafo,parziale.get(parziale.size()-1))) {
			if(!parziale.contains(v)) {
				parziale.add(v);
				cerca(parziale,destinazione);
				parziale.remove(parziale.size()-1);
			}
			
		}
		
	}
}
