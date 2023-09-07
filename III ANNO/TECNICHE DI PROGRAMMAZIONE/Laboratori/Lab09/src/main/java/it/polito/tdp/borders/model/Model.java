package it.polito.tdp.borders.model;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.alg.connectivity.ConnectivityInspector;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;
import org.jgrapht.traverse.BreadthFirstIterator;
import org.jgrapht.traverse.DepthFirstIterator;
import org.jgrapht.traverse.GraphIterator;

import it.polito.tdp.borders.db.BordersDAO;

public class Model{

	private BordersDAO dao;
	private List<Country> countries;
	private Map<Integer,Country> countriesMap;
	private List<Border> borders;
	private Graph<Country,DefaultEdge> grafo;
	
	public Model() {
		this.dao = new BordersDAO();
		this.countries = dao.loadAllCountries();
		this.countriesMap = new HashMap<Integer,Country>();
		for(Country c : countries) {
			this.countriesMap.put(c.getcCode(), c);
		}
	}
	
	public List<Country> getCountries(){
		return this.dao.loadAllCountries();
	}
	
	public int degreeOf(Graph grafo,Country c) {
		return grafo.degreeOf(c);
	}
	
	public Graph<Country,DefaultEdge> creaGrafo(int anno){
		
		this.borders = dao.getCountryPairs(anno);
		this.grafo = new SimpleGraph<Country,DefaultEdge>(DefaultEdge.class);
		
		for(Border b : borders) {
			Country c1 = this.countriesMap.get(b.getcCodeState1());
			Country c2 = this.countriesMap.get(b.getcCodeState2());
			this.grafo.addVertex(c1);
			this.grafo.addVertex(c2);
			this.grafo.addEdge(c1, c2);
		}
		
		return grafo;
		
	}
	
	public Graph<Country,DefaultEdge> creaGrafo(){
		
		this.borders = dao.getCountryPairs();
		this.grafo = new SimpleGraph<Country,DefaultEdge>(DefaultEdge.class);
		
		for(Border b : borders) {
			Country c1 = this.countriesMap.get(b.getcCodeState1());
			Country c2 = this.countriesMap.get(b.getcCodeState2());
			this.grafo.addVertex(c1);
			this.grafo.addVertex(c2);
			this.grafo.addEdge(c1, c2);
		}
		
		return grafo;
		
	}
	
	public Integer getNumberOfConnectedComponents() {
		ConnectivityInspector ci = new ConnectivityInspector(grafo);
		return ci.connectedSets().size();
	}
	
	public List<Country> visitaGrafoJGraphT(Country partenza) {
		
		List<Country> visitati = new LinkedList<Country>();
		this.grafo=this.creaGrafo();
		
		// VERSIONE 1 - BreadthFirstIterator
		GraphIterator<Country,DefaultEdge> visita = new BreadthFirstIterator<>(this.grafo,partenza);
		while(visita.hasNext()) {
			Country c = visita.next();
			visitati.add(c);
		}
		
		/* VERSIONE 2 - DeapthFirstIterator
		 GraphIterator<Country,DefaultEdge> visita = new DepthFirstIterator<>(this.grafo,partenza);
		 while(visita.hasNext()) {
			Country c = visita.next();
			visitati.add(c);
		 }*/
		
		 return visitati;
	}
	
	public List<Country> visitaGrafoIterativa(Country partenza){
		
		
		// Creo due liste : quella dei nodi visitati e quella dei nodi da visitare
		List<Country> visited = new LinkedList<Country>();
		List<Country> toBeVisited = new LinkedList<Country>();
		
		// Aggiungo alla lista dei nodi visitati il nodo di partenza
		visited.add(partenza);
		
		// Aggiungo ai vertici da visitare tutti i vertici collegati a quello inserito
		toBeVisited.addAll(Graphs.neighborListOf(this.grafo,partenza));
		
		while(!toBeVisited.isEmpty()) {
			
			// rimuovo il vertice in testa alla coda
			Country temp = toBeVisited.remove(0);
			
			// Aggiungo il nodo alla lista di quelli visitati
			visited.add(temp);
			
			// Ottengo tutti i vicini del nodo
			List<Country> listaDeiVicini = Graphs.neighborListOf(this.grafo,temp);
			
			// Rimuovo da questa vista tutti i nodi che ho già visitato e che so già che devo visitare
			listaDeiVicini.removeAll(visited);
			listaDeiVicini.removeAll(toBeVisited);
			
			// Aggiungo i rimanenti alla lista dei nodi da visitare
			toBeVisited.addAll(listaDeiVicini);
		}
		return visited;
	}
	
	public List<Country> visitaGrafoRicorsiva(Country partenza){
		
		List<Country> visited = new LinkedList<Country>();
		visitaRicorsiva(partenza,visited);
		return visited;
	}

	private void visitaRicorsiva(Country c, List<Country> visited) {
		
		visited.add(c);
		
		for(Country cc : Graphs.neighborListOf(this.grafo,c)) {
			
			if(!visited.contains(cc)) {
				visitaRicorsiva(cc,visited);
				// NO BACKTRACK IN THIS CASE
			}
		}
	}

}
