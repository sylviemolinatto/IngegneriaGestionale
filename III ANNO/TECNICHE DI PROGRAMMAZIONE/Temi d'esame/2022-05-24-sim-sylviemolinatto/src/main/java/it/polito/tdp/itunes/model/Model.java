package it.polito.tdp.itunes.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.alg.connectivity.ConnectivityInspector;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

import it.polito.tdp.itunes.db.ItunesDAO;

public class Model {
	
	private ItunesDAO dao;
	private Graph<Track,DefaultWeightedEdge> grafo;
	private Map<Integer,Track> idMap;
	private List<Track> best;
	private int numMigliore;
	ConnectivityInspector<Track,DefaultWeightedEdge> ispettore;
	List<Track> connessi;
	
	
	public Model() {
		this.dao = new ItunesDAO();
		this.idMap = new HashMap<Integer,Track>();
		for(Track t : this.dao.getAllTracks()) {
			idMap.put(t.getTrackId(), t);
		}
	}
	
	
	public List<Genre> getGenre(){
		return this.dao.getAllGenres();
	}
	
	public Graph<Track,DefaultWeightedEdge> creaGrafo(Genre genre) {
		
		this.grafo = new SimpleWeightedGraph<Track,DefaultWeightedEdge>(DefaultWeightedEdge.class);
		// aggiunta vertici
		Graphs.addAllVertices(this.grafo,this.dao.getVertici(genre));
		
		// aggiunta archi
		for(Edge e : this.dao.getArchi(genre)) {
			Graphs.addEdgeWithVertices(this.grafo,this.idMap.get(e.getTrackID1()),this.idMap.get(e.getTrackID2()),e.getWeight());
		}
		
		return this.grafo;
	}
	
	public List<Edge> getCanzoniDifferenzaDurataMassima(Genre genre){
		
		List<Edge> archi = this.dao.getArchi(genre);
		List<Edge> result = new ArrayList<Edge>();
		for(int i=0;i<archi.size();i++) {
			if(i==0) {
				result.add(archi.get(i));
			}
			else {
				if(archi.get(i).getWeight()==result.get(result.size()-1).getWeight()) {
					result.add(archi.get(i));
				}
				else
					break;
			}
		}
		
		return result;
	
	}
	
	public Map<Integer,Track> getIdMap(){
		return this.idMap;
	}
	
	public List<Track> init(Track t,Long memoria){
		
		this.ispettore=new ConnectivityInspector<Track,DefaultWeightedEdge>(this.grafo);
		
		this.best = new ArrayList<Track>();
		this.numMigliore=1;
		
		List<Track> parziale = new ArrayList<Track>();
		if(t.getBytes()>memoria) {
			return parziale;
		}
		parziale.add(t);
	
		this.connessi = new ArrayList<Track>(ispettore.connectedSetOf(parziale.get(0)));
		if(!this.connessi.contains(t))
			this.connessi.add(0, t);
		
		ricorsiva(1,parziale,memoria);
		
		System.out.println(best);
		return best;
		

	}


	private void ricorsiva(int livello,List<Track> parziale, Long memoria) {
		
		int capacita = this.calcolaCapacita(parziale);
		
		// caso terminale
		if(capacita<=memoria) {
			// controllo se è la soluzione migliore
			if(parziale.size()>numMigliore) {
				this.best = new ArrayList<Track>(parziale);
				numMigliore = parziale.size();
				//return;
			}
		}
		
		if(connessi.size()==livello) {
			return;
		}
		
		if(capacita>memoria) {
			return;
		}
		
		// provo ad aggiungere
		Track t = connessi.get(livello);
		parziale.add(t);
		ricorsiva(livello+1,parziale,memoria);
		
					
		// provo a non aggiungere
		parziale.remove(parziale.size()-1);
		ricorsiva(livello+1,parziale,memoria);		
		
	}


	private int calcolaCapacita(List<Track> parziale) {
		int capacita=0;
		for(Track t : parziale) {
			capacita+=t.getBytes();
		}
		return capacita;
	}

	public int getCapacita() {
		return this.calcolaCapacita(best);
	}
}
