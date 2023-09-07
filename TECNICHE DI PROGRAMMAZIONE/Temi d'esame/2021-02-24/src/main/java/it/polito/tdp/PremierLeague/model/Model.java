package it.polito.tdp.PremierLeague.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleDirectedWeightedGraph;

import it.polito.tdp.PremierLeague.db.PremierLeagueDAO;

public class Model {
	
	private PremierLeagueDAO dao;
	private Graph<Player,DefaultWeightedEdge> grafo;
	private Map<Integer,Player> idMap;
	private double deltaMigliore;
	private Simulatore sim;
	
	public Model() {
		this.dao = new PremierLeagueDAO();
	}
	
	public void creaGrafo(Match m) {
		
		this.grafo = new SimpleDirectedWeightedGraph<Player,DefaultWeightedEdge>(DefaultWeightedEdge.class);
		
		Graphs.addAllVertices(this.grafo, this.dao.getVertici(m.getMatchID()));
		
		this.idMap = new HashMap<Integer,Player>();
		
		for(Player p : this.dao.getVertici(m.getMatchID())) {
			this.idMap.put(p.getPlayerID(), p);
		}
		
		for(Edge e : this.dao.getArchi(m, idMap)) {
			if(e.getPeso()<0) {
				Graphs.addEdgeWithVertices(this.grafo,e.getP2(), e.getP1(),-e.getPeso());
			}
			else {
				Graphs.addEdgeWithVertices(this.grafo,e.getP1(), e.getP2(), e.getPeso());
			}
		}
		
		System.out.println("Grafo creato!\n");
		System.out.println("# VERTICI : "+this.grafo.vertexSet().size()+"\n");
		System.out.println("# ARCHI : "+this.grafo.edgeSet().size()+"\n");
	}
	
	public Player giocatoreMigliore() {
		
		Player best = null;
		deltaMigliore = Integer.MIN_VALUE;
		double differenza=0;
		List<Player> vertici = new ArrayList<Player>(this.grafo.vertexSet());
		
		for(Player p : vertici) {
			
			differenza=0;
			
			Set<DefaultWeightedEdge> entranti = this.grafo.incomingEdgesOf(p);
			Set<DefaultWeightedEdge> uscenti = this.grafo.outgoingEdgesOf(p);
			
			for(DefaultWeightedEdge u : uscenti) {
				differenza+=this.grafo.getEdgeWeight(u);
			}
			
			for(DefaultWeightedEdge e : entranti) {
				differenza-=this.grafo.getEdgeWeight(e);
			}
			
			if(differenza>=deltaMigliore) {
				deltaMigliore=differenza;
				best = p;
			}
		}
			
		return best;
	}
	
	public int getNVertici() {
		return this.grafo.vertexSet().size();
	}
	
	public int getNArchi() {
		return this.grafo.edgeSet().size();
	}
	
	public double getDeltaMigliore() {
		return this.deltaMigliore;
	}
	
	public List<Match> getAllMatches() {
		return this.dao.listAllMatches();
	}
	
	public boolean grafoCreato() {
		if(this.grafo==null)
			return false;
		else
			return true;
	}
	
	public void simula(Match m, int numAzioniSalienti) {
		this.sim = new Simulatore(this.grafo);
		int teamPlayerMigliore = this.dao.getSquadraGiocatoreMigliore(this.giocatoreMigliore());
		this.sim.init(m,numAzioniSalienti,teamPlayerMigliore);
	}
	
	public TeamSimulator getSquadra1() {
		return this.sim.getSquadra1();
	}
	
	public TeamSimulator getSquadra2() {
		return this.sim.getSquadra2();
	}
	
	
}
