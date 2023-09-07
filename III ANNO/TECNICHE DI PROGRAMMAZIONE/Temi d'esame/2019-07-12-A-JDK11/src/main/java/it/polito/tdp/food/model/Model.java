package it.polito.tdp.food.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

import it.polito.tdp.food.db.FoodDao;

public class Model {

	private FoodDao dao;
	private Graph<Food,DefaultWeightedEdge> grafo;
	private Map<Integer,Food> idMap;
	private Simulatore sim;
	
	public Model() {
		this.dao = new FoodDao();
		this.idMap = new HashMap<Integer,Food>();
		sim = new Simulatore();
	}
	
	public String creaGrafo(int numPorzioni) {
		
		this.grafo = new SimpleWeightedGraph<Food,DefaultWeightedEdge>(DefaultWeightedEdge.class);
		
		Graphs.addAllVertices(this.grafo, this.dao.getVertici(numPorzioni));
		
		for(Food f : this.dao.getVertici(numPorzioni)) {
			this.idMap.put(f.getFood_code(), f);
		}
		
		for(Edge e : this.dao.getArchi(idMap)) {
			if(this.grafo.containsVertex(e.getF1()) && this.grafo.containsVertex(e.getF2())){
				Graphs.addEdgeWithVertices(this.grafo, e.getF1(), e.getF2(), e.getWeight());
			}
		}
		
		return "Grafo creato: "+this.grafo.vertexSet().size()+" vertici, "+this.grafo.edgeSet().size()+" archi\n";
	}
	
	
	public List<Edge> getMaxCalorieCongiunte(Food f){
		
		List<Food> adiacenti = Graphs.neighborListOf(this.grafo, f);
		List<Edge> archi = new ArrayList<Edge>();
		for(Food food : adiacenti) {
			archi.add(new Edge(f,food, this.grafo.getEdgeWeight(this.grafo.getEdge(f, food))));
		}
		
		Collections.sort(archi);
		return archi;
	}
	
	public List<Food> getAdiacentiOrdinati(Food f){
		
		List <Edge> a = this.getMaxCalorieCongiunte(f);
		List<Food> result = new ArrayList<Food>();
		for(Edge e : a) {
			result.add(e.getF2());
		}
		return result;
		
	}
	
	public double simula(int K, Food foodIniziale) {
		sim = new Simulatore();
		sim.init(K, foodIniziale, this.getAdiacentiOrdinati(foodIniziale), grafo);
		sim.run();
		return sim.getTempoTotale();
	}
	
	public List<Food> getCibiPreparati() {
		return sim.getCibiPreparati();
	}
	
	public boolean grafoCreato() {
		if(this.grafo==null)
			return false;
		else
			return true;
	}
	
	public List<Food> getVertici(){
		return new ArrayList<Food>(this.grafo.vertexSet());
	}
}
