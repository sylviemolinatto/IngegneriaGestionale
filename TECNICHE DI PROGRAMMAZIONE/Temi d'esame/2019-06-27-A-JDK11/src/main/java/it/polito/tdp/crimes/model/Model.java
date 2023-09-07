package it.polito.tdp.crimes.model;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.alg.connectivity.ConnectivityInspector;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

import it.polito.tdp.crimes.db.EventsDao;

public class Model {
	
	private EventsDao dao;
	private Graph<String,DefaultWeightedEdge> grafo;
	private List<String> vertici;
	private List<Edge> archi;
	private List<String> best;
	private int pesoMax;
	private ConnectivityInspector<String,DefaultWeightedEdge> ispettore;
	
	public Model() {
		this.dao = new EventsDao();
	}
	
	public String creaGrafo(String categoria, int anno) {
		
		this.grafo = new SimpleWeightedGraph<String,DefaultWeightedEdge>(DefaultWeightedEdge.class);
		
		this.vertici = this.dao.getVertici(categoria, anno);
		Graphs.addAllVertices(this.grafo, this.vertici);
		
		this.archi = this.dao.getArchi(categoria, anno);
		for(Edge e : this.archi) {
			if(this.grafo.containsVertex(e.getId1()) && this.grafo.containsVertex(e.getId2()) && !e.getId1().equals(e.getId2())) {
				Graphs.addEdgeWithVertices(this.grafo, e.getId1(), e.getId2(), e.getWeight());
				System.out.println(e+" = "+e.getWeight()+"\n");
			}
		}
		
		
		return "Grafo creato: "+this.grafo.vertexSet().size()+" vertici, "+this.grafo.edgeSet().size()+" archi\n";
	}
	
	public int getPesoMassimo() {
		
		int pesoMax=0;
		for(Edge e : this.archi) {
			if(e.getWeight()>pesoMax) {
				pesoMax=e.getWeight();
			}
		}
		return pesoMax;
	}
	
	public List<Edge> getArchiPesoMassimo(){
		
		List<Edge> result = new LinkedList<Edge>();
		int pesoMax = this.getPesoMassimo();
		for(Edge e : this.archi) {
			if(e.getWeight()==pesoMax) {
				result.add(e);
			}
		}
		
		return result;
	}
	
	public List<String> cercaPercorso(String primo, String ultimo){
		
		this.best = new ArrayList<String>();
		List<String> parziale = new ArrayList<String>();
		parziale.add(primo);
		
		this.ispettore = new ConnectivityInspector<String,DefaultWeightedEdge>(this.grafo);
		List<String> connessi = new ArrayList<String>(this.ispettore.connectedSetOf(primo));
		this.pesoMax = Integer.MAX_VALUE;
		
		
		if(!connessi.contains(ultimo)) {
			return null;
		}
		if(connessi.size()<this.vertici.size()) {
			return null;
		}
		
		connessi.remove(primo);
		connessi.remove(ultimo);
		
		ricorsiva(parziale,connessi,ultimo,0);
		best.add(ultimo);
	
		return best;
	}
	
	private void ricorsiva(List<String> parziale,List<String> connessi, String ultimo, int livello) {
		
		String ultimoAggiunto = parziale.get(parziale.size()-1);
		
		if(this.grafo.containsEdge(this.grafo.getEdge(ultimoAggiunto, ultimo)) && parziale.size()==this.vertici.size()-1) {
			if(calcolaPeso(parziale)<this.pesoMax) {
				pesoMax = calcolaPeso(parziale);
				this.best = new ArrayList<String>(parziale);
			}
			return;
		}
		
		if(livello == connessi.size()) {
			return;
		}
		
		if(this.grafo.containsEdge(this.grafo.getEdge(ultimoAggiunto, connessi.get(livello)))) {
			parziale.add(connessi.get(livello));
			ricorsiva(parziale, connessi, ultimo, livello+1);
			
			parziale.remove(parziale.size()-1);
			ricorsiva(parziale, connessi, ultimo, livello+1);
		}
		
		ricorsiva(parziale, connessi, ultimo, livello+1);
	}

	public int calcolaPeso(List<String> parziale) {
		
		int peso=0;
		for(int i=0; i<parziale.size()-1;i++) {
			peso+=this.grafo.getEdgeWeight(this.grafo.getEdge(parziale.get(i), parziale.get(i+1)));
		}
		return peso;
	}
	
	public List<Edge> getPercorso(){
		
		List<Edge> result = new ArrayList<Edge>();
		
		for(int i=0; i<this.best.size()-1;i++) {
			result.add(new Edge(best.get(i), best.get(i+1), (int) this.grafo.getEdgeWeight(this.grafo.getEdge(this.best.get(i), this.best.get(i+1)))));
		}
		return result;
	}

	public List<String> getCategorie(){
		return this.dao.getCategorie();
	}
	
	public List<Integer> getAnni(){
		return this.dao.getAnni();
	}
	
	public List<Edge> getArchi(){
		return this.archi;
	}
	
	
	public boolean grafoCreato() {
		if(this.grafo==null) {
			return false;
		}
		else {
			return true;
		}
	}
}
