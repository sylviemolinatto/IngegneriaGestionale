package it.polito.tdp.yelp.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleDirectedWeightedGraph;

import it.polito.tdp.yelp.db.YelpDao;

public class Model {
	
	private YelpDao dao;
	private Graph<Review,DefaultWeightedEdge> grafo;
	private Map<String,Review> idMap;
	private List<Review> maxArchiUscenti;
	private int max;
	private List<Review> best;
	private List<Review> vertici;
	
	public Model() {
		
		this.dao = new YelpDao();
		this.idMap = new HashMap<String,Review>();
	}
	
	
	public void creaGrafo(Business business) {
		
		this.grafo = new SimpleDirectedWeightedGraph<Review,DefaultWeightedEdge>(DefaultWeightedEdge.class);
		
		// aggiungo i vertici
		this.vertici = this.dao.getReviewsByBusinessID(business.getBusinessId());
		Graphs.addAllVertices(this.grafo, this.dao.getReviewsByBusinessID(business.getBusinessId()));
		
		// carico la idMap
		for(Review r : this.dao.getReviewsByBusinessID(business.getBusinessId())) {
			this.idMap.put(r.getReviewId(), r);
		}
		
		// aggiungo gli archi
		for(Edge e : this.dao.getArchi(business, idMap)) {
			if(this.grafo.containsVertex(e.getR1()) && this.grafo.containsVertex(e.getR2())) {
				Graphs.addEdge(this.grafo,e.getR1(),e.getR2(),e.getWeight());
			}
		}
		
		
		System.out.println("Grafo creato!\n");
		System.out.println("# VERTICI : "+this.grafo.vertexSet().size()+"\n");
		System.out.println("# ARCHI : "+this.grafo.edgeSet().size()+"\n");
		
		this.maxArchiUscenti = new ArrayList<Review>();
		this.max=0;
		
		for(Review r : this.dao.getReviewsByBusinessID(business.getBusinessId())) {
			if(this.grafo.outgoingEdgesOf(r).size()>max) {
				this.maxArchiUscenti = new ArrayList<Review>();
				this.maxArchiUscenti.add(r);
				this.max = this.grafo.outgoingEdgesOf(r).size();
			}
			else if(this.grafo.outgoingEdgesOf(r).size()==max) {
				this.maxArchiUscenti.add(r);
			}
		}
	}

	public List<Review> cercaSequenza(){
		
		this.best = new ArrayList<Review>();
		List<Review> parziale = new ArrayList<Review>();
		
		ricorsiva(parziale,0);
		
		return best;
	}

	private void ricorsiva(List<Review> parziale, int livello) {
		
		if(parziale.size()>best.size()) {
			best = new ArrayList<Review>(parziale);
		}
		
		if(livello==vertici.size()) {
			return;
		}
		
		for(Review r : this.vertici) {
			if(!parziale.contains(r) && aggiuntaValida(r,parziale)) {
				parziale.add(r);
				ricorsiva(parziale,livello+1);
				parziale.remove(parziale.size()-1);
			}
		}
		
	}


	private boolean aggiuntaValida(Review r, List<Review> parziale) {
		
		if(parziale.size()==0) {
			return true;
		}
		else {
			if(this.grafo.containsEdge(this.grafo.getEdge(parziale.get(parziale.size()-1), r)) && parziale.get(parziale.size()-1).getStars()<=r.getStars()) {
				return true;
			}
			return false;
		}
		
	}


	public List<String> getAllCities(){
		return this.dao.getAllCities();
	}
	
	public List<Business> getBusinessByCity(String city){
		return this.dao.getBusinessByCity(city);
	}
	
	public int getNVertici() {
		return this.grafo.vertexSet().size();
	}
	
	public int getNArchi() {
		return this.grafo.edgeSet().size();
	}
	
	public List<Review> getMaxArchiUscenti(){
		return this.maxArchiUscenti;
	}
	
	public int getMax() {
		return this.max;
	}
	
	public boolean grafoCreato() {
		if(this.grafo==null)
			return false;
		else
			return true;
	}
	
	public int calcolaNumGiorni() {
		
		int numGiorni=0;
		for(int i=0;i<this.best.size()-1;i++) {
			numGiorni+=this.grafo.getEdgeWeight(this.grafo.getEdge(this.best.get(i), this.best.get(i+1)));
		}
		return numGiorni;
	}
	
}
