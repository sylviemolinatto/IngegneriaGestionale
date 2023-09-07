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
	private List<Edge> archi;
	private List<String> best;
	private int numVerticiMax;
	private ConnectivityInspector<String, DefaultWeightedEdge> ispettore;
	
	public Model() {
		this.dao = new EventsDao();
	}
	
	public String creaGrafo(String categoriaReato, int mese) {
		
		this.grafo = new SimpleWeightedGraph<String, DefaultWeightedEdge>(DefaultWeightedEdge.class);
		List<String> vertici = this.dao.getVertici(categoriaReato, mese);
		Graphs.addAllVertices(this.grafo, vertici);
		
		this.archi = this.dao.getArchi(categoriaReato, mese);
		for(Edge e : archi) {
			if(this.grafo.containsVertex(e.getId1()) && this.grafo.containsVertex(e.getId2())){
				Graphs.addEdgeWithVertices(this.grafo, e.getId1(), e.getId2(), e.getWeight());
			}
		}
		
		return "Grafo creato: "+this.grafo.vertexSet().size()+" vertici, "+this.grafo.edgeSet().size()+" archi\n";
	}
	

	public double getPesoMedioGrafo() {
		
		double pesoTot=0;
		for(Edge e : this.archi) {
			pesoTot+=e.getWeight();
		}
		double pesoMedio=pesoTot/this.grafo.edgeSet().size();
		return pesoMedio;
	}
	
	public List<Edge> getArchiSopraLaMedia(){
		
		double pesoMedio = this.getPesoMedioGrafo();
		List<Edge> result = new ArrayList<Edge>();
		for(Edge e : this.archi) {
			if(e.getWeight()>pesoMedio) {
				result.add(e);
			}
		}
		return result;
	}
	
	public List<String> trovaPercorso(String primo, String ultimo){
		
		this.best = new ArrayList<String>();
		List<String> parziale = new ArrayList<String>();
		this.numVerticiMax=0;
		parziale.add(primo);
		ricorsiva(parziale, ultimo);
		
		return best;
	}

	private void ricorsiva(List<String> parziale, String ultimo) {
		
		String ultimoAggiunto = parziale.get(parziale.size()-1);
		
		if(ultimoAggiunto.equals(ultimo)){
			if(parziale.size()>best.size()) {
				best = new LinkedList<String>(parziale);
			}
			return;
		}
		
		// scorro i vicini dell'ultimo inserito e provo le varie strade
		for(String v : Graphs.neighborListOf(this.grafo,ultimoAggiunto)) {
			if(!parziale.contains(v)) {
				parziale.add(v);
				ricorsiva(parziale,ultimo);
				parziale.remove(parziale.size()-1);
			}
			
		}
		
	}
	
	public List<Edge> getPercorso(){
		
		List<Edge> result = new ArrayList<Edge>();
		for(int i=0; i<this.best.size()-1;i++) {
			result.add(new Edge(this.best.get(i), this.best.get(i+1), (int) this.grafo.getEdgeWeight(this.grafo.getEdge(best.get(i), best.get(i+1)))));
		}
		
		return result;
	}

	public List<String> getCategorie(){
		return this.dao.getCategorie();
	}
	
	public List<Integer> getMesi(){
		return this.dao.getMesi();
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
