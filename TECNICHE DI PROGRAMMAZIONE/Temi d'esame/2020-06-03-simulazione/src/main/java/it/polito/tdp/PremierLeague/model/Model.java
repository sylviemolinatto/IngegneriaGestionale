package it.polito.tdp.PremierLeague.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleDirectedWeightedGraph;

import it.polito.tdp.PremierLeague.db.PremierLeagueDAO;

public class Model {
	
	private Graph<Player,DefaultWeightedEdge> grafo;
	private PremierLeagueDAO dao;
	private Map<Integer,Player> idMap;
	private List<Player> best;
	private int bestGradoTitolarita;
	private List<Player> giocatori;
	
	
	public Model() {
		this.idMap = new HashMap<Integer,Player>();
		this.dao = new PremierLeagueDAO();
		for(Player p : this.dao.listAllPlayers()) {
			idMap.put(p.getPlayerID(), p);
		}
	}
	
	public Graph<Player,DefaultWeightedEdge> creaGrafo(double goalFatti) {
		
		this.grafo = new SimpleDirectedWeightedGraph<Player,DefaultWeightedEdge>(DefaultWeightedEdge.class);
		
		// aggiunta vertici
		Graphs.addAllVertices(this.grafo,this.dao.getVertici(goalFatti));
		
		// aggiunta archi
		
		for(Edge e : this.dao.getArchi(goalFatti)) {
			if(this.grafo.vertexSet().contains(this.idMap.get(e.getP1()))&&this.grafo.vertexSet().contains(this.idMap.get(e.getP2())) )
				Graphs.addEdgeWithVertices(this.grafo,this.idMap.get(e.getP1()),this.idMap.get(e.getP2()),e.getWeight());
		}
		return this.grafo;
	}

	public Player getTopPlayer() {
		
		if(this.grafo!=null) {
			int best=0;
			Player topPlayer=null;
			for(Player p : this.grafo.vertexSet()) {
				if(this.grafo.outDegreeOf(p)>best) {
					best=this.grafo.outDegreeOf(p);
					topPlayer=p;
				}
			}
			
			return topPlayer;
		}
		
		return null;
		
	}
	
	
	public List<Edge> getAvversariBattuti(){
	
		Player topPlayer = this.getTopPlayer();
		List<Edge> avversari = new LinkedList();
		for(DefaultWeightedEdge d : this.grafo.outgoingEdgesOf(topPlayer)) {
			avversari.add(new Edge(this.grafo.getEdgeSource(d).getPlayerID(),this.grafo.getEdgeTarget(d).getPlayerID(),(int) this.grafo.getEdgeWeight(d)));
		}
		return avversari;
	}
		
	
	
	public Map<Integer,Player> getIdMap(){
		return this.idMap;
	}
	
	public List<Player> cercaDreamTeam(int numGiocatori){
		this.best = new ArrayList<Player>();
		List<Player> parziale = new ArrayList<Player>();
		this.bestGradoTitolarita = Integer.MIN_VALUE;
		this.giocatori = new ArrayList<Player>(this.grafo.vertexSet());
		ricorsiva(0,parziale,numGiocatori);
		return best;
	}
	
	public void ricorsiva(int livello, List<Player> parziale, int numGiocatori) {
		
		int gradoDiTitolarita = calcolaGradoDiTitolarita(parziale);
		// caso terminale
		if(parziale.size()==numGiocatori) {
			// controllo se è la soluzione migliore
			if(this.best==null || gradoDiTitolarita>this.bestGradoTitolarita) {
				this.best = new ArrayList<Player>(parziale);
				this.bestGradoTitolarita=gradoDiTitolarita;
				
			}
			return;
		}
		
		if(this.giocatori.size()==livello) {
			return;
		}
		
		// provo ad aggiungere un giocatore a parziale
		if(parziale.size()==0 || InserimentoValido(parziale,this.giocatori.get(livello))) {
			parziale.add(this.giocatori.get(livello));
			ricorsiva(livello+1,parziale,numGiocatori);
		}
		
		
		// provo a non aggiungere
		if(InserimentoValido(parziale,this.giocatori.get(livello))) {
			parziale.remove(parziale.size()-1);
			ricorsiva(livello+1,parziale,numGiocatori);
			
		}
		
		
		
	}

	private boolean InserimentoValido(List<Player> parziale, Player player) {
		
		boolean trovato=true;
		for(Player p : parziale) {
			if(this.grafo.outgoingEdgesOf(p).contains(player)) {
				trovato=false;
			}
		}
		return trovato;
	}

	public int calcolaGradoDiTitolarita(List<Player> parziale) {
		
		int gradoDiTitolarita=0;
		for(Player p : parziale) {
			for(DefaultWeightedEdge d : this.grafo.outgoingEdgesOf(p)){
				gradoDiTitolarita+=this.grafo.getEdgeWeight(d);
			}
			
			for(DefaultWeightedEdge d : this.grafo.incomingEdgesOf(p)) {
				gradoDiTitolarita-=this.grafo.getEdgeWeight(d);
			}
		}
		return gradoDiTitolarita;
	}
}
