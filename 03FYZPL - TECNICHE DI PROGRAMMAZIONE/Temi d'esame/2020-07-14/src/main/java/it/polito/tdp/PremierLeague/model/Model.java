package it.polito.tdp.PremierLeague.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleDirectedWeightedGraph;

import it.polito.tdp.PremierLeague.db.PremierLeagueDAO;

public class Model {
	
	private PremierLeagueDAO dao;
	private Graph<Team,DefaultWeightedEdge> grafo;
	private Map<Integer,Team> idMap;
	private Map<Team,Integer> classifica;
	private List<Team> squadre;
	private Simulatore sim;
	
	public Model() {
		this.dao = new PremierLeagueDAO();
		this.idMap = new HashMap<Integer,Team>();
		this.classifica = new HashMap<Team,Integer>();
		this.squadre = new ArrayList<Team>();
	}
	
	public Graph<Team,DefaultWeightedEdge> creaGrafo() {
		
		this.grafo = new SimpleDirectedWeightedGraph<Team,DefaultWeightedEdge>(DefaultWeightedEdge.class);
		
		this.squadre = this.dao.listAllTeams();
		Graphs.addAllVertices(this.grafo,this.squadre);
		
		// elavoro la classifica e idMap
		for(Team t : this.squadre) {
			int punteggioClassifica = this.dao.getPunteggioClassifica(t.getTeamID());
			this.classifica.put(t, punteggioClassifica);
		    t.setPunteggioInClassifica(punteggioClassifica);
			this.idMap.put(t.getTeamID(), t);
		}
		
		
		
		for(Edge e : this.dao.getArchi(idMap)) {
			
			// differenza di punti in classifica
			int pesoArco = this.classifica.get(e.getT1())-this.classifica.get(e.getT2());
			
			if(pesoArco>0) {
				Graphs.addEdge(this.grafo,e.getT1(),e.getT2(), pesoArco);
			}
			else if(pesoArco<0) {
				Graphs.addEdge(this.grafo,e.getT2(),e.getT1(), -pesoArco);
			}
		}
		
		System.out.println("GRAFO CREATO!\n");
		System.out.println("# VERTICI : "+this.grafo.vertexSet().size()+"\n");
		System.out.println("# ARCHI : "+this.grafo.edgeSet().size()+"\n");
		
		return this.grafo;
	}
	
	public List<Edge> getSquadreBattute(Team squadra){
		
		List<Edge> squadreBattute = new ArrayList<Edge>();
		List<Team> vicini = Graphs.neighborListOf(this.grafo, squadra);
		for(Team t : vicini) {
			if(this.grafo.containsEdge(squadra,t)) {
				squadreBattute.add(new Edge(squadra,t,(int) this.grafo.getEdgeWeight(this.grafo.getEdge(squadra, t))));
			}
		}
		Collections.sort(squadreBattute);
		return squadreBattute;
	}
	
	public List<Edge> getSquadreNonBattute(Team squadra){
		
		List<Edge> squadreNonBattute = new ArrayList<Edge>();
		List<Team> vicini = Graphs.neighborListOf(this.grafo, squadra);
		for(Team t : vicini) {
			if(this.grafo.containsEdge(t,squadra)) {
				squadreNonBattute.add(new Edge(t,squadra,(int) this.grafo.getEdgeWeight(this.grafo.getEdge(t, squadra))));
			}
		}
		Collections.sort(squadreNonBattute);
		return squadreNonBattute;
	}
	
	public List<Team> getSquadre(){
		return this.dao.listAllTeams();
	}
	
	public boolean grafoCreato() {
		if(this.grafo==null) {
			return false;
		}
		else 
			return true;
	}
	
	public void simula(int numReporter, int sogliaX) {
		this.sim = new Simulatore(numReporter,sogliaX);
		this.sim.init(this.dao.listAllMatches(), idMap, classifica);
		this.sim.run();
	}
	
	public int getNumPartiteSottoSoglia() {
		return this.sim.getNumPartiteSottoSoglia();
	}
	
	public double getMediaReporter() {
		return this.sim.getMediaReporter();
	}
	
}
