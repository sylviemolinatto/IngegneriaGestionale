package it.polito.tdp.yelp.model;

import java.util.ArrayList;
import java.util.List;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

import it.polito.tdp.yelp.db.YelpDao;

public class Model {
	
	private Graph<User,DefaultWeightedEdge> grafo;
	private List<User> utenti;
	private YelpDao dao;
	private Simulatore sim;
	
	public String creaGrafo(int minReviews, int anno) {
		
		this.grafo = new SimpleWeightedGraph<User,DefaultWeightedEdge>(DefaultWeightedEdge.class);
		this.dao = new YelpDao();
		
		this.utenti = dao.getUsersWithReviews(minReviews);
		Graphs.addAllVertices(this.grafo, utenti);
		
		for(User u1 : this.utenti) {
			for(User u2 : this.utenti) {
				if(!u1.equals(u2) && u1.getUserId().compareTo(u2.getUserId())<0) {
					int sim = dao.calcolaSimilarita(u1, u2, anno);
					if(sim>0) {
						Graphs.addEdge(this.grafo, u1, u2, sim);
					}
				}
			}
		}
		
		return "Grafo creato con "+this.grafo.vertexSet().size()+" vertici e "+this.grafo.edgeSet().size()+" archi \n";
		
	}
	
	public List<User> utentiSimili(User utente) {
		
		int max=0;
		for(DefaultWeightedEdge d : this.grafo.edgesOf(utente)) {
			if(this.grafo.getEdgeWeight(d)>max) {
				max = (int)this.grafo.getEdgeWeight(d);
			}
		}
		
		List<User> result = new ArrayList<User>();
		for(DefaultWeightedEdge d : this.grafo.edgesOf(utente)) {
			if((int)this.grafo.getEdgeWeight(d)==max) {
				result.add(Graphs.getOppositeVertex(this.grafo, d, utente));
			}
		}
		
		return result;
	}
	
	public List<User> getUsers(){
		return this.utenti;
	}
	
	public boolean grafoCreato() {
		if(this.grafo==null) {
			return false;
		}
		else {
			return true;
		}
	}
	
	public int getNVertici() {
		return this.grafo.vertexSet().size();
	}
	
	public void simula(int x1, int x2) {
		this.sim = new Simulatore(this.grafo);
		sim.init(x1, x2);
		sim.run();
	}
	
	public int getNumeroGiorni() {
		return this.sim.getNumeroGiorni();
	}
	
	public List<Giornalista> getGiornalisti(){
		return this.sim.getGiornalisti();
	}
	
}
