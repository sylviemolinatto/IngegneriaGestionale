package it.polito.tdp.genes.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

import it.polito.tdp.genes.db.GenesDao;

public class Model {
	
	private Graph<Genes,DefaultWeightedEdge> grafo;
	private GenesDao dao;
	private Simulatore sim;
	
	public Model() {
		this.dao = new GenesDao();
		this.sim = new Simulatore();
	}
	
	public List<Genes> getAllGenes(){
		return this.dao.getAllGenes();
	}
	
	public Graph<Genes,DefaultWeightedEdge> creaGrafo() {
		this.grafo = new SimpleWeightedGraph<Genes,DefaultWeightedEdge>(DefaultWeightedEdge.class);
		
		Graphs.addAllVertices(this.grafo, this.dao.getAllNecessaryGenes());
		
		for(Edge e : this.dao.getEdges()) {
			if(!e.getG1().equals(e.getG2())) {
				Graphs.addEdgeWithVertices(this.grafo, e.getG1(), e.getG2(), e.getWeight());
			}
		}
		
		return this.grafo;
		
	}
	
	public List<Edge> geniAdiacenti(Genes partenza){
		
		List<Edge> adiacenti = new ArrayList<Edge>();
		
		for(Edge e : this.dao.getEdges()) {
			if(e.getG1().equals(partenza)) {
				adiacenti.add(e);
			}
		}
		Collections.sort(adiacenti);
		return adiacenti;
	}
	
	public Map<Genes,Integer> geniInStudioENumIng(int nIngegneri, Genes genes){
		return this.sim.geniInStudioENumIng(nIngegneri,genes, this.grafo);
	}
	
}
