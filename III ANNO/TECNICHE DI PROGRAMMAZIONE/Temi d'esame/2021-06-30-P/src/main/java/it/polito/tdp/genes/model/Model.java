package it.polito.tdp.genes.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.alg.connectivity.ConnectivityInspector;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

import it.polito.tdp.genes.db.GenesDao;

public class Model {
	
	private GenesDao dao;
	private Graph<String,DefaultWeightedEdge> grafo;
	private List<String> best;
	private ConnectivityInspector<String,DefaultWeightedEdge> ispettore;
	private int maxLenght;
	
	public Model() {
		this.dao=new GenesDao();
	}
	
	public void creaGrafo() {
		
		this.grafo = new SimpleWeightedGraph<String,DefaultWeightedEdge>(DefaultWeightedEdge.class);
		
		// aggiungo i vertici
		Graphs.addAllVertices(this.grafo,this.dao.getVertici());
		
		// aggiungo gli archi
		for(Edge e : this.dao.getArchi()) {
			Graphs.addEdgeWithVertices(this.grafo,e.getL1(),e.getL2(),e.getWeight());		
		}
		

		System.out.println("Grafo creato!\n");
		System.out.println("# VERTICI : "+this.grafo.vertexSet().size()+"\n");
		System.out.println("# ARCHI : "+this.grafo.edgeSet().size()+"\n");
	}
	
	public List<String> getVertici(){
		return this.dao.getVertici();
	}
	
	public int getNVertici() {
		return this.grafo.vertexSet().size();
	}
	
	public int getNArchi() {
		return this.grafo.edgeSet().size();
	}
	
	public List<Edge> getAdiacenze(String localization){
		
		List<String> adiacenze = Graphs.neighborListOf(this.grafo, localization);
		List<Edge> archi = new ArrayList<Edge>();
		
		for(String a : adiacenze) {
			if(this.grafo.containsEdge(localization, a)) {
				archi.add(new Edge(localization,a,(int) this.grafo.getEdgeWeight(this.grafo.getEdge(localization, a))));
			}
		}
		
		return archi;
	}
	
	public List<String> init(String localization){
		
		this.best = new ArrayList<String>();
		List<String> parziale = new ArrayList<String>();
		this.ispettore = new ConnectivityInspector<String,DefaultWeightedEdge>(this.grafo);
		List<String> connessi = new ArrayList(this.ispettore.connectedSetOf(localization));
		this.maxLenght = Integer.MIN_VALUE;
		connessi.remove(localization);
		
		parziale.add(localization);
		
		cercaSequenza(parziale,0,connessi);
		
		return best;
	}
	
	public void cercaSequenza(List<String> parziale,int livello, List<String> connessi) {
		
		if(calcolaLunghezza(parziale)>this.maxLenght) {
			this.best = new ArrayList<String>(parziale);
			this.maxLenght = calcolaLunghezza(parziale);
		}
		
		if(livello==connessi.size()-1) {
			return;
		}
		
		if(this.grafo.containsEdge(parziale.get(parziale.size()-1),connessi.get(livello))) {
			parziale.add(connessi.get(livello));
			cercaSequenza(parziale,livello+1,connessi);
			
			parziale.remove(parziale.size()-1);
			cercaSequenza(parziale,livello+1,connessi);
		}
		
		cercaSequenza(parziale,livello+1,connessi);
		
	}
	
	private int calcolaLunghezza(List<String> localizations) {
		
		int lunghezza=0;
		for(int i=0; i<localizations.size()-1;i++) {
			lunghezza+=this.grafo.getEdgeWeight(this.grafo.getEdge(localizations.get(i),localizations.get(i+1)));
		}
		return lunghezza;
	}

	public boolean grafoCreato() {
		if(this.grafo==null) {
			return false;
		}
		else {
			return true;
		}
	}
	
	public List<Edge> getBestCammino(){
		
		List<Edge> cammino = new ArrayList<Edge>();
		for(int i=0; i<best.size()-1;i++) {
			cammino.add(new Edge(best.get(i),best.get(i+1),(int) this.grafo.getEdgeWeight(this.grafo.getEdge(best.get(i),best.get(i+1)))));
		}
		
		return cammino;
	}

}