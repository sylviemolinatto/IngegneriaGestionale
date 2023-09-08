package it.polito.tdp.yelp.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.alg.connectivity.ConnectivityInspector;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

import it.polito.tdp.yelp.db.YelpDao;

public class Model {
	
	private YelpDao dao;
	private Graph<Business,DefaultWeightedEdge> grafo;
	private Map<String,Business> idMap;
	private List<Business> best;
	private ConnectivityInspector<Business,DefaultWeightedEdge> ispettore;
	
	public Model() {
		this.dao = new YelpDao();
		this.idMap = new HashMap<String, Business>();
	}
	
	public List<String> getCities(){
		return this.dao.getCities();
	}
	
	public void creaGrafo(String city) {
		
		this.grafo = new SimpleWeightedGraph<Business,DefaultWeightedEdge>(DefaultWeightedEdge.class);
		
		// aggiungo i vertici
		Graphs.addAllVertices(this.grafo, this.dao.getVertici(city));
		
		// riempo idMap
		for(Business b : this.dao.getVertici(city)) {
			this.idMap.put(b.getBusinessId(), b);
		}
		
		// aggiungo gli archi
		for(Edge e : this.dao.getArchi(city, idMap)) {
			Graphs.addEdge(this.grafo, e.getBusiness_1(), e.getBusiness_2(), e.getWeight());
		
		}
		
		
		System.out.println("Grafo creati!\n");
		System.out.println("# VERTICI : "+this.grafo.vertexSet().size()+"\n");
		System.out.println("# ARCHI : "+this.grafo.edgeSet().size()+"\n");
		
	}
	
	public Edge getLocalePiuDistante(Business b) {
		
		List<Business> adiacenti = Graphs.neighborListOf(this.grafo, b);
		double distanza = Integer.MIN_VALUE;
		Edge piuLontano = null;
		for(Business business : adiacenti) {
			double peso = this.grafo.getEdgeWeight(this.grafo.getEdge(b, business));
			if(peso>distanza) {
				distanza = peso;
				piuLontano = new Edge(b, business,peso);	
			}
		}
		
		return piuLontano;
	}
	
	public boolean grafoCreato() {
		if(this.grafo==null){
			return false;
		}
		else
			return true;
	}
	
	public int getNVertici() {
		return this.grafo.vertexSet().size();
	}
	
	public int getNArchi() {
		return this.grafo.edgeSet().size();
	}
	
	public List<Business> getVertici(){
		return new ArrayList<Business>(this.grafo.vertexSet());
	}
	
	public List<Business> init(String city, double avg, Business b1, Business b2) {
		
		List<Business> businessSottoLaMedia = this.dao.getBusinessByAverageAndCity(city, avg);
		
		
	    List<Business> parziale = new ArrayList<Business>();
	    
	    this.best = new ArrayList<Business>();
	    
	    this.ispettore = new ConnectivityInspector<Business,DefaultWeightedEdge>(this.grafo);
	    
	    List<Business> connessi = new ArrayList<Business>(this.ispettore.connectedSetOf(b1));
	    connessi.removeAll(businessSottoLaMedia);
	    connessi.remove(b1);	    
	    
	    if(!connessi.contains(b2)) {
	    	return best;
	    }
	   
	   connessi.remove(b2);
	   parziale.add(b1);
	  
	   cercaSequenza(parziale,b2,connessi,0);
	   
	   this.best.add(b2);
	   
	   return best;
		
	}
	
	public void cercaSequenza(List<Business> parziale,Business ultimo, List<Business> connessi, int livello) {
		
	   
		Business ultimoAggiunto=parziale.get(parziale.size()-1);
		if(parziale.size()>this.best.size() && this.grafo.containsEdge(ultimoAggiunto,ultimo)) {
			this.best = new ArrayList<Business>(parziale);
		}
		
		if(livello==connessi.size()-1) {
			return;
		}
		
		if(grafo.containsEdge(ultimoAggiunto,connessi.get(livello))) {
			
			parziale.add(connessi.get(livello));
			cercaSequenza(parziale, ultimo,connessi, livello+1);
			
			parziale.remove(parziale.size()-1);
			cercaSequenza(parziale, ultimo, connessi, livello+1);
		}
		
		
	}
	
	public double getKilometres() {
		
		double km=0;
		for(int i=0;i<this.best.size()-1;i++) {
			km+=this.grafo.getEdgeWeight(this.grafo.getEdge(this.best.get(i),this.best.get(i+1)));
		}
		return km;
	}
	
	
	
}
