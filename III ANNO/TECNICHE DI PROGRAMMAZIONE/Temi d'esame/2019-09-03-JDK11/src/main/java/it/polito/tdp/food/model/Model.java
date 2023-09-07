package it.polito.tdp.food.model;

import java.util.ArrayList;
import java.util.List;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.alg.connectivity.ConnectivityInspector;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

import it.polito.tdp.food.db.FoodDao;

public class Model {
	
	private FoodDao dao;
	private Graph<String,DefaultWeightedEdge> grafo;
	private List<Edge> archi;
	private List<String> best;
	private int pesoMax;
	
	public Model() {
		this.dao = new FoodDao();
	}
	
	public void creaGrafo(int C) {
		
		this.grafo = new SimpleWeightedGraph<String,DefaultWeightedEdge>(DefaultWeightedEdge.class);
		
		Graphs.addAllVertices(this.grafo, this.dao.getVertici(C));
		
		this.archi = this.dao.getArchi(C);
		
		for(Edge e : archi ) {
			Graphs.addEdgeWithVertices(this.grafo, e.getPortionName1(), e.getPortionName2(), e.getWeight());
		}
		
		System.out.println("GRAFO CREATO : "+this.grafo.vertexSet().size()+" vertici, "+this.grafo.edgeSet().size()+" archi\n\n");
	}
	
	public List<String> getVicini (String porzione){
		
		List<String> vicini = Graphs.neighborListOf(this.grafo, porzione);
		return vicini;
		
	}
	
	public List<String> cercaSequenza(int lunghezza, String porzione) {
		
		this.best = new ArrayList<String>();
		
		this.pesoMax = 0;
		
		List<String> parziale = new ArrayList<String>();
		parziale.add(porzione);

		ricorsiva(parziale, 1, lunghezza);
		
		return best;
	}
	
	private void ricorsiva(List<String> parziale, int livello, int lunghezza) {
		
		if(livello == lunghezza+1) {
			int peso = calcolaPeso(parziale) ;
			if(peso>this.pesoMax) {
				this.pesoMax=peso ;
				this.best = new ArrayList<>(parziale);
			}
			return ;
		}
		
		List<String> vicini = Graphs.neighborListOf(this.grafo, parziale.get(livello-1)) ;
		for(String v : vicini) {
			if(!parziale.contains(v)) {
				parziale.add(v) ;
				ricorsiva(parziale, livello+1, lunghezza) ;
				parziale.remove(parziale.size()-1) ;
			}
		}
		
		
	}
	
	public int getPeso() {
		return this.calcolaPeso(best);
	}

	private int calcolaPeso(List<String> parziale) {
		
		int peso=0;
		for(int i=0; i<parziale.size()-1;i++) {
			peso+=this.grafo.getEdgeWeight(this.grafo.getEdge(parziale.get(i), parziale.get(i+1)));
		}
		return peso;
	}

	public int getNVertici() {
		return this.grafo.vertexSet().size();
	}
	
	public int getNArchi() {
		return this.grafo.edgeSet().size();
	}
	
	public List<String> getVertici(){
		return new ArrayList<String>(this.grafo.vertexSet());
	}
	
	public Graph<String,DefaultWeightedEdge> getGrafo(){
		return this.grafo;
	}
	
	public boolean grafoCreato() {
		if(this.grafo==null)
			return false;
		else
			return true;
	}
	
}
