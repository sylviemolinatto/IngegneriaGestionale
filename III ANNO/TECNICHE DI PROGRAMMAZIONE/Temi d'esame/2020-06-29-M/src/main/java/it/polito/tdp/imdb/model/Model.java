package it.polito.tdp.imdb.model;

import java.util.ArrayList;
import java.util.Collections;
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
	private Graph<Director,DefaultWeightedEdge> grafo;
	public Map<Integer,Director> idMap;
	public List<Director> best;
	public ConnectivityInspector<Director,DefaultWeightedEdge> ispettore;
	private int numMigliore;
	
	public Model() {
		this.dao = new ImdbDAO();
		this.idMap = new HashMap<Integer,Director>();
	}
	
	public String creaGrafo(int anno) {
		
		this.grafo = new SimpleWeightedGraph<Director,DefaultWeightedEdge>(DefaultWeightedEdge.class);
		
		Graphs.addAllVertices(this.grafo, this.dao.getVertici(anno));
		
		for(Director d : this.dao.getVertici(anno)) {
			this.idMap.put(d.getId(), d);
		}
		
		for(Edge e : this.dao.getArchi(anno, idMap)) {
			Graphs.addEdgeWithVertices(this.grafo, e.getD1(), e.getD2(), e.getWeight());
		}
		
		return "Grafo creato: "+this.grafo.vertexSet().size()+" vertici, "+this.grafo.edgeSet().size()+" archi\n";
	}
	
	public List<Edge> getAdiacenti(Director d) {
		
		List<Director> adiacenti = Graphs.neighborListOf(this.grafo, d);
		
		List<Edge> result = new ArrayList<Edge>();
		for(Director di : adiacenti) {
			result.add(new Edge(d,di,(int) this.grafo.getEdgeWeight(this.grafo.getEdge(d, di))));
		}
		
		Collections.sort(result);
		
		return result;
	}
	
	public List<Director> cercaSequenza(Director registaIniziale, int numAttoriCondivisi){
		
		this.best = new ArrayList<Director>();
		List<Director> parziale = new ArrayList<Director>();
		parziale.add(registaIniziale);
		
		this.ispettore = new ConnectivityInspector<Director,DefaultWeightedEdge>(this.grafo);
		List<Director> connessi = new ArrayList<Director>(ispettore.connectedSetOf(registaIniziale));
		connessi.remove(registaIniziale);
		
		this.numMigliore=1;
		
		ricorsiva(parziale, connessi, numAttoriCondivisi,0);
		
		return best;
	}
	
	public void ricorsiva(List<Director> parziale, List<Director> connessi, int numAttoriCondivisi, int livello) {
		
		int condivisi = calcolaAttoriCondivisi(parziale);
		
		if(condivisi>numAttoriCondivisi) {
			return;
		}
		
		if(condivisi<=numAttoriCondivisi) {
			if(parziale.size()>numMigliore) {
				best=new ArrayList<Director>(parziale);
				numMigliore = parziale.size();
			}
		}
		
		if(livello==connessi.size()) {
			return;
		}
		
		if(this.grafo.containsEdge(this.grafo.getEdge(parziale.get(parziale.size()-1), connessi.get(livello)))) {
			
			parziale.add(connessi.get(livello));
			ricorsiva(parziale,connessi,numAttoriCondivisi,livello+1);
			
			parziale.remove(parziale.size()-1);
			ricorsiva(parziale,connessi,numAttoriCondivisi,livello+1);
		}
		
		ricorsiva(parziale,connessi,numAttoriCondivisi,livello+1);
	}
	
	public List<Edge> getPercorso(){
		
		List<Edge> result = new ArrayList<Edge>();
		for(int i=0;i<best.size()-1;i++) {
			result.add(new Edge(best.get(i),best.get(i+1),(int) this.grafo.getEdgeWeight(this.grafo.getEdge(best.get(i), best.get(i+1)))));
		}
		return result;
	}
	
	private int calcolaAttoriCondivisi(List<Director> parziale) {
		int result=0;
		for(int i=0; i<parziale.size()-1;i++) {
			result+=this.grafo.getEdgeWeight(this.grafo.getEdge(parziale.get(i), parziale.get(i+1)));
		}
		return result;
	}

	public boolean grafoCreato() {
		if(this.grafo==null)
			return false;
		else
			return true;
	}
	
	public List<Director> getDirectors(){
		return new ArrayList<Director>(this.grafo.vertexSet());
	}
}
