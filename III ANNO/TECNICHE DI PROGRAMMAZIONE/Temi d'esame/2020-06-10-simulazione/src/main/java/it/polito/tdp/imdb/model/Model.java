package it.polito.tdp.imdb.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.alg.connectivity.ConnectivityInspector;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

import it.polito.tdp.imdb.db.ImdbDAO;

public class Model {

	private ImdbDAO dao;
	private Graph<Actor,DefaultWeightedEdge> grafo;
	private Map<Integer,Actor> idMap;
	private ConnectivityInspector<Actor,DefaultWeightedEdge> ispettore;
	private Simulatore sim;
	
	public Model() {
		this.dao = new ImdbDAO();
		this.idMap = new HashMap<Integer,Actor>();
	}
	
	public void creaGrafo(String genre) {
		
		this.grafo = new SimpleWeightedGraph<Actor,DefaultWeightedEdge>(DefaultWeightedEdge.class);
		
		Graphs.addAllVertices(this.grafo, this.dao.getVertici(genre));
		
		for(Actor a : this.dao.getVertici(genre)) {
			this.idMap.put(a.getId(), a);
		}
		
		for(Edge e : this.dao.getArchi(genre, idMap)) {
			Graphs.addEdgeWithVertices(this.grafo, e.getA1(), e.getA2(), e.getWeight());
		}
		
		System.out.println("Grafo Creato!\n");
		System.out.println("# VERTICI : "+this.grafo.vertexSet().size()+"\n");
		System.out.println("# ARCHI : "+this.grafo.edgeSet().size()+"\n");
		
	}
	
	public List<Actor> attoriSimili(Actor a){
		
		this.ispettore = new ConnectivityInspector<Actor,DefaultWeightedEdge>(this.grafo);
		List<Actor> simili = new ArrayList<Actor>(ispettore.connectedSetOf(a));
		simili.remove(a);
		return simili;
	}
	
	public void simula(int numGiorni) {
		this.sim = new Simulatore(this.grafo,numGiorni);
		this.sim.init();
		this.sim.run();
	}
	
	public int numPause() {
		return this.sim.getNumPause();
	}
	
	public List<Actor> getIntervistati(){
		return this.sim.getIntervistati();
	}
	
	public List<String> listAllGenres(){
		return this.dao.listAllGenres();
	}
	
	public List<Actor> getActors(){
		return new ArrayList<Actor>(this.grafo.vertexSet());
	}
	
	public int getNVertici() {
		return this.grafo.vertexSet().size();
	}
	
	public int getNArchi() {
		return this.grafo.edgeSet().size();
	}
	
	public boolean grafoCreato() {
		if(this.grafo==null) {
			return false;
		}
		else 
			return true;
	}
}
