package it.polito.tdp.crimes.model;

import java.time.LocalDate;
import java.util.ArrayList;
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
	private int pesoMax=0;
	private ConnectivityInspector<String,DefaultWeightedEdge> ispettore;
	
	public Model() {
		this.dao = new EventsDao();
		this.vertici = new ArrayList<String>();
		this.archi = new ArrayList<Edge>();
	}
	
	public List<String> getOffenseCatgories(){
		return this.dao.getOffenseCategories();
	}
	
	public List<LocalDate> getDays(){
		return this.dao.getDays();
	}
	
	public String creaGrafo(String categoria, LocalDate giorno) {
		
		this.grafo = new SimpleWeightedGraph<String, DefaultWeightedEdge>(DefaultWeightedEdge.class);
		
		Graphs.addAllVertices(this.grafo, this.dao.getVertici(categoria, giorno));
		this.vertici = this.dao.getVertici(categoria, giorno);
		this.archi = this.dao.getArchi(categoria, giorno);
		
		for(Edge e : archi) {
			if(this.grafo.containsVertex(e.getId1()) && this.grafo.containsVertex(e.getId2())){
				Graphs.addEdgeWithVertices(this.grafo, e.getId1(), e.getId2(), e.getWeight());
			}
		}
		
		return String.format("Grafo creato (%d vertici, %d archi)\n", this.grafo.vertexSet().size(), this.grafo.edgeSet().size()); 
	}
	
    public boolean grafoCreato() {
		
		if(this.grafo==null)
			return false;
		else 
			return true;
	}
    
    
    public List<Edge> getArchiSottoPesoMediano(){
    	
    	double pesoMediano = this.getPesoMediano();
    	List<Edge> result = new ArrayList<Edge>();
    	for(Edge e : this.archi) {
    		if(e.getWeight()<pesoMediano) {
    			result.add(e);
    		}
    	}
    	
    	return result;
    }
    
    public double getPesoMediano() {
    	
    	int pesoMax=Integer.MIN_VALUE;
    	
    	for(Edge e : this.archi) {
    		if(e.getWeight()>pesoMax) {
    			pesoMax = e.getWeight();
    		}
    	}
    	
    	int pesoMin=Integer.MAX_VALUE;
    	
    	for(Edge e : this.archi) {
    		if(e.getWeight()<pesoMin) {
    			pesoMin = e.getWeight();
    		}
    	}
    	
        double pesoMediano = (double)(pesoMax+pesoMin)/2;
    	return pesoMediano;
    	
    }
    
    public List<Edge> getArchi(){
    	return this.archi;
    }
    
    
    public List<String> calcolaPercorso(String primo, String ultimo){
    	
    	this.best = new ArrayList<String>();
    	List<String> parziale = new ArrayList<String>();
    	this.ispettore = new ConnectivityInspector<String,DefaultWeightedEdge>(this.grafo);
    	List<String> connessi = new ArrayList<String>(this.ispettore.connectedSetOf(primo));
    	connessi.remove(primo);
    	if(!connessi.contains(ultimo)) {
    		return best;
    	}
    	
    	connessi.remove(ultimo);
    	parziale.add(primo);
    	best.add(primo);
    	
    	ricorsiva(parziale, connessi, ultimo, 0);
    	best.add(ultimo);
    	return best;
    	
    }

	private void ricorsiva(List<String> parziale, List<String> connessi, String ultimo, int livello) {
		
		int peso = calcolaPeso(parziale);
		
		if(peso>this.pesoMax && this.grafo.containsEdge(parziale.get(parziale.size()-1),ultimo)) {
			pesoMax=peso;
			this.best = new ArrayList<String>(parziale);
		}
		
		if(livello==connessi.size()) {
			return;
		}
		
		if(this.grafo.containsEdge(parziale.get(parziale.size()-1),connessi.get(livello)) && !parziale.contains(connessi.get(livello))) {
			parziale.add(connessi.get(livello));
			ricorsiva(parziale, connessi, ultimo, livello+1);
			
			parziale.remove(parziale.size()-1);
			ricorsiva(parziale, connessi, ultimo, livello+1);
		}
		ricorsiva(parziale, connessi, ultimo, livello+1);
		
	}

	public int calcolaPeso(List<String> parziale) {
		int peso=0;
		for(int i=0;i<parziale.size()-1;i++) {
			peso+=this.grafo.getEdgeWeight(this.grafo.getEdge(parziale.get(i),parziale.get(i+1)));
		}
		return peso;
	}
    
   

	
}
