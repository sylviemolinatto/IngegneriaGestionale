package it.polito.tdp.PremierLeague.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.alg.connectivity.ConnectivityInspector;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

import it.polito.tdp.PremierLeague.db.PremierLeagueDAO;

public class Model {
	
	private PremierLeagueDAO dao;
	private Graph<Match,DefaultWeightedEdge> grafo;
	private Map<Integer,Match> idMap;
	private List<Match> best;
	private int pesoBest;
	private ConnectivityInspector<Match,DefaultWeightedEdge> ispettore;
	private List<Edge> archiAggiunti;
	
	public Model() {
		this.dao = new PremierLeagueDAO();
		this.idMap = new HashMap<Integer,Match>();
	}
	
	public void creaGrafo(int mese, int minutiMin) {
		this.grafo = new SimpleWeightedGraph<Match,DefaultWeightedEdge>(DefaultWeightedEdge.class);
		
		// aggiungo i vertici
		Graphs.addAllVertices(this.grafo, dao.getVertici(mese));
		
		for(Match m : dao.getVertici(mese)) {
			this.idMap.put(m.getMatchID(), m);
		}
		
		// aggiungo gli archi
		
		for(Edge e : this.dao.getArchi(mese, minutiMin, idMap)) {
			Graphs.addEdgeWithVertices(this.grafo, e.getM1(), e.getM2(),e.getWeight());
		}
		
		System.out.println("GRAFO CREATO!\n");
		System.out.println("# VERTICI : "+this.grafo.vertexSet().size()+"\n");
		System.out.println("# ARCHI : "+this.grafo.edgeSet().size()+"\n");
		
		
	}
	
	public List<Edge> getCoppieConnessioneMax(int mese, int minutiMin){
		
		List<Edge> result = new ArrayList<Edge>();
		int max = 0;
		for(Edge e : this.dao.getArchi(mese, minutiMin, idMap)) {
			if(e.getWeight()>max) {
				result = new ArrayList<Edge>();
				result.add(e);
				max=e.getWeight();
			}
			if(e.getWeight()==max && e!=result.get(result.size()-1)) {
				result.add(e);
			}
		}
		
		return result;
	}
	
	public boolean grafoCreato() {
		if(this.grafo==null) {
			return false;
		}
		else 
			return true;
	}
	
	public List<Match> cercaSequenza(Match m1, Match m2) {
		
		this.best = new ArrayList<Match>();
		this.pesoBest = 0;
		
		this.archiAggiunti = new ArrayList<Edge>();
		
		this.ispettore = new ConnectivityInspector<Match,DefaultWeightedEdge>(this.grafo);
		List<Match> connessi = new ArrayList<Match>(ispettore.connectedSetOf(m1));
		
		connessi.remove(m1);
		
		if(!connessi.contains(m2)) {
			return null;
		}
		
		connessi.remove(m2);
		
		List<Match> parziale = new ArrayList<Match>();
		parziale.add(m1);
		this.best.add(m1);
		
		ricorsiva(parziale,connessi,m2,0);
		if(this.grafo.containsEdge(this.best.get(this.best.size()-1),m2)) {

			this.best.add(m2);
		}
		else {
			return null;
		}
		
		return best;
		
	}
	
	public void ricorsiva(List<Match> parziale, List<Match> connessi, Match m2 , int livello) {
		
		Match ultimoAggiunto = parziale.get(parziale.size()-1);
		
		if(this.grafo.containsEdge(ultimoAggiunto, m2)) {
			// controllo il peso del cammino
			if(calcolaPesoCammino(parziale)>this.pesoBest) {
				this.pesoBest = calcolaPesoCammino(parziale);
				this.best = new ArrayList<Match>(parziale);
			}
		}
		
		if(livello == connessi.size()-1) {
			return;
		}
		
		
		if(this.grafo.containsEdge(ultimoAggiunto,connessi.get(livello))) {
			
			Edge daAggiungere = new Edge(ultimoAggiunto,connessi.get(livello),(int)this.grafo.getEdgeWeight(this.grafo.getEdge(ultimoAggiunto, connessi.get(livello))));
			
			if(!this.archiAggiunti.contains(daAggiungere)) {
				parziale.add(connessi.get(livello));
				this.archiAggiunti.add(daAggiungere);
				ricorsiva(parziale,connessi,m2,livello+1);
				
				parziale.remove(parziale.get(parziale.size()-1));
				this.archiAggiunti.remove(daAggiungere);
				ricorsiva(parziale,connessi,m2,livello+1);
				
			}
		}
		
		ricorsiva(parziale,connessi,m2,livello+1);
		
	}
	
	public int calcolaPesoCammino(List<Match> parziale) {
		int peso=0;
		for(int i=0;i<parziale.size()-1;i++) {
			peso+=this.grafo.getEdgeWeight(this.grafo.getEdge(parziale.get(i),parziale.get(i+1)));
		}
		return peso;
	}
	
	public int nVertici() {
		return this.grafo.vertexSet().size();
	}
	
	public int nArchi() {
		return this.grafo.edgeSet().size();
	}
	
	public List<Match> getMatches(int mese){
		return this.dao.getVertici(mese);
	}
	
	public List<Edge> getArchiSequenza(){
		
		List<Edge> sequenzaArchi = new ArrayList<Edge>();
		for(int i=0; i<this.best.size()-1;i++) {
			sequenzaArchi.add(new Edge(this.best.get(i),this.best.get(i+1),(int)(this.grafo.getEdgeWeight(this.grafo.getEdge(this.best.get(i),this.best.get(i+1))))));
		}
		
		return sequenzaArchi;
	}
	
	public int getPesoSequenza() {
		return this.calcolaPesoCammino(best);
	}
	
}
