package it.polito.tdp.artsmia.model;

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

import it.polito.tdp.artsmia.db.ArtsmiaDAO;

public class Model {

	private ArtsmiaDAO dao;
	public Graph<Artist, DefaultWeightedEdge> grafo;
	private Map<Integer, Artist> idMap;
	private List<Edge> archi;
	private List<Artist> best;
	private ConnectivityInspector<Artist,DefaultWeightedEdge> ispettore;
	public int pesoArchi;
	
	public Model() {
		this.dao = new ArtsmiaDAO();
		this.idMap = new HashMap<Integer,Artist>();
		this.archi = new ArrayList<Edge>();
	}
	
	public List<String> getRoles(){
		return this.dao.getRoles();
	}
	
	public String creaGrafo(String role) {
		
		this.grafo = new SimpleWeightedGraph<Artist,DefaultWeightedEdge>(DefaultWeightedEdge.class);
		
		Graphs.addAllVertices(this.grafo, this.dao.getVertici(role));
		
		for(Artist a : this.dao.getVertici(role)) {
			this.idMap.put(a.getArtist_id(), a);
		}
		

		for(Edge e : archi ) {
			if(this.grafo.containsVertex(e.getA1()) && this.grafo.containsVertex(e.getA2())){
				Graphs.addEdgeWithVertices(this.grafo, e.getA1(), e.getA2(), e.getWeight());
				this.archi.add(new Edge(e.getA1(),e.getA1(),e.getWeight()));
			}
		}
		
		return String.format("Grafo creato (%d vertici, %d archi)\n", this.grafo.vertexSet().size(), this.grafo.edgeSet().size()); 
	}
	
	public List<Artist> cercaPercorso(int artist_id) {
		
		Artist artistaIniziale = this.idMap.get(artist_id);
		
		this.best = new ArrayList<Artist>();
		List<Artist> parziale = new ArrayList<Artist>();
		parziale.add(artistaIniziale);
		this.ispettore = new ConnectivityInspector<Artist,DefaultWeightedEdge>(this.grafo);
		List<Artist> connessi = new ArrayList<Artist>(this.ispettore.connectedSetOf(artistaIniziale));
		
		ricorsiva(parziale, connessi, 0);
		
		return best;
	}
	
	private void ricorsiva(List<Artist> parziale, List<Artist> connessi, int livello) {
		
		if(livello==connessi.size()) {
			if(parziale.size()>best.size()) {
				best=new ArrayList<Artist>(parziale);
			}
			return;
		}
		
		if(parziale.size()==2) {
			this.pesoArchi = (int) this.grafo.getEdgeWeight(this.grafo.getEdge(parziale.get(0), parziale.get(1)));
		}
		
		if(this.grafo.containsEdge(parziale.get(parziale.size()-1),connessi.get(livello)) && (parziale.size()<2 
		 || this.grafo.getEdgeWeight(this.grafo.getEdge(parziale.get(parziale.size()-1), connessi.get(livello)))==this.pesoArchi) 
		 && !parziale.contains(connessi.get(livello))) {
			parziale.add(connessi.get(livello));
			ricorsiva(parziale, connessi, livello+1);
			
			parziale.remove(parziale.size()-1);
			ricorsiva(parziale,connessi,livello+1);
		}
		
		ricorsiva(parziale, connessi, livello+1);
		
	}

	public List<Edge> getArchi(){
		
		Collections.sort(this.archi);
		return this.archi;
	}
	
	public List<Artist> getVertici(){
		return new ArrayList<Artist>(this.grafo.vertexSet());
	}
	
	public boolean grafoCreato() {
		
		if(this.grafo==null)
			return false;
		else 
			return true;
	}
	
	public boolean idEsistente(Integer id) {
		if(this.idMap.get(id)==null) {
			return false;
		}
		else 
			return true;
	}
	
	public int getNumeroEsposizioni() {
		int peso=0;
		if(best.size()>1) {
			peso=(int) this.grafo.getEdgeWeight(this.grafo.getEdge(best.get(0), best.get(1)));
		}
		return peso*best.size();
	}
}
